docker exec -it redis-node1 bash

# 安装集群
redis-cli -a 123456 --cluster create \
127.0.0.1:7361 127.0.0.1:7362 \
127.0.0.1:7363 127.0.0.1:7364 \
127.0.0.1:7365 127.0.0.1:7366 --cluster-replicas 1


# 进入集群
redis-cli -c -a 123456 -h 127.0.0.1 -p 7361

# 查看集群节点
cluster nodes

# 查看集群状态
cluster info
# 查看某个key的槽位
cluster keyslot a
# 查看槽位下的key数量
cluster countkeysinslot 15495
# 查看指定槽位、指定数量的key
cluster getkeysinslot 15495 3
#槽位分部信息
CLUSTER SLOTS
