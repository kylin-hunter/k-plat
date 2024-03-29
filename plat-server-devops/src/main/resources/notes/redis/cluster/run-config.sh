#!/bin/bash
set -x
set -e
for port in $(seq 1 6); \
do \
mkdir -p /opt/redis/node-${port}/conf
touch /opt/redis/node-${port}/conf/redis.conf
cat << TTT > /opt/redis/node-${port}/conf/redis.conf
protected-mode no
port 736${port}
masterauth 123456
requirepass 123456
cluster-enabled yes
cluster-config-file nodes.conf
cluster-node-timeout 5000
cluster-announce-ip 47.94.98.98
cluster-announce-port 736${port}
cluster-announce-bus-port 1736${port}
appendonly yes
TTT
done



