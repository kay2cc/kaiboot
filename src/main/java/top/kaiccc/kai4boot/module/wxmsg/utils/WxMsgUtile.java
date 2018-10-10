package top.kaiccc.kai4boot.module.wxmsg.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import top.kaiccc.kai4boot.module.spider.entity.SpiderRecord;

import java.util.Map;

/**
 * 微信推送 工具类 (PushBear)
 * @link https://pushbear.ftqq.com/admin/#/
 * @author kaiccc
 * @date 2018-10-08 11:12
 */
public class WxMsgUtile {

    private static final Log log = LogFactory.get();

    public static void sendMessage(SpiderRecord record, String sendkey){
        Map<String, Object> map = CollUtil.newHashMap();

        log.info(record.getTitle(), record.getDetail());
        map.put("sendkey", sendkey);
        map.put("text", StrUtil.subPre(record.getTitle(),75));
        map.put("desp", record.getDetail() + " \n ID: " + record.getId());

        HttpRequest.post("https://pushbear.ftqq.com/sub").form(map).execute().body();
    }
}
