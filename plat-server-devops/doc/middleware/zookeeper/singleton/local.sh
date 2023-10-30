docker run --name zookeeper --privileged=true -p 2181:2181 -d \
-v /Users/bijian/software/zookeeper/singleton/data:/data \
-v /Users/bijian/software/zookeeper/singleton/conf/zoo.cfg:/conf/zoo.cfg \
-v /Users/bijian/software/zookeeper/singleton/datalog:/datalog \
zookeeper:3.6.4



docker exec -it zookeeper bash
./bin/zkServer.sh status
./bin/zkCli.sh -server localhost:2181

create /bijian "bijian-value"
ls /bijian
get /bijian



#http://localhost:xxxx/commands

#prettyZoo