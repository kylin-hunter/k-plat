# standalone-local
##exec
docker run --name nacos223 -e MODE=standalone -p 8848:8848 -p 9848:9848 -d nacos/nacos-server:v2.2.3
check problem:   docker logs xxxx
result: 不支持 mac M1
##exec
docker run --name nacos223-slim -e MODE=standalone -p 8848:8848 -p 9848:9848 -d nacos/nacos-server:v2.2.3-slim

