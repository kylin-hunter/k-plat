docker exec -it redis-node1 bash

# 安装集群
redis-cli -a kplat123456 --cluster create \
47.94.98.98:7361 47.94.98.98:7362 \
47.94.98.98:7363 47.94.98.98:7364 \
47.94.98.98:7365 47.94.98.98:7366 --cluster-replicas 1


# 进入集群
redis-cli -c -a kplat123456 -h 47.94.98.98 -p 7361

# 查看集群节点
cluster nodes

# 查看集群状态
cluster info
# 查看某个key的槽位
 cluster keyslot 9223372036398075807
# 查看槽位下的key数量
cluster countkeysinslot 14923

# 查看指定槽位、指定数量的key
cluster getkeysinslot 14923 3
