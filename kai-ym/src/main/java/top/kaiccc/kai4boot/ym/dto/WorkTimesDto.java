package top.kaiccc.kai4boot.ym.dto;

import java.util.List;

/**
 * @author kaiccc
 * @date 2018-12-10 15:34
 */
public class WorkTimesDto {

    /**
     * code : 0000
     * data : [{"createTime":"2017-12-13 13:37:20","depaCode":"5101151305","endTime":"11:30","id":363,"maxSub":20,"startTime":"08:30","tIndex":1,"workdayId":0,"yn":1}]
     * ok : true
     */

    private String code;
    private boolean ok;
    private List<WorkTimes> data;

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

    public List<WorkTimes> getData() {
        return data;
    }

    public void setData(List<WorkTimes> data) {
        this.data = data;
    }

    public static class WorkTimes {
        /**
         * createTime : 2017-12-13 13:37:20
         * depaCode : 5101151305
         * endTime : 11:30
         * id : 363
         * maxSub : 20
         * startTime : 08:30
         * tIndex : 1
         * workdayId : 0
         * yn : 1
         */

        private String createTime;
        private String depaCode;
        private String endTime;
        private int id;
        private int maxSub;
        private String startTime;
        private int tIndex;
        private int workdayId;
        private int yn;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDepaCode() {
            return depaCode;
        }

        public void setDepaCode(String depaCode) {
            this.depaCode = depaCode;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMaxSub() {
            return maxSub;
        }

        public void setMaxSub(int maxSub) {
            this.maxSub = maxSub;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public int getTIndex() {
            return tIndex;
        }

        public void setTIndex(int tIndex) {
            this.tIndex = tIndex;
        }

        public int getWorkdayId() {
            return workdayId;
        }

        public void setWorkdayId(int workdayId) {
            this.workdayId = workdayId;
        }

        public int getYn() {
            return yn;
        }

        public void setYn(int yn) {
            this.yn = yn;
        }
    }
}
