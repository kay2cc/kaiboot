package top.kaiccc.kai4boot.sldp.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kaiccc.kai4boot.common.utils.RestResponse;

/**
 * @author kaiccc
 * @date 2018-12-11 17:55
 */
@RestController
@RequestMapping("/sldp")
public class SldpController {
    @PostMapping("")
    public RestResponse index (){
        return RestResponse.success();
    }
}
