package top.kaiccc.kaiboot.admin.controller;

import cn.hutool.core.thread.ThreadUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kaiccc.kaiboot.admin.service.SqlBackupThread;
import top.kaiccc.kaiboot.common.utils.RestResponse;


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


    @GetMapping("/backup")
    @ApiOperation(value = "文件备份", notes = "文件备份")
    public RestResponse upload() {
        ThreadUtil.execute(new SqlBackupThread());
        return RestResponse.success();
    }

}
