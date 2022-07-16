#!/bin/bash



docker run -it --rm  k-plat/k-plat-base:v1 /bin/bash

docker run -it --rm  -p 8202:8202 -p 8302:8302   -e DUBBO_IP_TO_REGISTRY=192.168.31.155 \
 -e DUBBO_PORT_TO_REGISTRY=8302 -e DUBBO_PORT_TO_BIND=8302 \
 -e APOLLO_CONFIG_SERVICE=http://127.0.0.1:8778 \
 iregistry.kplat-int.com/acg-kplat/kplat-server-task-center:v1


docker run -it --rm  -p 8202:8202 -p 8302:8302   -e DUBBO_IP_TO_REGISTRY=192.168.31.155 \
 -e DUBBO_PORT_TO_REGISTRY=8302 -e DUBBO_PORT_TO_BIND=8302 \
 iregistry.kplat-int.com/acg-kplat/kplat-server-task-center:v1

