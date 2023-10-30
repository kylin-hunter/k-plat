docker run -d -name kafka-ui -it -p 8080:8080 \
--network  kylin-net \
-e DYNAMIC_CONFIG_ENABLED=true provectuslabs/kafka-ui



http://localhost:8080