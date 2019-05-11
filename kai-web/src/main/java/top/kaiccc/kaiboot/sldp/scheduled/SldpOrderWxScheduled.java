package top.kaiccc.kaiboot.sldp.scheduled;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.kaiccc.kaiboot.sldp.job.SldpOrderJob;
import top.kaiccc.kaiboot.sldp.service.SldpService;

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
    @Value("${spider.scheduled}")
    private boolean scheduledEnabled;

    @Scheduled(cron = "0 0 22 * * ?")
    public void orderWxScheduled() {
        log.info("SldpOrderWxScheduled start !!!!");
        if (scheduledEnabled){
            ThreadUtil.execute(new SldpOrderJob(sldpService));
        }
    }

}