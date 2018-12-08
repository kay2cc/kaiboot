package top.kaiccc.kai4boot.ym.http;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

/**
 * @author kaiccc
 * @date 2018-12-08 16:17
 */
public class YueMiaoHttp {
    private static final Log log = LogFactory.get();

    public static void main(String[] args) {
        YueMiaoHttp ym = new YueMiaoHttp();
        //CronUtil.start();
        ym.run();
    }

    public void run(){
        log.debug("Job Start !");
        // 配置文件
        YmConfig config = YmConfig.get();
        log.info(new Gson().toJson(config));

        Map<String, List<String>> headers = MapUtil.newHashMap();
        headers.put("User-Agent", CollUtil.newArrayList(config.getUserAgent()));
        headers.put("sk", CollUtil.newArrayList(config.getSk()));
        headers.put("tk", CollUtil.newArrayList(config.getTk()));

        HttpResponse response = HttpRequest.get(config.getTestUrl())
                                            .header(headers, true)
                                            .cookie(config.getCookie()).execute();
        log.info(response.body());
        log.debug(response.toString());



    }
}
