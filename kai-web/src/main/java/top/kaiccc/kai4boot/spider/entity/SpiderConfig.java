package top.kaiccc.kai4boot.spider.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 爬虫配置表
 * @author kaiccc
 * @date 2018-11-02 13:18
 */
@Entity
@Table(name = "kai_spider_config")
@Data
public class SpiderConfig {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private String id;
    /**
     * 类型
     */
    @Column(name = "type")
    private String type;
    /**
     * 搜索Key
     */
    @Column(name = "search_key")
    private String searchKey;
    /**
     * 网址
     */
    @Column(name = "url")
    private String url;
    /**
     * 微信推送key
     */
    @Column(name = "wx_push_key")
    private String wxPushKey;
}
