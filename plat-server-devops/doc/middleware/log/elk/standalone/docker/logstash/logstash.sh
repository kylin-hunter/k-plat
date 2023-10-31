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

docker run -itd --name logstash \
--net kylin-net \
-e "ELASTICSEARCH_HOSTS=http://106.12.159.165:9200" \
-v /Users/bijian/Documents/workspace_gitee/k-plat/logs:/data/logs/kplat \
-v ./config/logstash.yml:/usr/share/logstash/config/logstash.yml \
-v ./config/jvm.options:/usr/share/logstash/config/jvm.options \
-v ./pipeline/logstash.conf:/usr/share/logstash/pipeline/logstash.conf \
logstash:7.17.13
