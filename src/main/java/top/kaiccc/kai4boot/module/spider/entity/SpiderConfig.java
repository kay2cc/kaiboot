package top.kaiccc.kai4boot.module.spider.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 爬虫配置表
 * </p>
 *
 * @author kaiccc
 * @since 2018-10-10
 */
@TableName("kai_spider_config")
@ApiModel(value="SpiderConfig对象", description="爬虫配置表")
public class SpiderConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    @ApiModelProperty(value = "类型")
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "关键字")
    @TableField("search_key")
    private String searchKey;

    @ApiModelProperty(value = "网址")
    @TableField("url")
    private String url;

    @ApiModelProperty(value = "推送微信key")
    @TableField("wx_key")
    private String wxKey;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getWxKey() {
        return wxKey;
    }

    public void setWxKey(String wxKey) {
        this.wxKey = wxKey;
    }

    @Override
    public String toString() {
        return "SpiderConfig{" +
        "id=" + id +
        ", type=" + type +
        ", key=" + searchKey +
        ", url=" + url +
        ", wxKey=" + wxKey +
        "}";
    }
}
