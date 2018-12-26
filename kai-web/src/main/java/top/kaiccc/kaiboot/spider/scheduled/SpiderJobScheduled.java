package top.kaiccc.kaiboot.spider.scheduled;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.kaiccc.kaiboot.common.enums.ESpiderType;
import top.kaiccc.kaiboot.spider.entity.SpiderConfig;
import top.kaiccc.kaiboot.spider.job.Mp4BaSpider;
import top.kaiccc.kaiboot.spider.job.SMZDMSpider;
import top.kaiccc.kaiboot.spider.repository.SpiderConfigRepository;
import top.kaiccc.kaiboot.spider.repository.SpiderRecordRepository;
import top.kaiccc.kaiboot.spider.service.SpiderPipelineService;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 爬虫任务定时器
 * @author kaiccc
 * @date 2018-10-11 09:51
 */
@Component
@Slf4j
public class SpiderJobScheduled {

    @Autowired
    private SpiderConfigRepository configRepository;
    @Autowired
    private SpiderRecordRepository recordRepository;
    @Autowired
    private SpiderPipelineService spiderPipelineService;

    @Scheduled(cron = "35 0 10,17,18,19,20 * * ?")
    public void mp4BaScheduled() {
        log.info(" mp4BaScheduled start !!!");
        SpiderConfig mp4baConfig = configRepository.findAllByTypeIs(ESpiderType.mp4ba.toString()).get(0);

        runSpider(mp4baConfig.getUrl(), new Mp4BaSpider(mp4baConfig));
    }

    @Scheduled(cron = "20 5 0/1 * * ? ")
    public void smzdmScheduled() {
        log.info(" smzdmScheduled start !!!");

        List<SpiderConfig> smzdmConfigList = configRepository.findAllByTypeIs(ESpiderType.smzdm.toString());

        for (SpiderConfig config : smzdmConfigList) {
            try {
                runSpider(StrUtil.format(config.getUrl(),
                        URLEncoder.encode(config.getSearchKey(), "UTF-8")),
                        new SMZDMSpider(config));
            } catch (UnsupportedEncodingException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    private void runSpider(String url, PageProcessor pageProcessor) {
        Spider.create(pageProcessor)
                .addPipeline(spiderPipelineService)
                .addUrl(url)
                //开启5个线程抓取
                .thread(3)
                //启动爬虫(同步)
                .start();
    }
}