package top.kaiccc.kaiboot.spider.scheduled;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.kaiccc.kaiboot.common.enums.ESpiderType;
import top.kaiccc.kaiboot.spider.entity.SpiderConfig;
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
    @Value("${spider.scheduled}")
    private boolean scheduledEnabled;

    @Scheduled(cron = "20 5 0/1 * * ? ")
    public void smzdmScheduled() {
        log.info(" smzdmScheduled start !!! {}", scheduledEnabled);
        if (scheduledEnabled){
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