package top.kaiccc.kai4boot.module.index.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import top.kaiccc.kai4boot.common.utils.RedisUtils;

/**
 * Index Controller
 * @author kaiccc
 * @date 2018-10-09 16:46
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping("")
    public String index (Model model){
        model.addAttribute("projectName", "kai4boot");

        return "index";
    }

}
