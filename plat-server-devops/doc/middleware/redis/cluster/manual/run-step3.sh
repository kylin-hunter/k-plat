redis-cli -a 123456 --cluster create \
192.168.0.105:7361 192.168.0.105:7362 \
192.168.0.105:7363 192.168.0.105:7364 \
192.168.0.105:7365 192.168.0.105:7366 --cluster-replicas 1