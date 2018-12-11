package top.kaiccc.kai4boot.spider.repository;

import org.springframework.data.repository.CrudRepository;
import top.kaiccc.kai4boot.spider.entity.SpiderConfig;

import java.util.List;

/**
 * @author kaiccc
 * @date 2018-11-02 12:29
 */
public interface SpiderConfigRepository extends CrudRepository<SpiderConfig, String> {

    List<SpiderConfig> findAllByTypeIs(String type);

}
