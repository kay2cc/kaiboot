package top.kaiccc.kaiboot.taobao.controller;

import cn.hutool.core.thread.ThreadUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import top.kaiccc.kaiboot.common.utils.RestResponse;
import top.kaiccc.kaiboot.taobao.dto.TaoBaoDto;
import top.kaiccc.kaiboot.taobao.repository.HfProduceRepository;
import top.kaiccc.kaiboot.taobao.repository.HfSellerRepository;
import top.kaiccc.kaiboot.taobao.repository.PicsRepository;
import top.kaiccc.kaiboot.taobao.service.ImgDownloadThread;
import top.kaiccc.kaiboot.taobao.service.ImgZipThread;
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
    private TaoBaoService taoBaoService;
    @Autowired
    private PicsRepository picsRepository;
    @Autowired
    private HfSellerRepository hfSellerRepository;
    @Autowired
    private HfProduceRepository hfProduceRepository;
    @Value("${file.taobao.imagePath}")
    public String imagePath;
    @Value("${file.tempPath}")
    public String tempPath;
    @Value("${wx.test-sendkey}")
    public String sendkey;

    @PostMapping("/")
    @ApiOperation(value = "任务保存", notes = "任务保存")
    public RestResponse save (@RequestBody String taoBao){
        log.debug("Taobao 接收到任务");
        TaoBaoDto taoBaoDto = new Gson().fromJson(taoBao, new TypeToken<TaoBaoDto>() {
        }.getType());

        taoBaoService.save(taoBaoDto);
        return RestResponse.success("收到任务");
    }

    @GetMapping("/{sellerId}")
    @ApiOperation(value = "通过店铺id查询是否存在", notes = "通过店铺id查询是否存在")
    public RestResponse findSellerIdExist(@PathVariable String sellerId){
        return RestResponse.success(taoBaoService.countBySellerId(sellerId) == 0);
    }

    @GetMapping("/hf/{time}")
    @ApiOperation(value = "通过时间查询汉服排行", notes = "通过时间查询汉服排行")
    public RestResponse findHfByTime(@PathVariable String time){
        return RestResponse.success(taoBaoService.hfRanking(time));
    }

    @GetMapping("/hf/del")
    @ApiOperation(value = "删除汉服产品记录", notes = "删除汉服产品记录")
    public RestResponse delHf(){
        hfProduceRepository.deleteAll();
        return RestResponse.success();
    }

    @GetMapping("/hf")
    @ApiOperation(value = "查询所有汉服店铺信息", notes = "查询所有汉服店铺信息")
    public RestResponse findHfSellerId(){
        return RestResponse.success(hfSellerRepository.findAll());
    }

    @PostMapping("/hf")
    @ApiOperation(value = "汉服保存", notes = "汉服保存")
    public RestResponse hfSave(@RequestBody String hf){
        log.debug("hf 接收到任务");

        taoBaoService.saveHf(hf);
        return RestResponse.success();
    }

    @GetMapping("/img")
    @ApiOperation(value = "图片下载", notes = "图片下载")
    public RestResponse imgDownload(){
        ThreadUtil.execute(new ImgDownloadThread(picsRepository));

        return RestResponse.success();
    }

    @GetMapping("/zip")
    @ApiOperation(value = "图片压缩zip", notes = "图片压缩zip")
    public RestResponse imgZip() {
        ThreadUtil.execute(new ImgZipThread(imagePath, tempPath, sendkey));
        return RestResponse.success();
    }

}
