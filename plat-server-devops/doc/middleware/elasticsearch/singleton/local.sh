docker pull docker.elastic.co/elasticsearch/elasticsearch:7.17.13
docker pull elasticsearch:7.17.13
docker pull kibana:7.17.13



docker network create es-singleton

docker run -d -p 9200:9200 -p 9300:9300 --name elasticsearch --net es-singleton  -e "discovery.type=single-node" elasticsearch:7.17.13

docker run -d --name kibana --net es-singleton  -p 127.0.0.1:5601:5601 -e "ELASTICSEARCH_HOSTS=http://elasticsearch:9200" kibana:7.17.13

