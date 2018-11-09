package top.kaiccc.kai4boot.controller;

import cn.hutool.core.util.ObjectUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import top.kaiccc.kai4boot.common.base.BaseController;
import top.kaiccc.kai4boot.entity.User;
import top.kaiccc.kai4boot.common.utils.RestResponse;
import top.kaiccc.kai4boot.dto.UserDto;
import top.kaiccc.kai4boot.repository.UserRepository;
import top.kaiccc.kai4boot.service.UserService;

/**
 * 系统用户 Controller
 * @author kaiccc
 * @date 2018-10-09 16:46
 */
@RestController
@RequestMapping("/admin/user")
@Api(value = "UserController", description = "系统用户", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController extends BaseController {
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/{id}")
    @ApiOperation(value = "通过id获取", notes = "通过id获取")
    public RestResponse get(@PathVariable String id){
        User user = userRepository.findUserById(id);
        if (ObjectUtil.isNull(user)){
            return RestResponse.failed(500, "用户不存在");
        }
        return RestResponse.success(user);
    }

    @PostMapping("/")
    @ApiOperation(value = "添加", notes = "添加")
    public RestResponse save(@RequestBody UserDto userDto){
        userService.save(UserDto.form(userDto));
        return RestResponse.success();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "修改", notes = "修改")
    public RestResponse edit(@PathVariable String id, @RequestBody UserDto userDto){
        userDto.setId(id);
        userService.save(UserDto.form(userDto));
        return RestResponse.success();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除", notes = "删除")
    public RestResponse del(@PathVariable String id){
        try {
            userRepository.deleteById(id);
            return RestResponse.success();
        }catch (Exception e){
            log.error(e);
            return RestResponse.failed(500, "删除失败");
        }
    }

    @PostMapping("/login")
    @ApiOperation(value = "登录", notes = "登录")
    public RestResponse login(@RequestParam("name") String name,
                              @RequestParam("password") String password){

        return RestResponse.success();
    }

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }
}
