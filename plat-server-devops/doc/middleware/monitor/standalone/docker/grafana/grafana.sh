=====
安装 grafana
=====
docker pull grafana/grafana
docker pull grafana/grafana:10.2.0
docker stop grafana && docker rm grafana

# --restart=always
docker run -d --name grafana -p 3000:3000 \
--name=grafana \
--network kylin-net  \
-v /Users/bijian/software/grafana/singleton/storage:/var/lib/grafana \
grafana/grafana:10.2.0


http://localhost:3000/login

admin/Grafana123


mysqld-exporter  panel==7362
https://grafana.com/grafana/dashboards/7362-mysql-overview/

node-exporter panel=1860
https://grafana.com/grafana/dashboards/1860-node-exporter-full/


redis-exporter panel=763
https://grafana.com/grafana/dashboards/763-redis-dashboard-for-prometheus-redis-exporter-1-x/
Prometheus Redis Dashboard - displays data collected by the Prometheus redis_exporter (https://github.com/oliver006/redis_exporter)

https://grafana.com/grafana/dashboards/11835-redis-dashboard-for-prometheus-redis-exporter-helm-stable-redis-ha/
Redis Dashboard for Prometheus Redis Exporter 1.x, it works with helm stable/redis-ha exporter. If you missing redis memory utilization, please modify "maxmemory" value in values.yaml


