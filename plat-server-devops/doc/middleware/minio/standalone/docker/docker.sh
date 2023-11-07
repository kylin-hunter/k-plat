===============================================================================
文档
===============================================================================
https://min.io/docs/minio/container/index.html?ref=docs-redirect

https://github.com/minio/minio/blob/RELEASE.2023-09-07T02-05-02Z/docs/docker/README.md?plain=1

===============================================================================
安装
===============================================================================

docker pull minio/minio:RELEASE.2023-09-07T02-05-02Z
docker volume create minio-data
# -d --restart=always \

docker stop minio && docker rm minio

docker run -p 9000:9000 -p 9001:9001 \
 --network  kylin-net \
 --network-alias milvus-minio \
 --name minio \
 -e "MINIO_ROOT_USER=minioadmin" \
 -e "MINIO_ROOT_PASSWORD=minioadmin" \
 -v minio-data:/data \
 minio/minio:RELEASE.2023-09-07T02-05-02Z server \
 /data --console-address ":9001"
