package top.kaiccc.kai4boot.sldp.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.kaiccc.kai4boot.sldp.service.SldpService;

/**
 * 订单推送 定时器
 * @author kaiccc
 * @date 2018-10-11 09:51
 */
@Component
@Slf4j
public class SldpOrderWxScheduled {

    @Autowired
    private SldpService sldpService;

    @Scheduled(cron = "0 0 22,23 * * ?")
    public void orderWxScheduled() {
        sldpService.orderWxScheduledPush();
    }

}