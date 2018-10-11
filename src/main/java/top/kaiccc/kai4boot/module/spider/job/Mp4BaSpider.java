package top.kaiccc.kai4boot.module.spider.job;

import cn.hutool.core.collection.CollUtil;
import top.kaiccc.kai4boot.common.enums.ESpiderType;
import top.kaiccc.kai4boot.module.spider.entity.SpiderConfig;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;
import java.util.Map;

/**
 * Mp4Ba 爬虫解析
 *
 * @author kaiccc
 * @date 2018-10-08 10:12
 */
public class Mp4BaSpider implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    private SpiderConfig mp4baConfig;

    @Override
    public void process(Page page) {
        Selectable tableList = page.getHtml().$(".tbody .alt1");
        List<Map<String, Object>> spiderList = CollUtil.newArrayList();

        if (tableList.links().regex(mp4baConfig.getSearchKey()).match()) {
            // 列表页
            page.addTargetRequests(tableList.links().regex(mp4baConfig.getSearchKey()).all());
        } else {
            // 详情页
            Map<String, Object> map = CollUtil.newHashMap();
            map.put("type", ESpiderType.mp4ba.toString());
            map.put("title", page.getHtml().$(".folder.last", "text").get());
            map.put("url", page.getUrl().get());
            map.put("imageUrl", page.getHtml().$(".intro").xpath("/div/img/@src").get());
            map.put("info", page.getHtml().$(".intro").xpath("/div/p[1]/text()").get());
            map.put("wxKey", mp4baConfig.getWxKey());
            spiderList.add(map);
        }
        page.putField("spiderList", spiderList);
    }
    @Override
    public Site getSite() {
        return site;
    }

    public Mp4BaSpider(SpiderConfig mp4baConfig) {
        this.mp4baConfig = mp4baConfig;
    }
    public Mp4BaSpider() {
    }
}
