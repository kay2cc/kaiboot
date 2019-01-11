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
@Table(name = "kai_taobao_code")
public class Code {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", length = 36)
    private String id;

    /**
     * 秀-业务记录ID
     */
    private String codeId;
    /**
     * 标题
     */
    @Column(length = 2000)
    private String title;
    /**
     * 宝贝链接
     */
    private String targetUrl;

    /**
     * 店铺id
     */
    private String sellerId;

    /**
     * 店铺名称
     */
    private String sellerName;

}
