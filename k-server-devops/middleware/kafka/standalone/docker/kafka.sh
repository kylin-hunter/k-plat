docker pull bitnami/kafka:3.3.2


docker network create  kylin-net --driver bridge
docker network ls
docker network inspect  kylin-net

docker run -d --name kafka-server --hostname kafka-server \
    --network  kylin-net \
    -e KAFKA_CFG_NODE_ID=0 \
    -e KAFKA_CFG_PROCESS_ROLES=controller,broker \
    -e KAFKA_CFG_LISTENERS=PLAINTEXT://:9092,CONTROLLER://:9093 \
    -e KAFKA_CFG_LISTENER_SECURITY_PROTOCOL_MAP=CONTROLLER:PLAINTEXT,PLAINTEXT:PLAINTEXT \
    -e KAFKA_CFG_CONTROLLER_QUORUM_VOTERS=0@kafka-server:9093 \
    -e KAFKA_CFG_CONTROLLER_LISTENER_NAMES=CONTROLLER \
    bitnami/kafka:3.3.2





============================================
可以使用以下环境变量通过 Bitnami Apache Kafka Docker 映像轻松设置配置：
=======================================================
ALLOW_PLAINTEXT_LISTENER: 允许使用 PLAINTEXT 监听器。默认值：否。
KAFKA_INTER_BROKER_USER：Apache Kafka 代理间通信用户。默认值：管理员。默认值：用户。
KAFKA_INTER_BROKER_PASSWORD：Apache Kafka 代理间通信密码。默认值：比特纳米。
KAFKA_CERTIFICATE_PASSWORD: 证书密码。没有默认值。
KAFKA_HEAP_OPTS：Apache Kafka 的 Java 堆大小。默认值：-Xmx1024m -Xms1024m。
KAFKA_ZOOKEEPER_PROTOCOL：Zookeeper 连接的身份验证协议。允许的协议：PLAINTEXT、SASL、SSL和SASL_SSL。默认值：纯文本。
KAFKA_ZOOKEEPER_USER：用于 SASL 身份验证的 Apache Kafka Zookeeper 用户。没有默认值。
KAFKA_ZOOKEEPER_PASSWORD：用于 SASL 身份验证的 Apache Kafka Zookeeper 用户密码。没有默认值。
KAFKA_ZOOKEEPER_TLS_KEYSTORE_PASSWORD：Apache Kafka Zookeeper 密钥库文件密码和密钥密码。没有默认值。
KAFKA_ZOOKEEPER_TLS_TRUSTSTORE_PASSWORD：Apache Kafka Zookeeper 信任库文件密码。没有默认值。
KAFKA_ZOOKEEPER_TLS_VERIFY_HOSTNAME：验证 TLS 证书上的 Zookeeper 主机名。默认值：真。
KAFKA_ZOOKEEPER_TLS_TYPE：选择要使用的 TLS 证书格式。允许值：JKS, PEM. 默认值：JKS。
KAFKA_CFG_SASL_ENABLED_MECHANISMS：在客户端、代理间或 Zookeeper 通信中使用 SASL 时允许的机制。允许的值：PLAIN、SCRAM-SHA-256或SCRAM-SHA-512这些值的逗号分隔组合。默认值：普通、SCRAM-SHA-256、SCRAM-SHA-512
KAFKA_CFG_SASL_MECHANISM_INTER_BROKER_PROTOCOL：用于代理间通信的 SASL 机制。没有默认值。
KAFKA_TLS_CLIENT_AUTH：配置 kafka 代理以请求客户端身份验证。允许值：required, requested, none. 默认值：必需。
KAFKA_TLS_TYPE：选择要使用的 TLS 证书格式。允许值：JKS, PEM. 默认值：JKS。
KAFKA_CLIENT_USERS：使用 SASL 进行客户端通信时将创建到 Zookeeper 中的用户。被逗号隔开。默认值：用户
KAFKA_CLIENT_PASSWORDS：在 中指定的用户的密码KAFKA_CLIENT_USERS。被逗号隔开。默认值：bitnami
KAFKA_CFG_MAX_PARTITION_FETCH_BYTES：服务器将返回的每个分区的最大数据量。默认值：1048576
KAFKA_CFG_MAX_REQUEST_SIZE：请求的最大大小（以字节为单位）。默认值：1048576
KAFKA_ENABLE_KRAFT：是否开启Kafka Raft（KRaft）模式。默认值：无
KAFKA_KRAFT_CLUSTER_ID: 使用 Kafka Raft (KRaft) 时的 Kafka 集群 ID。没有默认值。





