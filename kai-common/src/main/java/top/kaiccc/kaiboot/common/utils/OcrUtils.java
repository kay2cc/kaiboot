package top.kaiccc.kaiboot.common.utils;

import cn.hutool.core.map.MapUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.google.gson.Gson;

import java.util.Map;

/**
 * baidu OCR
 * @author kaiccc
 * @date 2018-12-11 15:10
 */
public class OcrUtils {
    private static final Log log = LogFactory.get();
    private static final String URL = "https://v2-api.jsdama.com/upload";
//    https://www.jsdati.com/

    public static String ocr(String imgBase64) {
        HttpResponse response = HttpRequest.post(URL)
                .body(OcrUtils.config(imgBase64))
                .execute();
        log.info(response.toString());
        JSONObject jsonBody = new JSONObject(response.body());
        JSONObject data = jsonBody.getJSONObject("data");
        return data.getStr("recognition");
    }

    private static String config(String imgBase64){
        Map<String, Object> config = MapUtil.newHashMap();
        config.put("softwareId", "14013");
        config.put("softwareSecret", "ac7Smomld2BWZxKRQ1Qa4Tcx9umAbCn8iouUFKFu");
        config.put("username", "kkkai123123");
        config.put("password", "XVtie123!@#");
        config.put("captchaData", imgBase64);
        config.put("captchaType", "1001");

        return new Gson().toJson(config);
    }
}
