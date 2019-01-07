package top.kaiccc.kaiboot.taobao.dto;

import lombok.Data;

/**
 * @author kaiccc
 * @date 2019-01-07 10:46
 */
@Data
public class TaoBaoDto {
    /**
     * 内容code
     */
    String code;
    /**
     * 店铺id
     */
    String sellerId;

    /**
     * 店铺名称
     */
    String sellerName;

}
