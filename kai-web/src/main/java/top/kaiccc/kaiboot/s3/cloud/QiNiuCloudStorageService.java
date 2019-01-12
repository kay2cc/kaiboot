package top.kaiccc.kaiboot.s3.cloud;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.persistent.FileRecorder;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import top.kaiccc.kaiboot.common.exception.RestException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 七牛云存储
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-25 15:41
 */
@Slf4j
public class QiNiuCloudStorageService extends CloudStorageService {
    private QiNiuConfig config;

    private UploadManager uploadManager;
    private String token;
    private boolean isRbu;

    public QiNiuCloudStorageService(QiNiuConfig config, boolean isRbu) throws IOException {
        this.config = config;
        this.isRbu = isRbu;
        init();
    }

    private void init() throws IOException {
        Configuration cfg = new Configuration(Zone.zone1());
        // 超时单位 秒
        long expires = 3600;
        if (isRbu){
            //设置断点续传文件进度保存目录
            FileRecorder fileRecorder = new FileRecorder(config.getTempPath() + File.separator + config.getBucketName());
            uploadManager = new UploadManager(cfg, fileRecorder);
            expires = 3600 * 24;
        }else {
            uploadManager = new UploadManager(cfg);
        }

        token = Auth.create(config.getAccessKey(), config.getSecretKey()).
                uploadToken(config.getBucketName(), null, expires, null);
    }

    @Override
    public String upload(byte[] data, String path) {
        try {
            Response res = uploadManager.put(data, path, token);
            if (!res.isOK()) {
                throw new RuntimeException("上传七牛出错：" + res.toString());
            }
        } catch (Exception e) {
            throw new RestException("上传文件失败，请核对七牛配置信息", e);
        }

        return config.getDomain() + "/" + path;
    }

    @Override
    public String rbu(String filePath, String path) {
        try {
            log.debug("RBU Start {}", filePath);

            Response response = uploadManager.put(filePath, path, token);

            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            log.debug("上传成功 结果返回 {}, {}", putRet.key, putRet.hash);
            return config.getDomain() + "/" + putRet.key;
        } catch (QiniuException ex) {
            Response r = ex.response;
            try {
                log.error(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return null;
    }


    @Override
    public String upload(InputStream inputStream, String path) {
        try {
            byte[] data = IOUtils.toByteArray(inputStream);
            return this.upload(data, path);
        } catch (IOException e) {
            throw new RestException("上传文件失败", e);
        }
    }

}
