package top.kaiccc.kai4boot.index.controller;

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
@RequestMapping("/")
public class IndexController {

    @GetMapping("")
    public RestResponse index (){
        return RestResponse.success("访问访问");
    }

}
