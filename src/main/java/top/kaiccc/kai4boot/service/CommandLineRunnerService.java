package top.kaiccc.kai4boot.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import top.kaiccc.kai4boot.module.wxmsg.utils.WxMsgUtils;

@Order(value=2)
@Component
public class CommandLineRunnerService implements CommandLineRunner {

    private static final Log log = LogFactory.get();
    @Value("${server.servlet.context-path}")
    private String projectName;
    @Value("${server.port}")
    private String port;
    @Value("${wx.sendkey}")
    private String sendkey;


    @Override
    public void run(String... args) {
        log.info("http://localhost:{}{} Successful startup", port, projectName);
        WxMsgUtils.sendMessage(sendkey, projectName + "服务器启动成功", "服务器启动成功" + DateUtil.now());
    }
}
