package top.kaiccc.kai4boot.controller;


import cn.hutool.core.lang.UUID;
import cn.hutool.http.HtmlUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import top.kaiccc.kai4boot.common.enums.ESpiderType;
import top.kaiccc.kai4boot.entity.SpiderConfig;
import top.kaiccc.kai4boot.common.utils.RestResponse;
import top.kaiccc.kai4boot.repository.SpiderConfigRepository;
import top.kaiccc.kai4boot.repository.SpiderRecordRepository;

/**
 * <p>
 * 爬虫 前端控制器
 * </p>
 *
 * @author kaiccc
 * @since 2018-10-10
 */
@Log
@RestController
@RequestMapping(value = "/spider", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "SpiderController", description = "爬虫管理", produces = MediaType.APPLICATION_JSON_VALUE)
public class SpiderController {

    @Autowired
    SpiderConfigRepository spiderConfigRepository;
    @Autowired
    SpiderRecordRepository spiderRecordRepository;

    @ApiOperation(value = "爬虫管理-保存", notes = "爬虫管理-保存")
    @PostMapping("/config/save")
    public RestResponse save (SpiderConfig spiderConfig){
        spiderConfig.setUrl(HtmlUtil.unescape(spiderConfig.getUrl()));
        spiderConfigRepository.save(spiderConfig);
        return RestResponse.success();
    }

    @ApiOperation(value = "爬虫管理-删除", notes = "爬虫管理-删除")
    @DeleteMapping("/searchKey/{searchKey}")
    public RestResponse deleteByName(@PathVariable String searchKey){
        SpiderConfig config = new SpiderConfig();
        config.setSearchKey(searchKey);
        spiderConfigRepository.delete(config);
        return RestResponse.success();
    }

    @ApiOperation(value = "爬虫管理-记录查询", notes = "爬虫管理-记录查询")
    @GetMapping("/record")
    public RestResponse findRecordList(){
        return RestResponse.success(spiderRecordRepository.findAll());
    }

    @ApiOperation(value = "爬虫管理-配置查询", notes = "爬虫管理-配置查询")
    @GetMapping("/config")
    public RestResponse findConfigList(){
        return RestResponse.success(spiderConfigRepository.findAll());
    }

    @ApiOperation(value = "爬虫管理-init", notes = "爬虫管理-init")
    @GetMapping("/init")
    public RestResponse init(){
        if (spiderConfigRepository.count() == 0){
            SpiderConfig config = new SpiderConfig();
            config.setId(UUID.randomUUID().toString());
            config.setSearchKey("http://www\\.mp4ba\\.com/\\?m=vod-detail-id-\\d+\\.html");
            config.setUrl("http://www.mp4ba.com");
            config.setWxPushKey("6043-b8df07847e206f079a0e0682d8032fcd");
            config.setType(ESpiderType.mp4ba.toString());
            spiderConfigRepository.save(config);

            config.setId(UUID.randomUUID().toString());
            config.setSearchKey("日用品囤货");
            config.setUrl("http://search.smzdm.com/?c=faxian&s={}&v=b");
            config.setWxPushKey("6081-920770083f6d9c16a3ecd21cdd08c597");
            config.setType(ESpiderType.smzdm.toString());
            spiderConfigRepository.save(config);
        }

        return RestResponse.success(spiderConfigRepository.findAll());
    }
}
