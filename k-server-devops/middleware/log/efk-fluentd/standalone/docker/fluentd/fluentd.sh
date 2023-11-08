===================================
资料
===================================
[GitHub] https://github.com/fluent/fluentd
[Doc] https://docs.fluentd.org/configuration/config-file-yaml
[Example] https://github.com/fluent/fluentd/tree/master/example
https://docs.fluentd.org/deployment/plugin-management#if-using-td-agent,-use-/etc/td-agent/plugin

===================================
安装
===================================

docker pull fluentd:v1.16-1

docker stop fluentd && docker rm fluentd

docker run -d --name fluentd \
--net kylin-net \
-v ./config:/fluentd/etc \
-v /Users/bijian/Documents/workspace_gitee/k-plat/logs:/data/logs/kplat \
kplat/k-fluentd:v1.16-1



===启动一个默认容器测试===
docker stop filebeat2 && docker rm filebeat2

docker run -d --name filebeat2 \
--net kylin-net \
-v /Users/bijian/Documents/workspace_gitee/k-plat/logs:/data/logs/kplat \
elastic/filebeat:7.17.13

