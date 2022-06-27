#!/bin/bash
docker build -t k-plat/k-plat-base:v1 .
## docker tag acg-cskb/cskb-server-task-center:v1 iregistry.baidu-int.com/acg-cskb/cskb-server-task-center:v1

docker rmi  k-plat/k-plat-base:v1

