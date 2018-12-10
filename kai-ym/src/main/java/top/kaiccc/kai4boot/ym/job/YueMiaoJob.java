package top.kaiccc.kai4boot.ym.job;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.google.gson.Gson;
import top.kaiccc.kai4boot.ym.dto.VaccinesDto;
import top.kaiccc.kai4boot.ym.dto.WorkDaysDto;
import top.kaiccc.kai4boot.ym.dto.WorkTimesDto;
import top.kaiccc.kai4boot.ym.http.YmConfig;

import java.util.List;

/**
 * @author kaiccc
 * @date 2018-12-08 16:17
 */
public class YueMiaoJob extends Thread {
    private static final Log log = LogFactory.get();
    private YmConfig config;

    @Override
    public void run() {
        try {
            log.debug("Thread Start !");
            for (String depaCode : config.getDepaCodeList()) {

                /*
                 * 取到 诊所育苗id vaccines.getId();
                 */
                HttpResponse vaccinesResponse = this.yuMiaoHttpResponse(config, StrUtil.format(config.getVaccinesUrl(), depaCode, ""));
                VaccinesDto vaccinesDto = new Gson().fromJson(vaccinesResponse.body(), VaccinesDto.class);
                if (vaccinesDto.getData().size() == 0 ){
                    log.error("{} 诊所没有九价预定，了解一下。", depaCode);
                }
                for (VaccinesDto.Vaccines vaccines : vaccinesDto.getData()) {
                    if (vaccines.getCode().equals(config.getVaccineCode())) {
                        /*
                         * 取 可预定时间
                         * workDays.do?depaCode={}&linkmanId={}&vaccCode={}&vaccIndex=1&departmentVaccineId={}
                         */
                        String workDaysUrl = StrUtil.format(config.getWorkDaysUrl(),
                                depaCode, config.getLinkmanId(), config.getVaccineCode(), config.getVaccineIndex(), vaccines.getId());

                        HttpResponse workDaysResponse = this.yuMiaoHttpResponse(config, workDaysUrl);

                        WorkDaysDto workDaysDto = new Gson().fromJson(workDaysResponse.body(), WorkDaysDto.class);
                        List<String> workDaysList = workDaysDto.getData().getDateList();
                        String workDay = workDaysList.get(workDaysList.size() - 1);
                        /*
                         * 取 预定具体时间
                         * depaCode={}&vaccCode={}&vaccIndex={}&subsribeDate={}&departmentVaccineId={}
                         */
                        String departmentWorkTimesUrl = StrUtil.format(config.getDepartmentWorkTimesUrl(),
                                depaCode, config.getVaccineCode(), config.getVaccineIndex(), workDay, vaccines.getId());

                        HttpResponse workTimesResponse = this.yuMiaoHttpResponse(config, departmentWorkTimesUrl);
                        WorkTimesDto workTimesDto = new Gson().fromJson(workTimesResponse.body(), WorkTimesDto.class);
                        int workTimes = workTimesDto.getData().get(0).getId();

                        /*
                         * 提交 预约订单
                         * add.do?depaCode={}&vaccineCode={}&vaccineIndex={}&linkmanId={}&subscribeDate={}&subscirbeTime={}&departmentVaccineId={}
                         */
                        String submitUrl = StrUtil.format(config.getSubmitUrl(),
                                depaCode, config.getVaccineCode(), config.getVaccineIndex(), config.getLinkmanId(), workDay, workTimes, vaccines.getId());
                        HttpResponse submitHttpResponse = this.yuMiaoHttpResponse(config, submitUrl);
                        log.info("提交成功，成不成功就看着一波了。 {}", submitHttpResponse.body());
                        log.debug("提交成功");
                    }
                }
            }
            log.debug("Thread End !");
        } catch (Exception e) {
            log.error(e);
        }
    }

    private HttpResponse yuMiaoHttpResponse(YmConfig config, String url) {
        log.debug("Ym Url : {}", url);
        HttpResponse httpResponse = HttpRequest.get(url)
                .timeout(20 * 60 * 1000)
                .header(config.getHeaders(), true)
                .cookie(config.getCookie())
                .execute();
        log.debug(httpResponse.toString());
        return httpResponse;
    }

    public YueMiaoJob(YmConfig config) {
        this.config = config;
    }

}
