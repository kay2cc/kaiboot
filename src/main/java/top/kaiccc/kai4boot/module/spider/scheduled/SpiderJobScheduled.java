package top.kaiccc.kai4boot.module.spider.scheduled;

import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.kaiccc.kai4boot.common.enums.ESpiderType;
import top.kaiccc.kai4boot.module.spider.entity.SpiderConfig;
import top.kaiccc.kai4boot.module.spider.job.Mp4BaSpider;
import top.kaiccc.kai4boot.module.spider.job.SMZDMSpider;
import top.kaiccc.kai4boot.module.spider.service.ISpiderConfigService;
import top.kaiccc.kai4boot.module.spider.service.impl.SpiderPipelineServiceImpl;
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
public class SpiderJobScheduled {

    @Autowired
    private ISpiderConfigService configService;
    @Autowired
    private SpiderPipelineServiceImpl spiderPipelineService;

    private static final Log log = LogFactory.get();

    @Scheduled(cron = "35 0 10,17,18,19,20 * * ?")
    public void mp4BaScheduled(){
        log.info(" mp4BaScheduled start !!!");
        SpiderConfig mp4baConfig = configService.getOne(new QueryWrapper<SpiderConfig>()
                                                .eq("type" , ESpiderType.mp4ba.toString()));

        runSpider(mp4baConfig.getUrl(), new Mp4BaSpider(mp4baConfig));
    }


//    @Scheduled(cron = "0 */1 * * * ?")
    @Scheduled(cron = "20 5 0/1 * * ? ")
    public void smzdmScheduled(){
        log.info(" smzdmScheduled start !!!");
        List<SpiderConfig> smzdmConfigList = configService.list(new QueryWrapper<SpiderConfig>()
                                                                .eq("type" , ESpiderType.smzdm.toString()));

        for (SpiderConfig config : smzdmConfigList){
            try {
                runSpider(StrUtil.format(config.getUrl(), URLEncoder.encode(config.getSearchKey(), "UTF-8")), new SMZDMSpider(config));
            } catch (UnsupportedEncodingException e) {
                log.error(e);
            }
        }

    }

    private void runSpider(String url, PageProcessor pageProcessor){
        Spider.create(pageProcessor)
                .addPipeline(spiderPipelineService)
                .addUrl(url)
                //开启5个线程抓取
                .thread(1)
                //启动爬虫(同步)
                .run();
    }


}
