package top.kaiccc.kai4boot.module.spider.entity;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 爬虫记录表
 * @author kaiccc
 * @date 2018-11-02 13:18
 */
@Entity
@Table(name = "kai_spider_record")
@Data
public class SpiderRecord {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    /**
     * 类型
     */
    @Column(name = "type")
    private String type;
    /**
     * 标题
     */
    @Column(name = "title")
    private String title;
    /**
     * 详情
     */
    @Column(name = "detail", length = 4000)
    private String detail;
    /**
     * 网址
     */
    @Column(name = "url")
    private String url;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private String createTime;

    public SpiderRecord(String type, String title, String url) {
        this.id = IdUtil.simpleUUID();
        this.type = type;
        this.title = title;
        this.url = url;
        this.createTime = DateUtil.now();
    }

    public SpiderRecord() {
    }
}
