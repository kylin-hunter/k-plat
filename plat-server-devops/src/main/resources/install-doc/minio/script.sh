# standalone
https://min.io/docs/minio/container/index.html?ref=docs-redirect

mkdir -p ~/minio/data

docker run \
   -p 9000:9000 \
   -p 9090:9090 \
   --name minio \
   -v ~/minio/data:/data \
   -e "MINIO_ROOT_USER=ROOTNAME" \
   -e "MINIO_ROOT_PASSWORD=CHANGEME123" \
   quay.io/minio/minio server /data --console-address ":9090"

mkdir -p ${HOME}/minio/data

docker run \
   -p 9000:9000 \
   -p 9090:9090 \
   --user $(id -u):$(id -g) \
   --name minio1 \
   -e "MINIO_ROOT_USER=ROOTUSER" \
   -e "MINIO_ROOT_PASSWORD=CHANGEME123" \
   -v ${HOME}/minio/data:/data \
   quay.io/minio/minio server /data --console-address ":9090"
==============================
docker pull minio/minio:RELEASE.2023-09-07T02-05-02Z

docker run -p 9000:9000 -p 9090:9090 \
 --name minio \
 -d --restart=always \
 -e "MINIO_ROOT_USER=minioadmin" \
 -e "MINIO_ROOT_PASSWORD=minioadmin" \
 -v /Users/bijian/software/minio/data:/data \
 minio/minio:RELEASE.2023-09-07T02-05-02Z server \
 /data --console-address ":9090"
