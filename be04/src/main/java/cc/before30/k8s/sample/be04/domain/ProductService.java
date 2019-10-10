package cc.before30.k8s.sample.be04.domain;

import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * ProductService
 *
 * @author before30
 * @since 2019/10/10
 */

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    public Iterable<Product> findAll() {
        return repository.findAll();
    }

    public Product increaseProudctCount(Long id, Integer count) {
        Optional<Product> product = findById(id);
        product.ifPresent(p -> {
            Integer c = Math.max(0, p.getCount() + count);
            p.setCount(c);
            repository.save(p);
        });

        return product.get();
    }

    public Product decreaseProudctCount(Long id, Integer count) {
        Optional<Product> product = findById(id);
        product.ifPresent(p -> {
            Integer c = Math.max(0, p.getCount() - count);
            p.setCount(c);
            repository.save(p);
        });

        return product.get();
    }

    public void deleteOne(Long id) {
        repository.deleteById(id);
    }

    public Product insertOne(Product product) {
        return repository.save(product);
    }
}
