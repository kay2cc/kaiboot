package top.kaiccc.kai4boot.ym.http;

import lombok.Data;

/**
 * @author kaiccc
 * @date 2018-12-08 16:41
 */
@Data
public class YmPost {

    /**
     * 部门 code 码
     */
    private String depaCode;
    /**
     * 部门 主id
     */
    private String departmentVaccineId;
    /**
     * 报名用户id  458515于   526102李
     */
    private String linkmanId;
    /**
     * 育苗 code  九价8803  二价8801
     */
    private String vaccineCode;
    /**
     * 育苗 第几针 1
     */
    private String vaccineIndex;
    /**
     * 预约日期  2018-12-10
     */
    private String subscribeDate;
    /**
     * 预约时间 1400
     */
    private String subscirbeTime;

    public static YmPost get(){
        YmPost post = new YmPost();
        post.setDepaCode("");
        post.setDepartmentVaccineId("");
        post.setLinkmanId("526102");
        post.setVaccineCode("8801");
        post.setVaccineIndex("1");


        return post;
    }

}
