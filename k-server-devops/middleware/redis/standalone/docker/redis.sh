docker stop redis && docker rm redis
docker volume create redis-data
docker run -d   --name redis \
-p 6379:6379 \
--network kylin-net  \
-v ./redis.conf:/etc/redis/redis.conf \
-v redis-data:/data \
redis:6.2.13 \
redis-server /etc/redis/redis.conf