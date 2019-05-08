package top.kaiccc.kaiboot.sldp.job;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import top.kaiccc.kaiboot.sldp.service.SldpService;

/**
 * @author kk
 * @Package top.kaiccc.kaiboot.sldp
 * @date 2019/5/8 16:50
 */
@Slf4j
public class SldpOrderJob extends Thread{

    private SldpService sldpService;

    @Override
    public void run() {
        for(int i=1; i<=3; i++) {
            if (sldpService.login()) {
                log.info("登录成功");
                break;
            }
            log.error(StrUtil.format("登录第{}次失败", i));
            if (i == 3){
                return;
            }
        }
        try {
            sldpService.city_map.put("23", sldpService.orderWxScheduledPush("23"));
            sldpService.city_map.put("22", sldpService.orderWxScheduledPush("22"));
            log.info("订单同步完成 " + DateUtil.now());
        } catch (Exception e) {
            log.info("订单同步线程", e);
        }
    }

    public SldpOrderJob(SldpService sldpService) {
        this.sldpService = sldpService;
    }
}
