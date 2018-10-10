package top.kaiccc.kai4boot.module.spider.service.impl;

import top.kaiccc.kai4boot.module.spider.entity.SpiderRecord;
import top.kaiccc.kai4boot.module.spider.mapper.SpiderRecordMapper;
import top.kaiccc.kai4boot.module.spider.service.ISpiderRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 爬虫记录表 服务实现类
 * </p>
 *
 * @author kaiccc
 * @since 2018-10-10
 */
@Service
public class SpiderRecordServiceImpl extends ServiceImpl<SpiderRecordMapper, SpiderRecord> implements ISpiderRecordService {

}
