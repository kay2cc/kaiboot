package top.kaiccc.kaiboot.spider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.kaiccc.kaiboot.spider.entity.SpiderRecord;


/**
 * @author kaiccc
 * @date 2018-11-02 12:29
 */
public interface SpiderRecordRepository extends JpaRepository<SpiderRecord, String> {

    int countByUrlIs(String url);


}
