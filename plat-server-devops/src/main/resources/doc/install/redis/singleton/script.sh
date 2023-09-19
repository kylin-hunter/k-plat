docker run -d --restart=always --name redis6 \
-p 6379:6379 \
-v /Users/bijian/software/redis/singleton/conf/redis.conf:/etc/redis/redis.conf \
-v /Users/bijian/software/redis/singleton/data:/data \
redis:6.2.13 \
redis-server /etc/redis/redis.conf
=============


docker run -d   --name redis6 \
-p 6379:6379 \
-v /Users/bijian/software/redis/singleton/conf/redis.conf:/etc/redis/redis.conf \
-v /Users/bijian/software/redis/singleton/data:/data \
redis:6.2.13 \
redis-server /etc/redis/redis.conf
=============

docker exec -it redis6 /bin/bash