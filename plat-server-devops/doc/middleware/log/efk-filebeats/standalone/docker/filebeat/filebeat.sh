===================================
资料
===================================
https://www.elastic.co/guide/en/beats/filebeat/7.17/running-on-docker.html
https://www.elastic.co/cn/products/beats/filebeat
===================================
安装
===================================

docker pull elastic/filebeat:7.17.13


docker stop filebeat && docker rm filebeat

docker run -d --name filebeat \
--user=root \
--net kylin-net \
-v /Users/bijian/Documents/workspace_gitee/k-plat/logs:/data/logs/kplat \
-v ./config/filebeat.yml:/usr/share/filebeat/filebeat.yml:ro \
elastic/filebeat:7.17.13 filebeat -e --strict.perms=false




===启动一个默认容器测试===
docker stop filebeat2 && docker rm filebeat2

docker run -d --name filebeat2 \
--net kylin-net \
-v /Users/bijian/Documents/workspace_gitee/k-plat/logs:/data/logs/kplat \
elastic/filebeat:7.17.13

