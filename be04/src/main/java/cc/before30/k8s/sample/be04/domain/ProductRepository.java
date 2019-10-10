package cc.before30.k8s.sample.be04.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * ProductRepository
 *
 * @author before30
 * @since 2019/10/10
 */

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
