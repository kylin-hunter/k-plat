docker pull kibana:7.17.13
docker run -d --name kibana --net kylin-net  -p 127.0.0.1:5601:5601 -e "ELASTICSEARCH_HOSTS=http://elasticsearch:9200" kibana:7.17.13

