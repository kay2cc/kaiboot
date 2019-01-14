#!/bin/bash

ps -fe|grep kaiboot |grep -v grep
if [ $? -ne 0 ]
then
echo "******************未运行,开始启动******************"
nohup java -jar -Xms128m -Xmx256m /home/www/kaiboot.jar >> /home/www/file/log.txt 2>&1 &
else
echo "******************运行中******************"
fi

