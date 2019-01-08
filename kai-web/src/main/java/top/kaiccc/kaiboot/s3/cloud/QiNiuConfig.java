package top.kaiccc.kaiboot.s3.cloud;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author kaiccc
 * @date 2019-01-07 17:05
 */
@Data
@Component
@ConfigurationProperties(prefix = "cloud.qiniu")
public class QiNiuConfig {

    /**
     * 绑定的域名
     */
    private String domain;
    /**
     * 七牛路径前缀
     */
    private String prefix;
    /**
     * ACCESS_KEY
     */
    private String accessKey;
    /**
     * SECRET_KEY
     */
    private String secretKey;
    /**
     * bucket name
     */
    private String bucketName;
    /**
     * 临时目录
     */
    private String tempPath;
}
