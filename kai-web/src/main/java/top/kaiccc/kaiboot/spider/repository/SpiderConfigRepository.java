package top.kaiccc.kaiboot.spider.repository;

import org.springframework.data.repository.CrudRepository;
import top.kaiccc.kaiboot.spider.entity.SpiderConfig;

import java.util.List;

/**
 * @author kaiccc
 * @date 2018-11-02 12:29
 */
public interface SpiderConfigRepository extends CrudRepository<SpiderConfig, String> {

    List<SpiderConfig> findAllByTypeIs(String type);

}
