package top.kaiccc.kai4boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.kaiccc.kai4boot.entity.SpiderRecord;

/**
 * @author kaiccc
 * @date 2018-11-02 12:29
 */
public interface SpiderRecordRepository extends JpaRepository<SpiderRecord, String> {

    int countByUrlIs(String url);


}
