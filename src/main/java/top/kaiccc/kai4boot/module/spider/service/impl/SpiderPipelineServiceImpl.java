package top.kaiccc.kai4boot.module.spider.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.kaiccc.kai4boot.common.enums.ESpiderType;
import top.kaiccc.kai4boot.module.spider.entity.SpiderRecord;
import top.kaiccc.kai4boot.module.spider.service.ISpiderConfigService;
import top.kaiccc.kai4boot.module.wxmsg.utils.WxMsgUtils;
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
public class SpiderPipelineServiceImpl implements Pipeline {

    private static final Log log = LogFactory.get();

    @Autowired
    private SpiderRecordServiceImpl recordService;
    @Autowired
    private ISpiderConfigService configService;

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
            SpiderRecord record = recordService.getOne(new QueryWrapper<SpiderRecord>().eq("title", spiderRecord.getTitle()));
            if (ObjectUtil.isNull(record)){
                try {
                    recordService.save(spiderRecord);
                }catch (Exception e){
                    log.error(e);
                }
                WxMsgUtils.sendMessage(spiderRecord, result.get("wxKey"));
            }
        }catch (Exception e){
            log.error(e);
        }
    }

    private String editWxPushMsg(SpiderRecord record, Map<String, String> result){

        if (StrUtil.equalsIgnoreCase(ESpiderType.mp4ba.toString(), record.getType())){

            return StrUtil.format("\r\r### 电影名：{} \r\r> {} \r\r ![img]({}) \r\r ## [点我查看详情]({}) \r\r 创建时间：{}\r\rId:{}",
                                            record.getTitle(),
                                            result.get("info"),
                                            result.get("imageUrl"),
                                            record.getUrl(),
                                            record.getCreateTime(),
                                            record.getId());

        } else if (StrUtil.equalsIgnoreCase(ESpiderType.smzdm.toString(), record.getType())){

            return StrUtil.format("\r\r### 猫粮：{} \r\r### 价格：{} \r\r ![img]({}) \r\r## [点我查看详情]({}) \r\r 创建时间：{} \r\rId：{}",
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
