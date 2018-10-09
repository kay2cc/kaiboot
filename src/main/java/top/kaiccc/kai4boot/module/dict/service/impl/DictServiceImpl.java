package top.kaiccc.kai4boot.module.dict.service.impl;

import top.kaiccc.kai4boot.module.dict.entity.Dict;
import top.kaiccc.kai4boot.module.dict.mapper.DictMapper;
import top.kaiccc.kai4boot.module.dict.service.IDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 数据字典表 服务实现类
 * </p>
 *
 * @author kaiccc
 * @since 2018-10-09
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {

}
