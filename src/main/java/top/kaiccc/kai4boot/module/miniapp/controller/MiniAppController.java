package top.kaiccc.kai4boot.module.miniapp.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaMessage;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.kaiccc.kai4boot.module.miniapp.utils.WxMaConfiguration;

/**
 * 小程序
 * @author kaiccc
 * @date 2018-10-09 16:45
 */
@Slf4j
@RestController
@RequestMapping("/wx/app/{appid}")
public class MiniAppController {

    @PostMapping
    public String post(@PathVariable String appid,
                       @RequestBody String requestBody,
                       @RequestParam("msg_signature") String msgSignature,
                       @RequestParam("encrypt_type") String encryptType,
                       @RequestParam("signature") String signature,
                       @RequestParam("timestamp") String timestamp,
                       @RequestParam("nonce") String nonce) {

        final WxMaService wxService = WxMaConfiguration.getMaServices().get(appid);
        if (wxService == null) {
            throw new IllegalArgumentException(StrUtil.format("未找到对应appid={}的配置，请核实！", appid));
        }

        // 明文传输/兼容的消息
        if (StringUtils.isBlank(encryptType)) {
            WxMaMessage inMessage = WxMaMessage.fromJson(requestBody);
            this.route(inMessage,appid);
            return "success";
        }

        throw new RuntimeException("不可识别的加密类型：" + encryptType);
    }

    private void route(WxMaMessage message, String appid) {
        try {
            WxMaConfiguration.getRouters().get(appid).route(message);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
