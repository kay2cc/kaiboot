#!/bin/bash

log_path="/home/www/file/bd.txt"
zip_path=$1
bd_cmd="nohup python -m bypy -v upload ${zip_path} >> ${log_path} 2>&1 &"

ls_date=`date +%Y%m%d_%H%M`
echo -e "上传时间：${ls_date}, zip: ${zip_path}" > ${log_path}
echo -e "\n命令：${bd_cmd} " >> ${log_path}

${bd_cmd}

echo "已执行 ${bd_cmd}"