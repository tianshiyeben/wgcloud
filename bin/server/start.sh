#!/bin/sh
RUN_NAME="wgcloud-server-release.jar"
export LANG="en_US.UTF-8"
PRG=$0
APPDIRFILE=`dirname "$PRG"`
cd $APPDIRFILE
echo $APPDIRFILE/$RUN_NAME
PID=`ps -ef|grep $RUN_NAME|grep -v grep|awk '{printf $2}'`
echo $PID
if [ ! -n "$PID" ];
then
   echo "wgcloud-server程序开始启动"
else
   echo "已启动,杀掉进程后重启"
   echo $PID
   kill -9 $PID
fi
nohup java  -server  -Xms256m -Xmx512m  -jar $RUN_NAME >/dev/null 2>&1 &
