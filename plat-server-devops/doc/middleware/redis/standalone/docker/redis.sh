docker stop redis && docker rm redis

docker run -d   --name redis \
-p 6379:6379 \
--network kylin-net  \
-v ./redis.conf:/etc/redis/redis.conf \
-v /Users/bijian/software/redis/singleton/data:/data \
redis:6.2.13 \
redis-server /etc/redis/redis.conf