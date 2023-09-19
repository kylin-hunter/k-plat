docker pull minio/minio:RELEASE.2023-09-07T02-05-02Z

docker run -p 9000:9000 -p 9090:9090 \
 --name minio \
 -d --restart=always \
 -e "MINIO_ROOT_USER=minioadmin" \
 -e "MINIO_ROOT_PASSWORD=minioadmin" \
 -v /Users/bijian/software/minio/data:/data \
 minio/minio:RELEASE.2023-09-07T02-05-02Z server \
 /data --console-address ":9090"
