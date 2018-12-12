package top.kaiccc.kai4boot.sldp.dto;

import java.util.List;

/**
 * @author kaiccc
 * @date 2018-12-11 18:31
 */
public class OrderListDto {

    private DataBean data;
    private String msg;
    private int code;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class DataBean {

        private String page;
        private String pageSize;
        private String count;
        private double pv;
        private double payment_amount;
        private int totalPage;
        private List<ListBean> list;

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public String getPageSize() {
            return pageSize;
        }

        public void setPageSize(String pageSize) {
            this.pageSize = pageSize;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public double getPv() {
            return pv;
        }

        public void setPv(double pv) {
            this.pv = pv;
        }

        public double getPayment_amount() {
            return payment_amount;
        }

        public void setPayment_amount(double payment_amount) {
            this.payment_amount = payment_amount;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * order_id : 60604
             * com_id : 0
             * order_no : AO613328780
             * order_type : 1
             * user_id : 71991
             * total_fee : 185.00
             * goods_cost : 185.00
             * payment_type : 5
             * payment_status : 1
             * currency_id : 5
             * payment_time : 2018-12-11 20:54:58
             * payment_amount : 185.00
             * receipt_province_id : 23
             * receipt_city_id : 283
             * receipt_district_id : 2697
             * receipt_man : 黄洁
             * receipt_tel : 18208185995
             * receipt_addr : 四川省内江市东兴区四川省内江市东兴区胜利镇政府
             * use_money : 185.00
             * gain_coupon : 0.00
             * gain_intergal : 0
             * is_receipt : 0
             * shop_id : 0
             * freight : 0.00
             * status : 0
             * express_id : 0
             * express_no :
             * express_fee : 0.00
             * commision_status : 0
             * PV : 0
             * produce_coin_amount : 0.00
             * present_safe_amount : 0.00
             * pay_code :
             * out_pay_code :
             * member_type : 0
             * category_type_id : 4
             * category_type_name : 耗材
             * pay_user_id : 0
             * pay_real_name : 黄洁
             * deliver_shop_ids :
             * third_trade_no :
             * remark :
             * buy_role : 3
             * create_time : 2018-12-11 20:54:38
             * update_time : 2018-12-11 20:54:58
             * buy_real_name : 黄洁
             * buy_invitation_code : 65183282
             * buy_mobile : 18208185995
             * shop_num : null
             * shop_name : null
             * sn : 1
             * checked : false
             * order_type_name : 零售单
             * goodsInfo : [{"order_detail_id":"135681","com_id":"0","order_id":"60604","user_id":"71991","goods_id":"259","goods_spec_id":"285","goods_name":"三零鼎品资质手册","spec_name":"三零鼎品资质手册","price":"25.00","quantity":"1","goods_cost":"25.00","default_img":"https://gsdp.oss-cn-hangzhou.aliyuncs.com/img_15385686581737.jpg","deliver_shop_id":"0","create_time":"2018-12-11 20:54:38","update_time":"2018-12-11 20:54:38"},{"order_detail_id":"135682","com_id":"0","order_id":"60604","user_id":"71991","goods_id":"261","goods_spec_id":"287","goods_name":"产品单页/折页套装10份","spec_name":"产品单页/折页套装10份","price":"50.00","quantity":"1","goods_cost":"50.00","default_img":"https://gsdp.oss-cn-hangzhou.aliyuncs.com/img_15385702452642.jpg","deliver_shop_id":"0","create_time":"2018-12-11 20:54:38","update_time":"2018-12-11 20:54:38"},{"order_detail_id":"135683","com_id":"0","order_id":"60604","user_id":"71991","goods_id":"263","goods_spec_id":"289","goods_name":"双面手拉旗10副","spec_name":"双面手拉旗10副","price":"50.00","quantity":"1","goods_cost":"50.00","default_img":"https://gsdp.oss-cn-hangzhou.aliyuncs.com/img_15385708764897.jpg","deliver_shop_id":"0","create_time":"2018-12-11 20:54:38","update_time":"2018-12-11 20:54:38"},{"order_detail_id":"135684","com_id":"0","order_id":"60604","user_id":"71991","goods_id":"264","goods_spec_id":"290","goods_name":"螺旋藻面纯包装礼盒（能可装12包面）","spec_name":"螺旋藻面纯包装礼盒（能可装12包面）","price":"8.00","quantity":"2","goods_cost":"8.00","default_img":"https://gsdp.oss-cn-hangzhou.aliyuncs.com/img_1538571716632.jpg","deliver_shop_id":"0","create_time":"2018-12-11 20:54:38","update_time":"2018-12-11 20:54:38"},{"order_detail_id":"135685","com_id":"0","order_id":"60604","user_id":"71991","goods_id":"265","goods_spec_id":"291","goods_name":"鸡蛋包装礼盒（不含蛋托和鸡蛋）","spec_name":"鸡蛋包装礼盒（不含蛋托和鸡蛋）","price":"8.00","quantity":"2","goods_cost":"8.00","default_img":"https://gsdp.oss-cn-hangzhou.aliyuncs.com/img_15385720683052.jpg","deliver_shop_id":"0","create_time":"2018-12-11 20:54:38","update_time":"2018-12-11 20:54:38"},{"order_detail_id":"135686","com_id":"0","order_id":"60604","user_id":"71991","goods_id":"267","goods_spec_id":"293","goods_name":"蛋托（能装10枚鸡蛋）","spec_name":"蛋托（能装10枚鸡蛋）","price":"1.00","quantity":"10","goods_cost":"1.00","default_img":"https://gsdp.oss-cn-hangzhou.aliyuncs.com/img_1538572460067.jpg","deliver_shop_id":"0","create_time":"2018-12-11 20:54:38","update_time":"2018-12-11 20:54:38"},{"order_detail_id":"135687","com_id":"0","order_id":"60604","user_id":"71991","goods_id":"269","goods_spec_id":"296","goods_name":"泡沫箱1个","spec_name":"泡沫箱1个（承重5kg）","price":"8.00","quantity":"1","goods_cost":"8.00","default_img":"https://gsdp.oss-cn-hangzhou.aliyuncs.com/img_15385740480728.jpg","deliver_shop_id":"0","create_time":"2018-12-11 20:54:38","update_time":"2018-12-11 20:54:38"},{"order_detail_id":"135688","com_id":"0","order_id":"60604","user_id":"71991","goods_id":"272","goods_spec_id":"303","goods_name":"手提塑料袋1卷（50个/卷）","spec_name":"手提塑料袋1卷（小袋，50个/卷）","price":"10.00","quantity":"1","goods_cost":"10.00","default_img":"https://gsdp.oss-cn-hangzhou.aliyuncs.com/img_1541756503303.jpg","deliver_shop_id":"0","create_time":"2018-12-11 20:54:38","update_time":"2018-12-11 20:54:38"}]
             * status_name : 待提货
             * payment_status_name : 已支付
             * payment_type_name : 消费积分
             * currency_name : 消费积分
             * deliver_type_name : 快递
             * pay_invitation_code : 65183282
             * express_name :
             */

            private String order_id;
            private String com_id;
            private String order_no;
            private String order_type;
            private String user_id;
            private String total_fee;
            private String goods_cost;
            private String payment_type;
            private String payment_status;
            private String currency_id;
            private String payment_time;
            private String payment_amount;
            private String receipt_province_id;
            private String receipt_city_id;
            private String receipt_district_id;
            private String receipt_man;
            private String receipt_tel;
            private String receipt_addr;
            private String use_money;
            private String gain_coupon;
            private String gain_intergal;
            private String is_receipt;
            private String shop_id;
            private String freight;
            private String status;
            private String express_id;
            private String express_no;
            private String express_fee;
            private String commision_status;
            private String PV;
            private String produce_coin_amount;
            private String present_safe_amount;
            private String pay_code;
            private String out_pay_code;
            private String member_type;
            private String category_type_id;
            private String category_type_name;
            private String pay_user_id;
            private String pay_real_name;
            private String deliver_shop_ids;
            private String third_trade_no;
            private String remark;
            private String buy_role;
            private String create_time;
            private String update_time;
            private String buy_real_name;
            private String buy_invitation_code;
            private String buy_mobile;
            private Object shop_num;
            private Object shop_name;
            private int sn;
            private boolean checked;
            private String order_type_name;
            private String status_name;
            private String payment_status_name;
            private String payment_type_name;
            private String currency_name;
            private String deliver_type_name;
            private String pay_invitation_code;
            private String express_name;
            private List<GoodsInfoBean> goodsInfo;

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getCom_id() {
                return com_id;
            }

            public void setCom_id(String com_id) {
                this.com_id = com_id;
            }

            public String getOrder_no() {
                return order_no;
            }

            public void setOrder_no(String order_no) {
                this.order_no = order_no;
            }

            public String getOrder_type() {
                return order_type;
            }

            public void setOrder_type(String order_type) {
                this.order_type = order_type;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getTotal_fee() {
                return total_fee;
            }

            public void setTotal_fee(String total_fee) {
                this.total_fee = total_fee;
            }

            public String getGoods_cost() {
                return goods_cost;
            }

            public void setGoods_cost(String goods_cost) {
                this.goods_cost = goods_cost;
            }

            public String getPayment_type() {
                return payment_type;
            }

            public void setPayment_type(String payment_type) {
                this.payment_type = payment_type;
            }

            public String getPayment_status() {
                return payment_status;
            }

            public void setPayment_status(String payment_status) {
                this.payment_status = payment_status;
            }

            public String getCurrency_id() {
                return currency_id;
            }

            public void setCurrency_id(String currency_id) {
                this.currency_id = currency_id;
            }

            public String getPayment_time() {
                return payment_time;
            }

            public void setPayment_time(String payment_time) {
                this.payment_time = payment_time;
            }

            public String getPayment_amount() {
                return payment_amount;
            }

            public void setPayment_amount(String payment_amount) {
                this.payment_amount = payment_amount;
            }

            public String getReceipt_province_id() {
                return receipt_province_id;
            }

            public void setReceipt_province_id(String receipt_province_id) {
                this.receipt_province_id = receipt_province_id;
            }

            public String getReceipt_city_id() {
                return receipt_city_id;
            }

            public void setReceipt_city_id(String receipt_city_id) {
                this.receipt_city_id = receipt_city_id;
            }

            public String getReceipt_district_id() {
                return receipt_district_id;
            }

            public void setReceipt_district_id(String receipt_district_id) {
                this.receipt_district_id = receipt_district_id;
            }

            public String getReceipt_man() {
                return receipt_man;
            }

            public void setReceipt_man(String receipt_man) {
                this.receipt_man = receipt_man;
            }

            public String getReceipt_tel() {
                return receipt_tel;
            }

            public void setReceipt_tel(String receipt_tel) {
                this.receipt_tel = receipt_tel;
            }

            public String getReceipt_addr() {
                return receipt_addr;
            }

            public void setReceipt_addr(String receipt_addr) {
                this.receipt_addr = receipt_addr;
            }

            public String getUse_money() {
                return use_money;
            }

            public void setUse_money(String use_money) {
                this.use_money = use_money;
            }

            public String getGain_coupon() {
                return gain_coupon;
            }

            public void setGain_coupon(String gain_coupon) {
                this.gain_coupon = gain_coupon;
            }

            public String getGain_intergal() {
                return gain_intergal;
            }

            public void setGain_intergal(String gain_intergal) {
                this.gain_intergal = gain_intergal;
            }

            public String getIs_receipt() {
                return is_receipt;
            }

            public void setIs_receipt(String is_receipt) {
                this.is_receipt = is_receipt;
            }

            public String getShop_id() {
                return shop_id;
            }

            public void setShop_id(String shop_id) {
                this.shop_id = shop_id;
            }

            public String getFreight() {
                return freight;
            }

            public void setFreight(String freight) {
                this.freight = freight;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getExpress_id() {
                return express_id;
            }

            public void setExpress_id(String express_id) {
                this.express_id = express_id;
            }

            public String getExpress_no() {
                return express_no;
            }

            public void setExpress_no(String express_no) {
                this.express_no = express_no;
            }

            public String getExpress_fee() {
                return express_fee;
            }

            public void setExpress_fee(String express_fee) {
                this.express_fee = express_fee;
            }

            public String getCommision_status() {
                return commision_status;
            }

            public void setCommision_status(String commision_status) {
                this.commision_status = commision_status;
            }

            public String getPV() {
                return PV;
            }

            public void setPV(String PV) {
                this.PV = PV;
            }

            public String getProduce_coin_amount() {
                return produce_coin_amount;
            }

            public void setProduce_coin_amount(String produce_coin_amount) {
                this.produce_coin_amount = produce_coin_amount;
            }

            public String getPresent_safe_amount() {
                return present_safe_amount;
            }

            public void setPresent_safe_amount(String present_safe_amount) {
                this.present_safe_amount = present_safe_amount;
            }

            public String getPay_code() {
                return pay_code;
            }

            public void setPay_code(String pay_code) {
                this.pay_code = pay_code;
            }

            public String getOut_pay_code() {
                return out_pay_code;
            }

            public void setOut_pay_code(String out_pay_code) {
                this.out_pay_code = out_pay_code;
            }

            public String getMember_type() {
                return member_type;
            }

            public void setMember_type(String member_type) {
                this.member_type = member_type;
            }

            public String getCategory_type_id() {
                return category_type_id;
            }

            public void setCategory_type_id(String category_type_id) {
                this.category_type_id = category_type_id;
            }

            public String getCategory_type_name() {
                return category_type_name;
            }

            public void setCategory_type_name(String category_type_name) {
                this.category_type_name = category_type_name;
            }

            public String getPay_user_id() {
                return pay_user_id;
            }

            public void setPay_user_id(String pay_user_id) {
                this.pay_user_id = pay_user_id;
            }

            public String getPay_real_name() {
                return pay_real_name;
            }

            public void setPay_real_name(String pay_real_name) {
                this.pay_real_name = pay_real_name;
            }

            public String getDeliver_shop_ids() {
                return deliver_shop_ids;
            }

            public void setDeliver_shop_ids(String deliver_shop_ids) {
                this.deliver_shop_ids = deliver_shop_ids;
            }

            public String getThird_trade_no() {
                return third_trade_no;
            }

            public void setThird_trade_no(String third_trade_no) {
                this.third_trade_no = third_trade_no;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getBuy_role() {
                return buy_role;
            }

            public void setBuy_role(String buy_role) {
                this.buy_role = buy_role;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public String getBuy_real_name() {
                return buy_real_name;
            }

            public void setBuy_real_name(String buy_real_name) {
                this.buy_real_name = buy_real_name;
            }

            public String getBuy_invitation_code() {
                return buy_invitation_code;
            }

            public void setBuy_invitation_code(String buy_invitation_code) {
                this.buy_invitation_code = buy_invitation_code;
            }

            public String getBuy_mobile() {
                return buy_mobile;
            }

            public void setBuy_mobile(String buy_mobile) {
                this.buy_mobile = buy_mobile;
            }

            public Object getShop_num() {
                return shop_num;
            }

            public void setShop_num(Object shop_num) {
                this.shop_num = shop_num;
            }

            public Object getShop_name() {
                return shop_name;
            }

            public void setShop_name(Object shop_name) {
                this.shop_name = shop_name;
            }

            public int getSn() {
                return sn;
            }

            public void setSn(int sn) {
                this.sn = sn;
            }

            public boolean isChecked() {
                return checked;
            }

            public void setChecked(boolean checked) {
                this.checked = checked;
            }

            public String getOrder_type_name() {
                return order_type_name;
            }

            public void setOrder_type_name(String order_type_name) {
                this.order_type_name = order_type_name;
            }

            public String getStatus_name() {
                return status_name;
            }

            public void setStatus_name(String status_name) {
                this.status_name = status_name;
            }

            public String getPayment_status_name() {
                return payment_status_name;
            }

            public void setPayment_status_name(String payment_status_name) {
                this.payment_status_name = payment_status_name;
            }

            public String getPayment_type_name() {
                return payment_type_name;
            }

            public void setPayment_type_name(String payment_type_name) {
                this.payment_type_name = payment_type_name;
            }

            public String getCurrency_name() {
                return currency_name;
            }

            public void setCurrency_name(String currency_name) {
                this.currency_name = currency_name;
            }

            public String getDeliver_type_name() {
                return deliver_type_name;
            }

            public void setDeliver_type_name(String deliver_type_name) {
                this.deliver_type_name = deliver_type_name;
            }

            public String getPay_invitation_code() {
                return pay_invitation_code;
            }

            public void setPay_invitation_code(String pay_invitation_code) {
                this.pay_invitation_code = pay_invitation_code;
            }

            public String getExpress_name() {
                return express_name;
            }

            public void setExpress_name(String express_name) {
                this.express_name = express_name;
            }

            public List<GoodsInfoBean> getGoodsInfo() {
                return goodsInfo;
            }

            public void setGoodsInfo(List<GoodsInfoBean> goodsInfo) {
                this.goodsInfo = goodsInfo;
            }

            public static class GoodsInfoBean {
                /**
                 * order_detail_id : 135681
                 * com_id : 0
                 * order_id : 60604
                 * user_id : 71991
                 * goods_id : 259
                 * goods_spec_id : 285
                 * goods_name : 三零鼎品资质手册
                 * spec_name : 三零鼎品资质手册
                 * price : 25.00
                 * quantity : 1
                 * goods_cost : 25.00
                 * default_img : https://gsdp.oss-cn-hangzhou.aliyuncs.com/img_15385686581737.jpg
                 * deliver_shop_id : 0
                 * create_time : 2018-12-11 20:54:38
                 * update_time : 2018-12-11 20:54:38
                 */

                private String order_detail_id;
                private String com_id;
                private String order_id;
                private String user_id;
                private String goods_id;
                private String goods_spec_id;
                private String goods_name;
                private String spec_name;
                private String price;
                private String quantity;
                private String goods_cost;
                private String default_img;
                private String deliver_shop_id;
                private String create_time;
                private String update_time;

                public String getOrder_detail_id() {
                    return order_detail_id;
                }

                public void setOrder_detail_id(String order_detail_id) {
                    this.order_detail_id = order_detail_id;
                }

                public String getCom_id() {
                    return com_id;
                }

                public void setCom_id(String com_id) {
                    this.com_id = com_id;
                }

                public String getOrder_id() {
                    return order_id;
                }

                public void setOrder_id(String order_id) {
                    this.order_id = order_id;
                }

                public String getUser_id() {
                    return user_id;
                }

                public void setUser_id(String user_id) {
                    this.user_id = user_id;
                }

                public String getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(String goods_id) {
                    this.goods_id = goods_id;
                }

                public String getGoods_spec_id() {
                    return goods_spec_id;
                }

                public void setGoods_spec_id(String goods_spec_id) {
                    this.goods_spec_id = goods_spec_id;
                }

                public String getGoods_name() {
                    return goods_name;
                }

                public void setGoods_name(String goods_name) {
                    this.goods_name = goods_name;
                }

                public String getSpec_name() {
                    return spec_name;
                }

                public void setSpec_name(String spec_name) {
                    this.spec_name = spec_name;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getQuantity() {
                    return quantity;
                }

                public void setQuantity(String quantity) {
                    this.quantity = quantity;
                }

                public String getGoods_cost() {
                    return goods_cost;
                }

                public void setGoods_cost(String goods_cost) {
                    this.goods_cost = goods_cost;
                }

                public String getDefault_img() {
                    return default_img;
                }

                public void setDefault_img(String default_img) {
                    this.default_img = default_img;
                }

                public String getDeliver_shop_id() {
                    return deliver_shop_id;
                }

                public void setDeliver_shop_id(String deliver_shop_id) {
                    this.deliver_shop_id = deliver_shop_id;
                }

                public String getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(String create_time) {
                    this.create_time = create_time;
                }

                public String getUpdate_time() {
                    return update_time;
                }

                public void setUpdate_time(String update_time) {
                    this.update_time = update_time;
                }
            }
        }
    }
}
