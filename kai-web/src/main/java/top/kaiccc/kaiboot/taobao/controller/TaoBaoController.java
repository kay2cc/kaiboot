package top.kaiccc.kaiboot.taobao.controller;

import cn.hutool.core.thread.ThreadUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import top.kaiccc.kaiboot.common.utils.RestResponse;
import top.kaiccc.kaiboot.taobao.dto.TaoBaoDto;
import top.kaiccc.kaiboot.taobao.repository.PicsRepository;
import top.kaiccc.kaiboot.taobao.service.ImgDownloadThread;
import top.kaiccc.kaiboot.taobao.service.TaoBaoService;


/**
 * Taobao Controller
 * @author kaiccc
 * @date 2018-10-09 16:46
 */
@Slf4j
@RestController
@RequestMapping("/tb")
@Api(value = "TaobaoController", description = "淘宝爬虫", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaoBaoController {

    @Autowired
    public TaoBaoService taoBaoService;
    @Autowired
    public PicsRepository picsRepository;

    @PostMapping("/")
    @ApiOperation(value = "任务保存", notes = "任务保存")
    public RestResponse save (@RequestBody String taoBao){
        TaoBaoDto taoBaoDto = new Gson().fromJson(taoBao, new TypeToken<TaoBaoDto>() {
        }.getType());

        taoBaoService.save(taoBaoDto);
        return RestResponse.success("收到任务");
    }

    @GetMapping("/img")
    @ApiOperation(value = "图片下载", notes = "图片下载")
    public RestResponse imgDownload(){
        ThreadUtil.execute(new ImgDownloadThread(picsRepository));

        return RestResponse.success();
    }
}
