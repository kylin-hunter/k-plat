===================================
资料
===================================
https://github.com/grafana/loki
https://github.com/grafana/loki/blob/v2.9.2/cmd/loki/loki-local-config.yaml

===================================
安装
===================================

docker pull grafana/loki

docker stop loki && docker rm loki

# --restart=always

docker run -d --name=loki \
--network kylin-net  \
--mount type=bind,source=./config/loki-config.yaml,target=/etc/loki/local-config.yaml \
-p 3100:3100 grafana/loki:2.9.2


===================================
验证
===================================
http://localhost:3100/loki/api/v1/labels
//{"status":"success"}


http://localhost:3100/api/prom/label
//{}

