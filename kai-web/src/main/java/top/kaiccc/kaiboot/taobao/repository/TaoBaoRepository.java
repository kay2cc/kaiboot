package top.kaiccc.kaiboot.taobao.repository;

import org.springframework.data.repository.CrudRepository;
import top.kaiccc.kaiboot.taobao.entity.Code;

/**
 * @author kaiccc
 * @date 2018-11-02 12:29
 */
public interface TaoBaoRepository extends CrudRepository<Code, String> {
    int countBySellerId(String sellerId);
}
