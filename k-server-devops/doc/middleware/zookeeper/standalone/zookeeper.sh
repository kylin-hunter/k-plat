docker stop zookeeper && docker rm zookeeper
docker volume create zookeeper-data
docker volume create zookeeper-log
docker run --name zookeeper --privileged=true -d \
-p 2181:2181  \
-p 8181:8080  \
-v zookeeper-data:/data \
-v ./zoo.cfg:/conf/zoo.cfg \
-v zookeeper-log:/datalog \
zookeeper:3.6.4



docker exec -it zookeeper bash
./bin/zkServer.sh status
./bin/zkCli.sh -server localhost:2181

create /bijian "bijian-value"
ls /bijian
get /bijian



http://localhost:8181/commands

prettyZoo