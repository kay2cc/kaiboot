package top.kaiccc.kai4boot.module.wxmsg.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import top.kaiccc.kai4boot.module.spider.entity.SpiderRecord;

import java.util.Map;

/**
 * 微信推送 工具类 (PushBear)
 * @link https://pushbear.ftqq.com/admin/#/
 * @author kaiccc
 * @date 2018-10-08 11:12
 */
public class WxMsgUtils {

    public static void sendMessage(SpiderRecord record, String sendkey){
        Map<String, Object> map = CollUtil.newHashMap();
        map.put("sendkey", sendkey);
        map.put("text", StrUtil.subPre(record.getTitle(),75));
        map.put("desp", record.getDetail());

        HttpRequest.post("https://pushbear.ftqq.com/sub").form(map).executeAsync().body();
    }

    public static void sendMessage(String sendkey, String text, String desp){
        Map<String, Object> map = CollUtil.newHashMap();
        map.put("sendkey", sendkey);
        map.put("text", StrUtil.subPre(text,75));
        map.put("desp", desp);

        HttpRequest.post("https://pushbear.ftqq.com/sub").form(map).executeAsync().body();
    }

}
