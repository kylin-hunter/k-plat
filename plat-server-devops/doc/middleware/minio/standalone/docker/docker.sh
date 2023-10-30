===============================================================================
文档
===============================================================================
https://min.io/docs/minio/container/index.html?ref=docs-redirect


===============================================================================
安装
===============================================================================

docker pull minio/minio:RELEASE.2023-09-07T02-05-02Z
# -d --restart=always \
docker run -p 9000:9000 -p 9090:9090 \
 --name minio \
 -e "MINIO_ROOT_USER=minioadmin" \
 -e "MINIO_ROOT_PASSWORD=minioadmin" \
 -v /Users/bijian/software/minio/data:/data \
 minio/minio:RELEASE.2023-09-07T02-05-02Z server \
 /data --console-address ":9090"
