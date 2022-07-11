#!/bin/bash
docker build -t k-plat/k-plat-base:v1 .
## docker tag acg-kplat/kplat-server-task-center:v1 iregistry.kplat-int.com/acg-kplat/kplat-server-task-center:v1

docker rmi  k-plat/k-plat-base:v1

