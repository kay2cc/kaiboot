package top.kaiccc.kai4boot.common.service;

import cn.hutool.core.util.NetUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;

@Order(value=2)
@Component
public class CommandLineRunnerService implements CommandLineRunner {

    private static final Log log = LogFactory.get();
    @Value("${server.servlet.context-path}")
    private String projectName;
    @Value("${server.port}")
    private String port;
    @Value("${wx.test-sendkey}")
    private String sendkey;


    @Override
    public void run(String... args) {
        log.info("http://localhost:{}{} Successful startup", port, projectName);
        LinkedHashSet<String> ips =  NetUtil.localIpv4s();
  /*      WxMsgUtils.sendMessage(sendkey, projectName + "服务器启动成功",
                StrUtil.format("#### 服务器启动成功 \n" +
                        "#### ip：{} \n" +
                        "#### 端口：{} \n" +
                        "#### 启动时间：{}\n ",
                        new Gson().toJson(ips),
                        port,
                        DateUtil.now()));*/
    }
}
