package top.kaiccc.kai4boot.ym.dto;

import java.util.List;

/**
 * @author kaiccc
 * @date 2018-12-10 14:17
 */
public class VaccinesDto {

    /**
     * code : 0000
     * data : [{"code":"8801","name":"二价人乳头瘤病毒疫苗","intro":"人类乳头瘤病毒疫苗（HPV疫苗）是一种预防宫颈癌疾病的疫苗。二价HPV疫苗可以预防人乳头瘤病毒16、18亚型两种高危亚型感染，对女性预防宫颈癌有较好的效果。","id":121,"price":61000,"total":31,"isSeckill":0},{"code":"8802","name":"四价人乳头瘤病毒疫苗","intro":"人类乳头瘤病毒疫苗（HPV疫苗）是一种预防宫颈癌疾病的疫苗。四价HPV疫苗可预防6、11、16、18亚型病毒引起的尖锐湿疣和宫颈癌。","id":156,"price":82800,"total":0,"isSeckill":0},{"code":"0202","name":"国产20μg重组乙肝（酿酒酵母）","intro":"乙型病毒性肝炎是由乙肝病毒(HBV)引起的消化系统传染病。接种乙肝疫苗可以预防感染乙型病毒性肝炎。","id":105,"price":11400,"total":-1,"isSeckill":0},{"code":"1201","name":"麻腮风减毒活疫苗","intro":"麻疹、流行性腮腺炎、风疹分别是由麻疹流行性腮腺炎风疹病毒引起的急性传染病。接种其可产生对麻疹、腮腺炎病毒和风疹病毒的免疫力。","id":1966,"price":10600,"total":0,"isSeckill":0},{"code":"0202","name":"60μg重组乙肝疫苗","intro":"乙型病毒性肝炎是由乙肝病毒(HBV)引起的消化系统传染病。接种乙肝疫苗可以预防感染乙型病毒性肝炎。","id":106,"price":25000,"total":0,"isSeckill":0},{"code":"2501","name":"23价肺炎球菌多糖疫苗","intro":"肺炎疫苗用于免疫预防肺炎球菌疾病。本制剂包含了23种型别肺炎球菌多糖抗原，可有效预防88%的细菌性肺炎球菌疾病。接种2-3周后，产生相应的血清型抗体。其保护持久性至少可延续5年。  ","id":107,"price":23000,"total":3,"isSeckill":0},{"code":"2102","name":"流感病毒裂解疫苗","intro":"流行性感冒是由流感病毒引起的急性呼吸道传染病。接种本疫苗可刺激机体产生抗流感病毒的免疫力。","id":240,"price":15800,"total":50,"isSeckill":0}]
     * ok : true
     */

    private String code;
    private boolean ok;
    private List<Vaccines> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<Vaccines> getData() {
        return data;
    }

    public void setData(List<Vaccines> data) {
        this.data = data;
    }

    public static class Vaccines {
        /**
         * code : 8801
         * name : 二价人乳头瘤病毒疫苗
         * intro : 人类乳头瘤病毒疫苗（HPV疫苗）是一种预防宫颈癌疾病的疫苗。二价HPV疫苗可以预防人乳头瘤病毒16、18亚型两种高危亚型感染，对女性预防宫颈癌有较好的效果。
         * id : 121
         * price : 61000
         * total : 31
         * isSeckill : 0
         */

        private String code;
        private String name;
        private String intro;
        private int id;
        private int price;
        private int total;
        private int isSeckill;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getIsSeckill() {
            return isSeckill;
        }

        public void setIsSeckill(int isSeckill) {
            this.isSeckill = isSeckill;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "code='" + code + '\'' +
                    ", name='" + name + '\'' +
                    ", intro='" + intro + '\'' +
                    ", id=" + id +
                    ", price=" + price +
                    ", total=" + total +
                    ", isSeckill=" + isSeckill +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "VaccinesDto{" +
                "code='" + code + '\'' +
                ", ok=" + ok +
                ", data=" + data +
                '}';
    }
}
