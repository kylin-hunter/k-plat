docker network create kylin-net --driver bridge
docker stop mysql8 && docker rm mysql8

docker volume create mysql8_data
docker volume create mysql8_log
docker run \
 --network  kylin-net \
 -p 3306:3306 --name mysql8 \
 -v ./conf:/etc/mysql/conf.d \
 -v mysql8_log:/var/log/mysql \
 -v mysql8_data:/var/lib/mysql \
 -e MYSQL_ROOT_PASSWORD=root \
 -d mysql:8.0.33 \
 --lower-case-table-names=1