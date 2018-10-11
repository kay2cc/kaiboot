package top.kaiccc.kai4boot.service;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value=2)
@Component
public class CommandLineRunnerService implements CommandLineRunner {

    private static final Log log = LogFactory.get();

    @Override
    public void run(String... args) throws Exception {
        log.info(" --- kai4boot Successful startup --- ");


    }
}
