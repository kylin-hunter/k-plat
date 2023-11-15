docker run -d --name kafka-ui -it -p 8080:8080 \
--network  kylin-net \
-e DYNAMIC_CONFIG_ENABLED=true provectuslabs/kafka-ui



http://localhost:8080


// linux 上访问kafka 2.0.0 版本 试验 --start
docker pull provectuslabs/kafka-ui:0.3.3  --platform linux/amd64
docker save -o kafka-ui.tar provectuslabs/kafka-ui:0.3.3
docker network ls

docker network create bijian --subnet 172.20.1.0/24


-e DYNAMIC_CONFIG_ENABLED=true \


docker stop kafka-ui && docker  rm kafka-ui

docker run -d --privileged=true --network  bijian --name kafka-ui \
-it -p 0.0.0.0:8462:8080 \
-e KAFKA_CLUSTERS_0_NAME=cskb \
-e KAFKA_CLUSTERS_0_BOOTSTRAPSERVERS=10.214.77.88:8297 \
-e KAFKA_CLUSTERS_0_ZOOKEEPER=10.214.77.87:8182 \
provectuslabs/kafka-ui:0.3.3
// linux 上访问kafka 2.0.0 版本 试验 --end

