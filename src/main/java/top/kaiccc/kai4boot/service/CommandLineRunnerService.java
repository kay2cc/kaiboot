package top.kaiccc.kai4boot.service;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import top.kaiccc.kai4boot.common.enums.ESpiderType;
import top.kaiccc.kai4boot.module.spider.job.Mp4BaSpiderJob;
import top.kaiccc.kai4boot.module.spider.entity.SpiderConfig;
import top.kaiccc.kai4boot.module.spider.service.ISpiderConfigService;

@Order(value=1)
@Component
public class CommandLineRunnerService implements CommandLineRunner {

    private static final Log log = LogFactory.get();
    @Autowired
    private ISpiderConfigService spiderConfigService ;


    @Override
    public void run(String... args) throws Exception {
        log.info("CommandLineRunnerService Start!!!");

        Mp4BaSpiderJob.MP4BA_CONFIG = spiderConfigService.getOne(new QueryWrapper<SpiderConfig>().
                                            eq("type" , ESpiderType.mp4ba.toString()));

    }
}
