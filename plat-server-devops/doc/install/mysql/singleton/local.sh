docker run \
 -p 3306:3306 --name mysql8 \
 -v /Users/bijian/software/mysql/docker/conf/mysql.cnf:/etc/mysql/conf.d/mysql.cnf \
 -v /Users/bijian/software/mysql/docker/log:/var/log/mysql \
 -v /Users/bijian/software/mysql/docker/data:/var/lib/mysql \
 -e MYSQL_ROOT_PASSWORD=root \
 -d mysql:8.0.33 \
 --lower-case-table-names=1