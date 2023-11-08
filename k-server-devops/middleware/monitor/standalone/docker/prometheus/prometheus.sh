
=====
安装prometheus
=====

docker pull prom/prometheus
docker pull prom/prometheus:v2.47.2
docker stop prometheus && docker rm prometheus
docker volume create prometheus-data

# --restart=always
docker run  -d --name prometheus  -p 9090:9090 \
 --network kylin-net  \
 -v ./config/prometheus.yml:/etc/prometheus/prometheus.yml \
 prom/prometheus


 http://localhost:9090/graph
