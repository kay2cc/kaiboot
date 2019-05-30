package top.kaiccc.kaiboot.taobao.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author kaiccc
 * @date 2019-01-07 10:48
 */
@Data
@Entity
@Table(name = "kai_hf_product")
public class HfProduct {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", length = 36)
    private String id;
    /**
     * 店铺id 关联id
     */
    private String hfSellerId;

    /**
     * 商品id
     */
    private String itemId;

    /**
     * 名称
     */
    private String title;

    /**
     * 付款人数
     */
    private Integer payNum;

    /**
     * 商品地址
     */
    private String url;

    /**
     * 创建时间
     */
    private String createTime;

}
