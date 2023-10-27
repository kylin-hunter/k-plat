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


mysql-exporter  panel==7362

node-exporter panel=1860


