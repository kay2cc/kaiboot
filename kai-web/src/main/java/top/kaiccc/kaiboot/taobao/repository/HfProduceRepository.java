package top.kaiccc.kaiboot.taobao.repository;

import org.springframework.data.repository.CrudRepository;
import top.kaiccc.kaiboot.taobao.entity.HfProduct;

/**
 * @author kaiccc
 * @date 2018-11-02 12:29
 */
public interface HfProduceRepository extends CrudRepository<HfProduct, String> {
    HfProduct findFirstByHfSellerIdAndCreateTimeOrderByPayNumDesc(String sellerId, String time);
}
