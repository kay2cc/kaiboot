package top.kaiccc.kaiboot.taobao.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * @author kaiccc
 * @date 2019-01-07 14:36
 */
@Slf4j
public class ImgUploadThread extends Thread {

    private String imgPath;
    private String zipPath;
    private static final String BD_SHELL = "/home/www/bd.sh";

    @Override
    public void run(){
        log.info("ImgUploadThread start ");

        File zipFile;
        if (FileUtil.exist(zipPath)){
            zipFile = FileUtil.file(zipPath);
        }else {
            log.info("压缩文件不存在，{}， 开始压缩", zipPath);
            zipFile = ZipUtil.zip(imgPath, zipPath);
            log.info("压缩完成，{}， 开始上传", zipFile.getPath());
        }

        log.info("开始上传到百度 {}", zipFile.getPath());
        String cmd = StrUtil.format("sh {} {}", BD_SHELL, zipFile);

        String rest = RuntimeUtil.execForStr(cmd);

        log.info("上传命令已发送，{} ，", cmd, rest);
    }

    public ImgUploadThread(String imgPath, String zipPath) {
        this.imgPath = imgPath;
        this.zipPath = zipPath;
    }
}
