package top.kaiccc.kai4boot.common.utils;

import cn.hutool.core.map.MapUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.google.gson.Gson;

import java.util.Map;

/**
 * @author kaiccc
 * @date 2018-12-11 15:10
 */
public class RuoKuaiUtils {
    private static final Log log = LogFactory.get();

    public static String ruoKuaiOCR(String imgBase64){
        HttpResponse ruoKuaResponse = HttpRequest.post("http://api.ruokuai.com/create.json")
                .body(RuoKuaiUtils.ruoKuaiConfig(imgBase64))
                .execute();
        log.info(ruoKuaResponse.toString());
        return new JSONObject(ruoKuaResponse.body()).getStr("Result");
    }

    /**
     * username : 用户名
     * password : 密码
     * typeid : 类型
     * timeout : 超时时间
     * softid : 软件ID
     * softkey : 软件KEY
     * image : base64内容 注意:图片的base64编码不包含图片头 如(data:image/jpg;base64,)
     */
    private static String ruoKuaiConfig(String imgBase64){
        Map<String, Object> config = MapUtil.newHashMap();
        config.put("username", "18123268321");
        config.put("password", "xvtie123");
        config.put("typeid", "3040");

        config.put("softid", "117955");
        config.put("softkey", "c5145edec1694701bc5f8839b5177bd0");
        config.put("image", imgBase64);

        return new Gson().toJson(config);
    }

}
