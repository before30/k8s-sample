package cc.before30.k8s.sample.be03.controller;

import cc.before30.k8s.sample.be03.domain.Product;
import cc.before30.k8s.sample.be03.domain.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.SplittableRandom;

@Slf4j
@RestController
public class ApiController {
    private final ProductRepository repository;
    private final RedisTemplate redisTemplate;

    public ApiController(ProductRepository repository, RedisTemplate redisTemplate) {
        this.repository = repository;
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/all")
    public List<Product> all() {
        List<Product> result = new ArrayList<Product>();
        repository.findAll().forEach(result::add);

        return result;
    }

    @GetMapping("/insert/{name}")
    public Product insert(@PathVariable("name") String name) {
        String randomId = createId();
        Product product = Product.builder()
                .id(randomId)
                .name(name)
                .build();

        log.info("insert product={}", product);
        repository.save(product);

        return product;
    }

    @GetMapping("/get/{id}")
    public Optional<Product> get (@PathVariable("id") String id) {
       return repository.findById(id);
    }

    private String createId() {
        SplittableRandom random = new SplittableRandom();
        return String.valueOf(random.nextInt(1, 1_000_000_000));
    }
}
