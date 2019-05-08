package top.kaiccc.kaiboot.sldp.controller;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.kaiccc.kaiboot.common.utils.RestResponse;
import top.kaiccc.kaiboot.sldp.job.SldpOrderJob;
import top.kaiccc.kaiboot.sldp.service.SldpService;

/**
 * @author kaiccc
 * @date 2018-12-11 17:55
 */
@RestController
@RequestMapping("/sldp")
@Api(value = "SldpController", description = "30订单管理", produces = MediaType.APPLICATION_JSON_VALUE)
public class SldpController {

    @Autowired
    private SldpService sldpService;

    @GetMapping("")
    @ApiOperation(value = "推送订单信息", notes = "推送订单信息")
    public RestResponse index (@RequestParam(value = "city", required = false) String city) {
        if (StrUtil.equals("重庆", city)){
            return RestResponse.success(sldpService.city_map.get("22"));
        }else {
            return RestResponse.success(sldpService.city_map.get("23"));
        }
    }

    @GetMapping("/sync")
    @ApiOperation(value = "同步订单信息", notes = "同步订单信息")
    public RestResponse sync () {
        ThreadUtil.excAsync(new SldpOrderJob(sldpService), false);
        return RestResponse.success();
    }
}
