

#列出创建topic
docker run -it --rm --network cluster_kafka-cluster-net   bitnami/kafka:3.3.2 kafka-topics.sh --list  --bootstrap-server kafka-1:9092

docker run -it --rm --network cluster_kafka-cluster-net   bitnami/kafka:3.3.2 kafka-topics.sh --list  --bootstrap-server 172.25.104.103 :9292


#创建topic
docker run -it --rm  --network cluster_kafka-cluster-net bitnami/kafka:3.3.2 kafka-topics.sh  --bootstrap-server kafka-1:9092 \
      --create --topic test --replication-factor 1 --partitions 2

#查看topic
docker run -it --rm  --network cluster_kafka-cluster-net bitnami/kafka:3.3.2 kafka-topics.sh  --bootstrap-server kafka-1:9092 \
            --describe --topic test
#删除topic
docker run -it --rm  --network cluster_kafka-cluster-net bitnami/kafka:3.3.2 kafka-topics.sh  --bootstrap-server kafka-1:9092 \
      --delete --topic test

# 修改topic配置
docker run -it --rm  --network cluster_kafka-cluster-net bitnami/kafka:3.3.2 kafka-configs.sh  --bootstrap-server kafka-1:9092 \
       --entity-type topics --entity-name test --alter --add-config max.message.bytes=128000


#查看topic配置
docker run -it --rm  --network cluster_kafka-cluster-net bitnami/kafka:3.3.2 kafka-configs.sh  --bootstrap-server kafka-1:9092 \
       --entity-type topics --entity-name test --describe

# 恢复topic原来的配置
docker run -it --rm  --network cluster_kafka-cluster-net bitnami/kafka:3.3.2 kafka-configs.sh  --bootstrap-server kafka-1:9092 \
       --entity-type topics --entity-name test --alter --delete-config max.message.bytes


# 增加topic分区数
docker run -it --rm  --network cluster_kafka-cluster-net bitnami/kafka:3.3.2 kafka-topics.sh  --bootstrap-server kafka-1:9092 \
       --topic test --alter --partitions 2




#生产者
 docker run -it --rm  --network cluster_kafka-cluster-net bitnami/kafka:3.3.2 kafka-console-producer.sh  --bootstrap-server kafka-1:9092 \
           --topic test

docker run -it --rm  --network cluster_kafka-cluster-net bitnami/kafka:3.3.2 kafka-console-producer.sh  --bootstrap-server 172.25.104.103:9392 \
           --topic test
#消费者
 docker run -it --rm  --network cluster_kafka-cluster-net bitnami/kafka:3.3.2 kafka-console-consumer.sh  --bootstrap-server kafka-1:9092 \
           --from-beginning --topic test
docker run -it --rm   --network cluster_kafka-cluster-net  bitnami/kafka:3.3.2 kafka-console-consumer.sh  --bootstrap-server 172.25.104.103:9292 \
            --from-beginning --topic test
docker run -it --rm   --network cluster_kafka-cluster-net  bitnami/kafka:3.3.2 kafka-console-consumer.sh  --bootstrap-server 172.25.104.103:9392 \
            --from-beginning --topic test
#查看消费者组列表
 docker run -it --rm  --network cluster_kafka-cluster-net bitnami/kafka:3.3.2 kafka-consumer-groups.sh  --bootstrap-server kafka-1:9092 \
           --list
#查看消费者组
 docker run -it --rm  --network cluster_kafka-cluster-net bitnami/kafka:3.3.2 kafka-consumer-groups.sh  --bootstrap-server kafka-1:9092 \
           --describe --group console-consumer-67923