#!/bin/bash

ls_date=`date +%Y%m%d_%H%M`
home_file="/home/www"
qshell="/home/www/qshell_linux_x64"
zip_path=${home_file}/ali_${ls_date}_bak.zip

echo "****************** H2 DB Backup To QiuNiu Start ${ls_date} ******************"
echo "qshell account"
${qshell} account K-PpsApkNeManR_6VaWRMcM9xxIhbQuV3KlzTQDQ nEZv0um4wJeJ89hclzPoa_XfHGKxfrazGaH3quDZ ali_kai

echo "zip /home/www/ali_${ls_date}_bak.zip"
zip -r ${zip_path} ${home_file}/h2/
echo "mv.db rbu start "
${qshell} rput kai-bj-bucket ${ls_date}_bak.zip ${zip_path}

echo "****************** H2 DB Backup To QiuNiu stop ${ls_date} ******************"