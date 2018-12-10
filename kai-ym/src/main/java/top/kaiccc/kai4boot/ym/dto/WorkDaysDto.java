package top.kaiccc.kai4boot.ym.dto;

import java.util.List;

/**
 * @author kaiccc
 * @date 2018-12-10 14:54
 */
public class WorkDaysDto {

    /**
     * code : 0000
     * data : {"dateList":["2018-12-12","2018-12-14","2018-12-19","2018-12-21","2018-12-26","2018-12-28","2019-01-02","2019-01-04","2019-01-09"],"subscribeDays":30}
     * ok : true
     */

    private String code;
    private WorkDays data;
    private boolean ok;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public WorkDays getData() {
        return data;
    }

    public void setData(WorkDays data) {
        this.data = data;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public static class WorkDays {
        /**
         * dateList : ["2018-12-12","2018-12-14","2018-12-19","2018-12-21","2018-12-26","2018-12-28","2019-01-02","2019-01-04","2019-01-09"]
         * subscribeDays : 30
         */

        private int subscribeDays;
        private List<String> dateList;

        public int getSubscribeDays() {
            return subscribeDays;
        }

        public void setSubscribeDays(int subscribeDays) {
            this.subscribeDays = subscribeDays;
        }

        public List<String> getDateList() {
            return dateList;
        }

        public void setDateList(List<String> dateList) {
            this.dateList = dateList;
        }
    }
}
