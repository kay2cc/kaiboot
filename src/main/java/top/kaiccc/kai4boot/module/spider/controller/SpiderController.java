package top.kaiccc.kai4boot.module.spider.controller;


import cn.hutool.http.HtmlUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import top.kaiccc.kai4boot.common.utils.RestResponse;
import top.kaiccc.kai4boot.module.spider.entity.SpiderConfig;
import top.kaiccc.kai4boot.module.spider.service.ISpiderConfigService;
import top.kaiccc.kai4boot.module.spider.service.ISpiderRecordService;

/**
 * <p>
 * 爬虫 前端控制器
 * </p>
 *
 * @author kaiccc
 * @since 2018-10-10
 */
@RestController
@RequestMapping(value = "/spider", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "SpiderController", description = "爬虫管理", produces = MediaType.APPLICATION_JSON_VALUE)
public class SpiderController {

    @Autowired
    private ISpiderConfigService configService;
    @Autowired
    private ISpiderRecordService recordService;

    @ApiOperation(value = "爬虫管理-保存", notes = "爬虫管理-保存")
    @PostMapping("/config/save")
    public RestResponse save (SpiderConfig spiderConfig){
        spiderConfig.setUrl(HtmlUtil.unescape(spiderConfig.getUrl()));
        configService.save(spiderConfig);
        return RestResponse.success();
    }

    @ApiOperation(value = "爬虫管理-删除", notes = "爬虫管理-删除")
    @DeleteMapping("/searchKey/{searchKey}")
    public RestResponse deleteByName(@PathVariable String searchKey){
        configService.remove(new QueryWrapper<SpiderConfig>().like("search_key", searchKey));
        return RestResponse.success();
    }
}
