package top.kaiccc.kai4boot.sldp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kaiccc.kai4boot.common.utils.RestResponse;
import top.kaiccc.kai4boot.sldp.service.SldpService;

/**
 * @author kaiccc
 * @date 2018-12-11 17:55
 */
@RestController
@RequestMapping("/sldp")
public class SldpController {

    @Autowired
    private SldpService sldpService;

    @GetMapping("")
    public RestResponse index (){
        sldpService.orderWxScheduledPush();
        return RestResponse.success();
    }
}
