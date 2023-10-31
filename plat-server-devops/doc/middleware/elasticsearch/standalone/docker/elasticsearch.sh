docker pull docker.elastic.co/elasticsearch/elasticsearch:7.17.13
docker pull elasticsearch:7.17.13

docker network create kylin-net

docker run -d -p 9200:9200 -p 9300:9300 --name elasticsearch --net kylin-net  -e "discovery.type=single-node" elasticsearch:7.17.13


http://localhost:9200/

