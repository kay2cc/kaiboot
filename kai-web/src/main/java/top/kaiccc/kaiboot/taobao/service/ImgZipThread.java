package top.kaiccc.kaiboot.taobao.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
import lombok.extern.slf4j.Slf4j;
import top.kaiccc.kaiboot.common.utils.WxMsgUtils;

import java.io.File;
import java.util.Date;

/**
 * @author kaiccc
 * @date 2019-01-07 14:36
 */
@Slf4j
public class ImgZipThread extends Thread {

    private String tempPath;
    private String sendkey;
    private String imgPath;

    @Override
    public void run(){
        log.info("ImgZipThread start ");
        String zipName = StrUtil.format("{}_{}.zip",
                                        DateUtil.format(new Date(), "yyyyMMdd"),
                                        System.currentTimeMillis());
        String zipPath = tempPath + File.separator + zipName;

        File zipFile = ZipUtil.zip(imgPath, zipPath);

        log.info("压缩完成，{}", zipFile.getPath());

        WxMsgUtils.sendMessage(sendkey, "图片压缩情况",
                StrUtil.format("### 图片压缩情况" +
                                        "### 文件名：{}" +
                                        "时间：{}"
                , zipFile.getPath(), DateUtil.now()));
        log.info("ImgUploadThread end {}, ZIP包位置：{}" , zipFile.getPath());
    }

    public ImgZipThread(String imgPath, String tempPath, String sendkey) {
        this.imgPath = imgPath;
        this.tempPath = tempPath;
        this.sendkey = sendkey;
    }
}
