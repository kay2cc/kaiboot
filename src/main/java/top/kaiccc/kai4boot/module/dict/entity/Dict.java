package top.kaiccc.kai4boot.module.dict.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 数据字典表
 * </p>
 *
 * @author kaiccc
 * @since 2018-10-09
 */
@TableName("kaiccc_dict")
@ApiModel(value="Dict对象", description="数据字典表")
public class Dict implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId("id")
    private String id;

    @ApiModelProperty(value = "key")
    @TableField("key")
    private String key;

    @ApiModelProperty(value = "值")
    @TableField("value")
    private String value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Dict{" +
        "id=" + id +
        ", key=" + key +
        ", value=" + value +
        "}";
    }
}
