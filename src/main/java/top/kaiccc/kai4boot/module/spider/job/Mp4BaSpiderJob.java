package top.kaiccc.kai4boot.module.spider.job;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.kaiccc.kai4boot.common.enums.ESpiderType;
import top.kaiccc.kai4boot.module.spider.entity.SpiderConfig;
import top.kaiccc.kai4boot.module.spider.entity.SpiderRecord;
import top.kaiccc.kai4boot.module.spider.service.ISpiderConfigService;
import top.kaiccc.kai4boot.module.spider.service.ISpiderRecordService;
import top.kaiccc.kai4boot.module.wxmsg.utils.WxMsgUtile;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

/**
 * Mp4Ba 推送
 * @author kaiccc
 * @date 2018-10-08 10:12
 */
@Component
public class Mp4BaSpiderJob implements PageProcessor {

    public static SpiderConfig MP4BA_CONFIG ;
    @Autowired
    private ISpiderRecordService recordService;
    @Autowired
    private ISpiderConfigService configService;

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    private static final Log log = LogFactory.get();

    @Override
    public void process(Page page) {
        Selectable tableList = page.getHtml().$(".tbody .alt1");

        if (tableList.links().regex(MP4BA_CONFIG.getSearchKey()).match()){
            // 列表页
            page.addTargetRequests(tableList.links().regex(MP4BA_CONFIG.getSearchKey()).all());
        }else {
            // 详情页

            String imageUrl = page.getHtml().$(".intro").xpath("/div/img/@src").get();
            String info = page.getHtml().$(".intro").xpath("/div/p[1]/text()").get();

            SpiderRecord mp4ba = new SpiderRecord(
                    ESpiderType.mp4ba.toString(),
                    page.getHtml().$(".folder.last", "text").get(),
                    page.getUrl().get()
            );

            SpiderRecord record = recordService.getOne(new QueryWrapper<SpiderRecord>().eq("title", mp4ba.getTitle()));
            if (ObjectUtil.isNull(record)){

                String msg = StrUtil.format("\r\n### 电影名：{} \r\n> {} \r\n\r\n ![img]({}) \r\n ## [点我查看详情]({}) \r\n\r\n\r\n 发送时间：{}",
                                            mp4ba.getTitle(), info, imageUrl, mp4ba.getUrl(), mp4ba.getCreateTime());

                mp4ba.setDetail(msg);
                recordService.save(mp4ba);

                WxMsgUtile.sendMessage(mp4ba, MP4BA_CONFIG.getWxKey());
            }

        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    @Scheduled(cron = "0 */1 * * * ?")
    public void run() {

        Spider.create(new Mp4BaSpiderJob())
                .addUrl(MP4BA_CONFIG.getUrl())
                //开启5个线程抓取
                .thread(3)
                //启动爬虫
                .run();
    }
/*    public static void main(String[] args) {
        Mp4BaSpiderJob job = new Mp4BaSpiderJob();
        job.run();
    }*/
}
