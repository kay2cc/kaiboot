package top.kaiccc.kaiboot.taobao.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import top.kaiccc.kaiboot.taobao.entity.Pics;

import java.util.List;

/**
 * @author kaiccc
 * @date 2018-11-02 12:29
 */
public interface PicsRepository extends CrudRepository<Pics, String> {
    List<Pics> findByCompleted(boolean status);


    @Transactional
    @Modifying
    @Query("update Pics pics set pics.completed=:completed where pics.id = :id")
    int updateCompletedById(@Param(value = "id") String id,
                            @Param(value = "completed") boolean completed);
}
