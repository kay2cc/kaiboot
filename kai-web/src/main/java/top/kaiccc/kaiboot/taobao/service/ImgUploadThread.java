package top.kaiccc.kaiboot.taobao.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
import lombok.extern.slf4j.Slf4j;
import top.kaiccc.kaiboot.s3.cloud.QiniuCloudStorageService;

import java.io.File;
import java.util.Date;

/**
 * @author kaiccc
 * @date 2019-01-07 14:36
 */
@Slf4j
public class ImgUploadThread extends Thread {

    private QiniuCloudStorageService storageService;
    private String imgPath;
    private String tempPath;

    @Override
    public void run(){
        log.info("ImgUploadThread start ");
        String zipName = StrUtil.format("{}_{}.zip",
                                        DateUtil.format(new Date(), "yyyyMMdd"),
                                        System.currentTimeMillis());

        File zipFile = ZipUtil.zip(imgPath, tempPath + File.separator + zipName);
        String cloudPath = storageService.upload(IoUtil.toStream(zipFile), zipName);

        log.info("ImgUploadThread end {}, {}" , zipFile, cloudPath);
    }

    public ImgUploadThread(QiniuCloudStorageService storageService, String imgPath, String tempPath) {
        this.storageService = storageService;
        this.imgPath = imgPath;
        this.tempPath = tempPath;
    }
}
