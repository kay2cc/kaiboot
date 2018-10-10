package top.kaiccc.kai4boot.module.spider.job;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import top.kaiccc.kai4boot.module.spider.service.ISpiderConfigService;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * 给我家QQ 撸点吃点
 *
 * @author kaic
 */
public class SMZDMSpiderJob implements PageProcessor {

    @Autowired
    private ISpiderConfigService configService;

    private final String SMZDM_CATFOOD_URL = "http://search.smzdm.com/?c=faxian&s=%E7%8C%AB%E7%A0%82&v=b";
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    private static final Log log = LogFactory.get();

    @Override
    public void process(Page page) {
        List<String> feedList = page.getHtml().$(".feed-block.z-hor-feed").all();
        for (String feed : feedList){
            Html feedHtml = new Html(feed);
            Selectable imgSelect = feedHtml.$(".z-feed-img");

      /*      CatFood catFood = new CatFood(
                    imgSelect.xpath("//img/@alt").toString(),
                    feedHtml.$(".z-highlight", "text").toString(),
                    StrUtil.subSuf(imgSelect.xpath("//img/@src").toString(),2),
                    imgSelect.xpath("//a/@href").toString()
            );

            if (ObjectUtil.isNull(App.timedCache.get(catFood.getTitle()))){
                App.timedCache.put(catFood.getTitle() , catFood);
                String desp = StrUtil.format("\n### 猫粮：{} \n### 价格：{} \n ![img](http://{}) \r\n## [点我查看详情]({}) \n \n\n发送时间：{}",
                                                catFood.getTitle(), catFood.getPrice(), catFood.getImgUrl(), catFood.getUrl(), DateUtil.now());
                WxUtile.sendMessage(catFood.getTitle(), desp);
            }*/
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public void run() {
        Spider.create(new SMZDMSpiderJob())
                .addUrl(SMZDM_CATFOOD_URL)
                //开启5个线程抓取
                .thread(3)
                //启动爬虫
                .run();
    }
}
