package top.kaiccc.kai4boot.ym.http;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.setting.Setting;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
    private int threadNum;
    private Map<String, List<String>> headers;
    /**
     * 部门 code 码
     */
    private List<String> depaCodeList;
    /**
     * 报名用户id  458515于   526102李
     */
    private String linkmanId;
    /**
     * 育苗 code  九价8803  二价8801
     */
    private String vaccineCode;
    /**
     * 育苗 第几针 1
     */
    private String vaccineIndex;
    /**
     * 提交接口 URL
     */
    private String submitUrl;
    /**
     * 诊所 可打育苗  获取到诊所关联育苗id
     */
    private String vaccinesUrl;
    /**
     * 诊所 育苗可预定时间 URL
     */
    private String workDaysUrl;
    /**
     * 诊所 育苗可预定具体时间 URL
     */
    private String departmentWorkTimesUrl;

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
        config.setThreadNum(30);

        Map<String, List<String>> headers = MapUtil.newHashMap();
        headers.put("User-Agent", CollUtil.newArrayList(config.getUserAgent()));
        headers.put("sk", CollUtil.newArrayList(config.getSk()));
        headers.put("tk", CollUtil.newArrayList(config.getTk()));

        config.setHeaders(headers);

        List<String> depaCodeList = Arrays.asList(set.getStr("depaCodeList").split(","));
        config.setDepaCodeList(depaCodeList);
        config.setLinkmanId("458515");
        config.setVaccineCode("8803");
        config.setVaccineIndex("1");

        config.setTestUrl("https://wx.healthych.com/base/department/pageList.do?cityName=&offset=0&limit=10&regionCode=510109&isOpen=1&longitude=&latitude=");
        config.setVaccinesUrl("https://wx.healthych.com/base/department/vaccines.do?depaCode={}&vaccineCode={}");
        config.setWorkDaysUrl("https://wx.healthych.com/order/subscribe/workDays.do?depaCode={}&linkmanId={}&vaccCode={}&vaccIndex={}&departmentVaccineId={}");
        config.setDepartmentWorkTimesUrl("https://wx.healthych.com/order/subscribe/departmentWorkTimes.do?depaCode={}&vaccCode={}&vaccIndex={}&subsribeDate={}&departmentVaccineId={}");
        config.setSubmitUrl("https://wx.healthych.com/order/subscribe/add.do?depaCode={}&vaccineCode={}&vaccineIndex={}&linkmanId={}&subscribeDate={}&subscirbeTime={}&departmentVaccineId={}");

        return config;
    }
}
