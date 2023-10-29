===================================
资料
===================================
https://grafana.com/docs/loki/latest/send-data/promtail/installation/
===================================
安装
===================================

docker pull grafana/promtail:2.9.2

docker stop promtail && docker rm promtail

# --restart=always

docker run -d \
--name promtail \
--network kylin-net  \
--privileged=true \
-v /Users/bijian/Documents/workspace_gitee/k-plat/plat-server-devops/doc/middleware/log/singleton/docker/promtail:/mnt/config \
-v /Users/bijian/Documents/workspace_gitee/k-plat/logs:/data/logs \
-v /etc/localtime:/etc/localtime:ro \
-p 9080:9080 \
-m 256m \
grafana/promtail:2.9.2 -config.file=/mnt/config/promtail-config.yaml


===================================
验证
===================================


