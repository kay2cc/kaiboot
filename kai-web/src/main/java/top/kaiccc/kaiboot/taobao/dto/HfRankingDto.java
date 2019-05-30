package top.kaiccc.kaiboot.taobao.dto;

/**
 * @author kk
 * @Package top.kaiccc.kaiboot.taobao.dto
 * @date 2019/5/30 16:50
 */
public class HfRankingDto {
    private String id;

    /**
     * 店铺id
     */
    private String sellerId;

    /**
     * 店铺名称
     */
    private String sellerName;

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

    public HfRankingDto() {
    }

    private HfRankingDto(Builder builder) {
        id = builder.id;
        sellerId = builder.sellerId;
        sellerName = builder.sellerName;
        itemId = builder.itemId;
        title = builder.title;
        payNum = builder.payNum;
        url = builder.url;
        createTime = builder.createTime;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private String id;
        private String sellerId;
        private String sellerName;
        private String itemId;
        private String title;
        private Integer payNum;
        private String url;
        private String createTime;

        private Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder sellerId(String val) {
            sellerId = val;
            return this;
        }

        public Builder sellerName(String val) {
            sellerName = val;
            return this;
        }

        public Builder itemId(String val) {
            itemId = val;
            return this;
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder payNum(Integer val) {
            payNum = val;
            return this;
        }

        public Builder url(String val) {
            url = val;
            return this;
        }

        public Builder createTime(String val) {
            createTime = val;
            return this;
        }

        public HfRankingDto build() {
            return new HfRankingDto(this);
        }
    }
}
