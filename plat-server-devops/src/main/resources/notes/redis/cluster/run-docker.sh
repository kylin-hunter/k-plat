#!/bin/bash
for port in $(seq 1 6); \
do \
docker run --privileged=true --name redis-node${port} --restart=always \
-p 736${port}:736${port} -p 1736${port}:1736${port} \
-v /opt/redis/node-${port}/data:/data \
-v /opt/redis/node-${port}/conf/redis.conf:/etc/redis/redis.conf \
-d redis:6.2.2 redis-server /etc/redis/redis.conf
done






