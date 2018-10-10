package top.kaiccc.kai4boot.module.spider.service.impl;

import top.kaiccc.kai4boot.module.spider.entity.SpiderConfig;
import top.kaiccc.kai4boot.module.spider.mapper.SpiderConfigMapper;
import top.kaiccc.kai4boot.module.spider.service.ISpiderConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 爬虫配置表 服务实现类
 * </p>
 *
 * @author kaiccc
 * @since 2018-10-10
 */
@Service
public class SpiderConfigServiceImpl extends ServiceImpl<SpiderConfigMapper, SpiderConfig> implements ISpiderConfigService {

}
