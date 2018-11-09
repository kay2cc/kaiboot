package top.kaiccc.kai4boot.job;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import top.kaiccc.kai4boot.common.enums.ESpiderType;
import top.kaiccc.kai4boot.entity.SpiderConfig;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;
import java.util.Map;

/**
 * 什么值得买  爬虫分析
 * @author kaiccc
 * @date 2018-11-02 13:57
 */
@Slf4j
public class SMZDMSpider implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(3000);
    private SpiderConfig smzdmConfig;

    @Override
    public void process(Page page) {
        List<String> feedList = page.getHtml().$(".feed-block.z-hor-feed").all();
        List<Map<String, Object>> smzdmList = CollUtil.newArrayList();
        for (String feed : feedList){
            Map<String, Object> map = CollUtil.newHashMap();

            Html feedHtml = new Html(feed);
            Selectable imgSelect = feedHtml.$(".z-feed-img");

            map.put("type", ESpiderType.smzdm.toString());
            map.put("title", imgSelect.xpath("//img/@alt").toString());
            map.put("price", StrUtil.trim(feedHtml.$(".z-highlight", "text").toString()));
            map.put("imageUrl", "http://" + StrUtil.subSuf(imgSelect.xpath("//img/@src").toString(),2));
            map.put("url",  StrUtil.trim(imgSelect.xpath("//a/@href").toString()));
            map.put("wxKey", smzdmConfig.getWxPushKey());
            map.put("searchKey", smzdmConfig.getSearchKey());
            smzdmList.add(map);
        }
        page.putField("spiderList", smzdmList);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public SMZDMSpider(SpiderConfig smzdmConfig) {
        this.smzdmConfig = smzdmConfig;
    }

    public SMZDMSpider() {
    }
}
