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
@Table(name = "kai_hf_seller")
public class HfSeller {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", length = 36)
    private String id;

    /**
     * 店铺id
     */
    private String sellerId;

    /**
     * 店铺名称
     */
    private String sellerName;

}
