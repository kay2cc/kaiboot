package top.kaiccc.kaiboot.common.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

import java.util.Map;

/**
 * 微信推送 工具类 (PushBear)
 * @link https://pushbear.ftqq.com/admin/#/
 * @author kaiccc
 * @date 2018-10-08 11:12
 */
public class WxMsgUtils {
    private static final Log log = LogFactory.get();

    public static void sendMessage(String sendkey, String text, String desp){
        Map<String, Object> map = CollUtil.newHashMap();
        map.put("sendkey", sendkey);
        map.put("text", StrUtil.subPre(text,75));
        map.put("desp", desp);

        HttpResponse response =  HttpRequest.post("https://pushbear.ftqq.com/sub")
                                                .form(map)
                                                .executeAsync();
        log.info(response.toString());
    }

}
