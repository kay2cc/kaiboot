package top.kaiccc.kai4boot.spider.service;


import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.kaiccc.kai4boot.common.enums.ESpiderType;
import top.kaiccc.kai4boot.common.utils.WxMsgUtils;
import top.kaiccc.kai4boot.spider.entity.SpiderRecord;
import top.kaiccc.kai4boot.spider.repository.SpiderConfigRepository;
import top.kaiccc.kai4boot.spider.repository.SpiderRecordRepository;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;
import java.util.Map;

/**
 * 爬虫数据处理
 * @author kaiccc
 * @date 2018-10-11 09:47
 */
@Component
public class SpiderPipelineService implements Pipeline {

    private static final Log log = LogFactory.get();

    @Autowired
    private SpiderRecordRepository recordRepository;
    @Autowired
    private SpiderConfigRepository configRepository;

    @Override
    public void process(ResultItems result, Task task) {
        List<Map<String, String>> spiderList = result.get("spiderList");
        for (Map<String, String> map : spiderList){
            dataProcess(map);
        }
    }

    private void dataProcess(Map<String, String> result){
        try {
            SpiderRecord spiderRecord = new SpiderRecord(result.get("type"), StrUtil.trim(result.get("title")), result.get("url"));

            if (StrUtil.isBlank(spiderRecord.getType()) || StrUtil.isBlank(spiderRecord.getTitle())){
                return;
            }
            spiderRecord.setDetail(editWxPushMsg(spiderRecord, result));

            long count = recordRepository.countByUrlIs(spiderRecord.getUrl());
            if (count == 0){
                try {
                    recordRepository.save(spiderRecord);
                }catch (Exception e){
                    log.error(e);
                }
                sendMessage(spiderRecord, result.get("wxKey"));
            }
        }catch (Exception e){
            log.error(e);
        }
    }

    private void sendMessage(SpiderRecord record, String sendkey) {
        WxMsgUtils.sendMessage(sendkey, StrUtil.subPre(record.getTitle(), 75), record.getDetail());
    }

    private String editWxPushMsg(SpiderRecord record, Map<String, String> result){

        if (StrUtil.equalsIgnoreCase(ESpiderType.mp4ba.toString(), record.getType())){
            return StrUtil.format("\r\r ### 类型：mp4ba \r\r### 电影名：{} \r\r> {} \r\r ![img]({}) \r\r ## [点我查看详情]({}) \r\r `创建时间：{}`\r\r`Id:{}`",
                    record.getTitle(),
                    result.get("info"),
                    result.get("imageUrl"),
                    record.getUrl(),
                    record.getCreateTime(),
                    record.getId());
        } else if (StrUtil.equalsIgnoreCase(ESpiderType.smzdm.toString(), record.getType())){
            return StrUtil.format("\r\r ### 类型：{} \r\r### 商品名称：{} \r\r### 价格：{} \r\r ![img]({}) \r\r## [点我查看详情]({}) \r\r `创建时间：{}` \r\r`Id：{}`",
                    result.get("searchKey"),
                    record.getTitle(),
                    result.get("price"),
                    result.get("imageUrl"),
                    record.getUrl(),
                    record.getCreateTime(),
                    record.getId());
        }
        return null;
    }

}