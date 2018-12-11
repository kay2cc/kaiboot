package top.kaiccc.kai4boot.ym.http;

import cn.hutool.core.date.DateUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.google.gson.Gson;
import top.kaiccc.kai4boot.ym.job.YueMiaoJob;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author kaiccc
 * @date 2018-12-08 16:17
 */
public class YueMiaoHttp{
    private static final Log log = LogFactory.get();

    public static void main(String[] args) {
        YmConfig config = YmConfig.get();
        log.debug("创建配置对象 OK : {}", new Gson().toJson(config));
        YueMiaoJob yueMiaoJob = new YueMiaoJob(config);


        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            log.debug("Scheduled Start !!!!");
            if (DateUtil.hour(new Date(), true) == 11){
                yueMiaoJob.run();
                log.debug("Scheduled End !!!!");
                executorService.shutdown();
            }

        }, 0, 100, TimeUnit.MILLISECONDS);
    }
}
