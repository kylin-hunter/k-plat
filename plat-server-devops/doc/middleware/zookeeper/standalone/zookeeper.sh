docker stop zookeeper && docker rm zookeeper

docker run --name zookeeper --privileged=true -d \
-p 2181:2181  \
-p 8181:8080  \
-v /Users/bijian/software/zookeeper/singleton/data:/data \
-v ./zoo.cfg:/conf/zoo.cfg \
-v /Users/bijian/software/zookeeper/singleton/datalog:/datalog \
zookeeper:3.6.4



docker exec -it zookeeper bash
./bin/zkServer.sh status
./bin/zkCli.sh -server localhost:2181

create /bijian "bijian-value"
ls /bijian
get /bijian



http://localhost:8181/commands

prettyZoo