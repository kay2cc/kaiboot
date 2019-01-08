package top.kaiccc.kaiboot.taobao.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.StreamProgress;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import top.kaiccc.kaiboot.taobao.entity.Pics;
import top.kaiccc.kaiboot.taobao.repository.PicsRepository;

import java.io.File;
import java.util.List;

/**
 * @author kaiccc
 * @date 2019-01-07 14:36
 */
@Slf4j
public class ImgDownloadThread extends Thread {

    private PicsRepository picsRepository;

    @Override
    public void run(){
        log.info("ImgDownloadThread start ");
        List<Pics> picsList = picsRepository.findByCompleted(false);

        int count = 1;
        for (Pics pics : picsList){
            File imgPath = FileUtil.file(pics.getFilePath());

            if (!FileUtil.exist(imgPath)){
                imageDownload(pics.getPath(), imgPath, pics.getTitle(), pics.getId());
            }

            log.info("图片总数：{}，已下载：{}", picsList.size(), count);
            count++;
        }
        log.info("ImgDownloadThread end ");
    }

    /**
     * 图片下载
     * @param imgUrl
     * @param imgPath
     * @param title
     */
    private void imageDownload(String imgUrl, File imgPath, String title, String id) {
        try {
            HttpUtil.downloadFile(imgUrl, imgPath, 5000, new StreamProgress() {
                @Override
                public void start() {
                    log.info("开始下载 >>> {}", title);
                }

                @Override
                public void progress(long progressSize) {
                }

                @Override
                public void finish() {
                    log.info("下载完成 >>> {} {}", id, title);
                }
            });
        }catch (Exception e){
            log.error("图片下载异常: ", e);
        }
        picsRepository.updateCompletedById(id, true);
        log.info("{} {} 已更新状态", title, id);
    }

    public ImgDownloadThread(PicsRepository picsRepository) {
        this.picsRepository = picsRepository;
    }
}
