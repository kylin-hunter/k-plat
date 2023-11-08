#!/usr/bin/env bash
# exit whenever encounter errors
set -e
# print commands  before execute (for debug) set+x
set -x


sed -i "s/REDIS_IP/${REDIS_IP}/g" /opt/redis/conf/redis.conf

redis-server /opt/redis/conf/redis.conf