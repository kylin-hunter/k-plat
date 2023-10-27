docker network create kylin-net --driver bridge
docker stop mysql8 && docker rm mysql8
docker run \
 --network  kylin-net \
 -p 3306:3306 --name mysql8 \
 -v /Users/bijian/software/mysql/docker/conf:/etc/mysql/conf.d \
 -v /Users/bijian/software/mysql/docker/log:/var/log/mysql \
 -v /Users/bijian/software/mysql/docker/data:/var/lib/mysql \
 -e MYSQL_ROOT_PASSWORD=root \
 -d mysql:8.0.33 \
 --lower-case-table-names=1