package top.kaiccc.kai4boot.ym.dto;

/**
 * @author kaiccc
 * @date 2018-12-10 15:49
 */
public class SubmitDto {

    /**
     * code : 0000
     * data : {"subscribeId":211010}
     * ok : true
     */

    private String code;
    private DataBean data;
    private boolean ok;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public static class DataBean {
        /**
         * subscribeId : 211010
         */

        private int subscribeId;

        public int getSubscribeId() {
            return subscribeId;
        }

        public void setSubscribeId(int subscribeId) {
            this.subscribeId = subscribeId;
        }
    }
}
