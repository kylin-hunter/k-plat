APP_NAME='plat-server-eureka'
APP_DIR="/Users/bijian/workspace_gitee/k-plat/${APP_NAME}/build/libs"
JAVA_HEAP_OPTS=' -Xms100m -Xmx256m '
function start_server(){
    java ${JAVA_DEFAULT_OPTS}  -jar ${APP_DIR}/${APP_NAME}.jar 2>&1
}

if [ -f APP_DIR/pid ]; then
    pid_id=`cat $DIR/pid`
    count=`ps aux | grep "$pid_id" | grep -v grep | wc -l`
    if [ -n "$pid_id" -a $count -gt 0 ];then
        echo "${APP_DIR}/${APP_NAME}.jar is already running"
    else
        rm APP_DIR/pid;
        start_server;
    fi
else
     start_server;
fi

java  -jar