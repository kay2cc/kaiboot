package top.kaiccc.kai4boot.module.spider.entity;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 爬虫记录表
 * </p>
 *
 * @author kaiccc
 * @since 2018-10-10
 */
@TableName("kai_spider_record")
@ApiModel(value="SpiderRecord对象", description="爬虫记录表")
public class SpiderRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    @ApiModelProperty(value = "类型")
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "标题")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "详情")
    @TableField("detail")
    private String detail;

    @ApiModelProperty(value = "网址")
    @TableField("url")
    private String url;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private String createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "SpiderRecord{" +
        "id=" + id +
        ", type=" + type +
        ", title=" + title +
        ", detail=" + detail +
        ", createTime=" + createTime +
        "}";
    }

    public SpiderRecord() {
    }

    public SpiderRecord(String type, String title, String url) {
        this.type = type;
        this.title = title;
        this.url = url;
        this.createTime = DateUtil.now();
    }
}
