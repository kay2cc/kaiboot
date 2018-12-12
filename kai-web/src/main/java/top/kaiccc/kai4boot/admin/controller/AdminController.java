package top.kaiccc.kai4boot.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kaiccc.kai4boot.common.utils.RestResponse;


/**
 * Admin Controller
 * @author kaiccc
 * @date 2018-10-09 16:46
 */
@RestController
@RequestMapping("/admin")
@Api(value = "AdminController", description = "系统管理", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {

    @GetMapping("")
    @ApiOperation(value = "Hello", notes = "Hello")
    public RestResponse index (){
        return RestResponse.success();
    }

}
