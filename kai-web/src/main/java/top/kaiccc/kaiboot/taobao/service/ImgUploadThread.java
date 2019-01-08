package top.kaiccc.kaiboot.taobao.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
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
    private String zipPath;

    @Override
    public void run(){
        log.info("ImgUploadThread start ");
        String zipName = StrUtil.format("{}_{}.zip",
                                        DateUtil.format(new Date(), "yyyyMMdd"),
                                        System.currentTimeMillis());

        File zipFile;

        if(StrUtil.isEmpty(zipPath)){
            zipFile = ZipUtil.zip(imgPath, tempPath + File.separator + zipName);
            log.info("压缩完成，{}， 开始上传", zipFile.getPath());
        }else {
            zipFile = FileUtil.file(zipPath);
        }

        log.info("开始断点续传上传 {}", zipFile.getPath());
        String cloudPath = storageService.rbu(zipFile.getPath(), zipName);

        log.info("ImgUploadThread end {}, \n下载地址：{}" , zipFile.getPath(), cloudPath);
    }

    public ImgUploadThread(QiniuCloudStorageService storageService, String imgPath, String tempPath, String zipPath) {
        this.storageService = storageService;
        this.imgPath = imgPath;
        this.tempPath = tempPath;
        this.zipPath = zipPath;
    }
}
