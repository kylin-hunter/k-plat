===================================
资料
===================================
https://www.elastic.co/guide/en/logstash/current/index.html


===================================
自定义镜像
===================================
FROM docker.elastic.co/logstash/logstash:7.17.13
RUN rm -f /usr/share/logstash/pipeline/logstash.conf
COPY pipeline/ /usr/share/logstash/pipeline/
COPY config/ /usr/share/logstash/config/
===================================
安装
===================================

docker pull logstash:7.17.13


docker stop logstash && docker rm logstash

docker run -d --name logstash \
--net kylin-net \
-v /Users/bijian/Documents/workspace_gitee/k-plat/logs:/data/logs/kplat \
-v ./config/logstash.yml:/usr/share/logstash/config/logstash.yml \
-v ./config/jvm.options:/usr/share/logstash/config/jvm.options \
-v ./pipeline/kplat.conf:/usr/share/logstash/pipeline/kplat.conf \
logstash:7.17.13

===启动一个默认容器测试===
docker stop logstash2 && docker rm logstash2

docker run -itd --name logstash2 \
--net kylin-net \
logstash:7.17.13
