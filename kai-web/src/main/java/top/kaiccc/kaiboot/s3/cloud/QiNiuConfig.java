package top.kaiccc.kaiboot.s3.cloud;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author kaiccc
 * @date 2019-01-07 17:05
 */
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

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}
