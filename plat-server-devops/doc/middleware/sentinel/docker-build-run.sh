docker build --build-arg SENTINEL_VERSION=1.8.6 -t k-plat/sentinel-dashboard:v1.8.6 .
docker run -d --name sentinel -p 8080:8080    k-plat/sentinel-dashboard:v1.8.6
docker start sentinel
docker stop  sentinel

