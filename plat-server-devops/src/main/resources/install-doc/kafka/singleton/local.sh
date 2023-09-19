docker pull bitnami/kafka:3.3.2


docker network create kafka-net --driver bridge
docker network ls
docker network inspect kafka-net

docker run -d --name kafka-server --hostname kafka-server \
    --network kafka-net \
    -e KAFKA_CFG_NODE_ID=0 \
    -e KAFKA_CFG_PROCESS_ROLES=controller,broker \
    -e KAFKA_CFG_LISTENERS=PLAINTEXT://:9092,CONTROLLER://:9093 \
    -e KAFKA_CFG_LISTENER_SECURITY_PROTOCOL_MAP=CONTROLLER:PLAINTEXT,PLAINTEXT:PLAINTEXT \
    -e KAFKA_CFG_CONTROLLER_QUORUM_VOTERS=0@kafka-server:9093 \
    -e KAFKA_CFG_CONTROLLER_LISTENER_NAMES=CONTROLLER \
    bitnami/kafka:3.3.2

    docker run -it --rm --network kafka-net \
      bitnami/kafka:3.3.2 kafka-topics.sh --list  --bootstrap-server kafka-server:9092


#创建topic
docker run -it --rm  --network kafka-net bitnami/kafka:3.3.2 kafka-topics.sh  --bootstrap-server kafka-server:9092 \
      --create --topic test --replication-factor 1 --partitions 2

#查看topic
docker run -it --rm  --network kafka-net bitnami/kafka:3.3.2 kafka-topics.sh  --bootstrap-server kafka-server:9092 \
            --describe --topic test
#删除topic
docker run -it --rm  --network kafka-net bitnami/kafka:3.3.2 kafka-topics.sh  --bootstrap-server kafka-server:9092 \
      --delete --topic test

# 修改topic配置
docker run -it --rm  --network kafka-net bitnami/kafka:3.3.2 kafka-configs.sh  --bootstrap-server kafka-server:9092 \
       --entity-type topics --entity-name test --alter --add-config max.message.bytes=128000


#查看topic配置
docker run -it --rm  --network kafka-net bitnami/kafka:3.3.2 kafka-configs.sh  --bootstrap-server kafka-server:9092 \
       --entity-type topics --entity-name test --describe

# 恢复topic原来的配置
docker run -it --rm  --network kafka-net bitnami/kafka:3.3.2 kafka-configs.sh  --bootstrap-server kafka-server:9092 \
       --entity-type topics --entity-name test --alter --delete-config max.message.bytes


# 增加topic分区数
docker run -it --rm  --network kafka-net bitnami/kafka:3.3.2 kafka-topics.sh  --bootstrap-server kafka-server:9092 \
       --topic test --alter --partitions 2




#生产者
 docker run -it --rm  --network kafka-net bitnami/kafka:3.3.2 kafka-console-producer.sh  --bootstrap-server kafka-server:9092 \
           --topic test
#消费者
 docker run -it --rm  --network kafka-net bitnami/kafka:3.3.2 kafka-console-consumer.sh  --bootstrap-server kafka-server:9092 \
           --from-beginning --topic test
#查看消费者组列表
 docker run -it --rm  --network kafka-net bitnami/kafka:3.3.2 kafka-consumer-groups.sh  --bootstrap-server kafka-server:9092 \
           --list
#查看消费者组
 docker run -it --rm  --network kafka-net bitnami/kafka:3.3.2 kafka-consumer-groups.sh  --bootstrap-server kafka-server:9092 \
           --describe --group console-consumer-67923