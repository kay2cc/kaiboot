package top.kaiccc.kai4boot.ym.http;

import cn.hutool.setting.Setting;
import lombok.Data;

/**
 * @author kaiccc
 * @date 2018-12-08 16:32
 */
@Data
public class YmConfig {

    private String userAgent;
    private String sk;
    private String tk;
    private String cookie;
    private String accept;

    /**
     * 提交接口 URL
     */
    private String submitUrl;
    /**
     * 查询预约接口 URL
     */
    private String reservationUrl;
    /**
     * 测试接口 预定列表
     */
    private String testUrl;


    public static YmConfig get(){
        YmConfig config = new YmConfig();
        Setting set = new Setting("config/ym.setting");
        config.setAccept(set.getStr("Accept"));
        config.setCookie(set.getStr("Cookie"));
        config.setSk(set.getStr("sk"));
        config.setTk(set.getStr("tk"));
        config.setUserAgent(set.get("UserAgent"));

        config.setTestUrl("https://wx.healthych.com/base/department/pageList.do?cityName=&offset=0&limit=10&regionCode=510109&isOpen=1&longitude=&latitude=");

        config.setReservationUrl("https://wx.healthych.com/order/subscribe/workDays.do?");
        /*
         * {
         * 	"code": "0000",
         * 	"data": {
         * 		"dateList": [
         * 			"2018-12-10",
         * 			"2018-12-11",
         * 			"2018-12-12",
         * 			"2018-12-13"],
         * 		"subscribeDays": 7
         *        },
         * 	"ok": true
         * }
         */
        config.setSubmitUrl("https://wx.healthych.com/order/subscribe/add.do?");



        return config;
    }
}
