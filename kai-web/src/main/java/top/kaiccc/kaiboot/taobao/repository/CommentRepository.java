package top.kaiccc.kaiboot.taobao.repository;

import org.springframework.data.repository.CrudRepository;
import top.kaiccc.kaiboot.taobao.entity.Comment;

/**
 * @author kaiccc
 * @date 2018-11-02 12:29
 */
public interface CommentRepository extends CrudRepository<Comment, String> {
    
}
