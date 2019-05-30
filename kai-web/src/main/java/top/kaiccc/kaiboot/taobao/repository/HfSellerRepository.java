package top.kaiccc.kaiboot.taobao.repository;

import org.springframework.data.repository.CrudRepository;
import top.kaiccc.kaiboot.taobao.entity.HfSeller;

/**
 * @author kaiccc
 * @date 2018-11-02 12:29
 */
public interface HfSellerRepository extends CrudRepository<HfSeller, String> {
    HfSeller findBySellerId(String sellerId);
}
