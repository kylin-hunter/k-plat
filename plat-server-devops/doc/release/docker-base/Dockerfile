FROM centos:7.9.2009
MAINTAINER bijian <250917500@qq.com>
# set timezone
ENV  TIME_ZONE Asia/Shanghai
RUN ln -sf /usr/share/zoneinfo/${TIME_ZONE} /etc/localtime

#OS
RUN yum install -y wget
RUN yum install -y unzip zip
#install jdk
CMD echo "创建文件夹"
RUN mkdir /opt/app
RUN mkdir /opt/jdk8
# 这里oracle官方下载不可用了，本地引入jdk
#RUN wget --no-check-certificate --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" -P /var/tmp/jdk http://download.oracle.com/otn-pub/java/jdk/8u171-b11/512cd62ec5174c3487ac17c61aaa89e8/jdk-8u171-linux-x64.tar.gz
CMD echo "复制文件"
COPY ./jdk-8u301-linux-x64.tar.gz /opt/jdk8/

CMD echo "安装java"
RUN tar xzf /opt/jdk8/jdk-8u301-linux-x64.tar.gz -C /opt/jdk8 && rm -rf /opt/jdk8/jdk-8u301-linux-x64.tar.gz
#env
CMD echo "配置java环境变量"
ENV JAVA_HOME /opt/jdk8/jdk1.8.0_301
ENV CLASSPATH $JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
ENV PATH $PATH:$JAVA_HOME/bin
CMD echo "java环境配置完成"

WORKDIR /opt/app
