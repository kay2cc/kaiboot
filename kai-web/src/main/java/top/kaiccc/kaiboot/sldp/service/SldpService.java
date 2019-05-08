package top.kaiccc.kaiboot.sldp.service;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.kaiccc.kaiboot.common.utils.RuoKuaiUtils;
import top.kaiccc.kaiboot.sldp.dto.OrderListDto;

import java.util.Date;
import java.util.Map;

/**
 * 商城 参数分析
 *
 * 例：今天10号
 * 1.付款日期，前一天（9号）  开始时间 9号，结束时间9号
 *
 * 2.本月1号至今天的业绩。 付款日期，开始时间本月1号，结束时间 今天（10号）
 * @author kaiccc
 * @date 2018-12-11 16:48
 */
@Service
@Slf4j
public class SldpService {
    /**
     * 30 验证码 URL
     */
    private HttpRequest httpRequest;
    private static final String CAPTCHA_URL = "http://www.sanlingdp.com/site/captcha";
    private static final String LOGIN_URL = "http://www.sanlingdp.com/admin-read/login";
    private static final String ORDER_URL = "http://www.sanlingdp.com/order-read/list?page=1&keyword=&page_size=20&status=-1&payment_status=-1&payment_type=-1&order_type=-1&category_type_id=-1&is_receipt=-1&deliver_shop_id=-1&start_date=&end_date=&pay_start_date={}&pay_end_date={}&province_id={}&city_id=-1&district_id=-1&map=1";

    /**
     * 订单信息 微信推送
     */
    public Map<String, OrderListDto> orderWxScheduledPush(String city){
        log.info("SLDP orderWxScheduledPush start ! --- " + city);
        Map<String, OrderListDto> jsonMap = Maps.newHashMap();
        /*
         * 昨天
         */
        String yesterday = DateUtil.formatDate(DateUtil.yesterday());

        /*
         * 本月至今
         */
        String monthStartDate = DateUtil.formatDate(DateUtil.beginOfMonth(new Date()));
        String monthEndDate = DateUtil.today();

        try {
            this.login();
            // 昨天
            OrderListDto yesterOrder = this.findOrderList(yesterday, yesterday, city);
            // 本月至今
            OrderListDto monthOrder = this.findOrderList(monthStartDate, monthEndDate, city);

            jsonMap.put("yesterOrder", yesterOrder);
            jsonMap.put("monthOrder", monthOrder);
        } catch (Exception e) {
            log.error("出现错误，请刷新或者请呼叫皮皮猪", e);
        }
        log.info("orderWxScheduledPush end !");
        return jsonMap;
    }

    /**
     * 登录
     */
    private void login() throws Exception {
        HttpResponse httpResponse = HttpRequest.get(CAPTCHA_URL).execute();
        String imgBase64 = Base64.encode(httpResponse.bodyStream());
        String rkCaptcha = RuoKuaiUtils.ruoKuaiOCR(imgBase64);

        Map<String, Object> loginForm = MapUtil.newHashMap();
        loginForm.put("user_name", "ysx");
        loginForm.put("user_pwd", "ysx");
        loginForm.put("verify_code", rkCaptcha);

        HttpResponse loginResponse = HttpRequest.post(LOGIN_URL)
                .form(loginForm)
                .execute();
        JSONObject bodyJson = new JSONObject(loginResponse.body());
        log.info(loginResponse.toString());
        if (!"0".equals(bodyJson.getStr("code"))){
            throw new Exception("login error");
        }
    }

    /**
     * 查询 订单列表
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    private OrderListDto findOrderList(String startDate, String endDate, String city) throws Exception {
        String url = StrUtil.format(ORDER_URL, startDate, endDate, city);

        HttpResponse orderResponse = HttpRequest.get(url).execute();

        OrderListDto orderListDto = new Gson().fromJson(orderResponse.body(), OrderListDto.class);

        if (ObjectUtil.isNull(orderListDto) || orderListDto.getCode() != 0){
            throw new Exception("查询失败");
        }
        return orderListDto;
    }

    /**
     * 推送消息格式化
     * @return
     */
    private String formatWxPushMsg(OrderListDto orderListDto, String type){
        StringBuilder orderMsg = new StringBuilder(StrUtil.format("订单合计|实付金额合计|PV合计\n" +
                        ":--:|:--:|:--:\n" +
                        "{}|{}|{}\n\n\n",
                orderListDto.getData().getCount(), orderListDto.getData().getPayment_amount(), orderListDto.getData().getPv()));

        if ("yesterday".equals(type)){
            int i=1;
            for (OrderListDto.DataBean.ListBean order : orderListDto.getData().getList()){

                String msg = StrUtil.format(" ## {}. {} \n" +
                                "购买人|订单金额|实付金额|产生PV|订单类型\n" +
                                ":--:|:--:|:--:|:--:|:--:\n" +
                                "{}|{}|{}|{}|{} \n" +
                                "- 单号：{} \n" +
                                "- 下单时间：{} \n" +
                                "- 支付时间：{} \n" +
                                "- 收货人：{} \n" +
                                "- 收货地址：{} \n" +
                                "- 联系人：{} \n",
                                i,order.getPay_real_name(),
                        order.getPay_real_name(),
                        order.getTotal_fee(), order.getPayment_amount(), order.getPV(), order.getOrder_type_name(),
                        order.getOrder_no(), order.getCreate_time(), order.getPayment_time(), order.getReceipt_man(), order.getReceipt_addr(), order.getReceipt_tel());

                orderMsg.append(msg);
                i++;
                StringBuilder infoList = new StringBuilder();
                for (OrderListDto.DataBean.ListBean.GoodsInfoBean info : order.getGoodsInfo()){
                    infoList.append(info.getSpec_name()).append("、");
                }
                orderMsg.append(StrUtil.format("> {} \n\n", infoList));
            }
        }
        orderMsg.append(StrUtil.format("\n `发送时间：{}`", DateUtil.now()));
        log.info(orderMsg.toString());
        return orderMsg.toString();
    }

}
