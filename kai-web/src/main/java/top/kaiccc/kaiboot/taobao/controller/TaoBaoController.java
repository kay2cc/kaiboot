package top.kaiccc.kaiboot.taobao.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
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
import top.kaiccc.kaiboot.common.utils.StorageUnitUtils;
import top.kaiccc.kaiboot.common.utils.WxMsgUtils;
import top.kaiccc.kaiboot.s3.cloud.QiNiuCloudStorageService;
import top.kaiccc.kaiboot.s3.cloud.QiNiuConfig;
import top.kaiccc.kaiboot.s3.dto.QiNiuProgressDto;
import top.kaiccc.kaiboot.taobao.dto.TaoBaoDto;
import top.kaiccc.kaiboot.taobao.repository.PicsRepository;
import top.kaiccc.kaiboot.taobao.service.ImgDownloadThread;
import top.kaiccc.kaiboot.taobao.service.ImgUploadThread;
import top.kaiccc.kaiboot.taobao.service.TaoBaoService;

import java.io.File;
import java.io.IOException;


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
    private QiNiuConfig qiNiuConfig;
    @Autowired
    private TaoBaoService taoBaoService;
    @Autowired
    private PicsRepository picsRepository;
    @Value("${file.taobao.imagePath}")
    public String imagePath;
    @Value("${file.tempPath}")
    public String tempPath;
    @Value("${wx.test-sendkey}}")
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

    @GetMapping("/img")
    @ApiOperation(value = "图片下载", notes = "图片下载")
    public RestResponse imgDownload(){
        ThreadUtil.execute(new ImgDownloadThread(picsRepository));

        return RestResponse.success();
    }

    @GetMapping("/upload")
    @ApiOperation(value = "文件上传任务", notes = "文件上传任务")
    public RestResponse upload(@RequestParam(value = "zipPath", required = false) String zipPath) throws IOException {
        QiNiuCloudStorageService storageService = new QiNiuCloudStorageService(qiNiuConfig, true);
        ThreadUtil.execute(new ImgUploadThread(storageService, imagePath, tempPath, zipPath, sendkey));
        return RestResponse.success();
    }

    @GetMapping("/upload/progress")
    @ApiOperation(value = "文件上传进度", notes = "文件上传进度")
    public RestResponse progress() {
        String msg = "没发现上传文件";
        File[] tempList = FileUtil.ls(tempPath);

        for (File temp : tempList){
            if (FileUtil.isModifed(temp, System.currentTimeMillis() - 1000 * 60 * 10)){
                String progressStr = FileReader.create(temp).readString();
                QiNiuProgressDto progressDto = new Gson().fromJson(progressStr, QiNiuProgressDto.class);
                msg = StrUtil.format("### 文件名：{} \n" +
                        "### 文件总大小：{} \n" +
                        "### 已上传：{} \n" +
                        "### 最后上传成功时间：{} \n" +
                        "### 当前时间：{} \n",
                        temp.getPath(),
                        StorageUnitUtils.getPrintSize(progressDto.getSize()),
                        StorageUnitUtils.getPrintSize(progressDto.getOffset()),
                        DateUtil.date(progressDto.getModify_time()).toString(),
                        DateUtil.now());
                WxMsgUtils.sendMessage(sendkey, "文件上传进度", msg);
                break;
            }
        }

        return RestResponse.success(msg);
    }

}
