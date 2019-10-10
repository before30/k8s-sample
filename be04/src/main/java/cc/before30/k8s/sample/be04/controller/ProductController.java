package cc.before30.k8s.sample.be04.controller;

import cc.before30.k8s.sample.be04.domain.Product;
import cc.before30.k8s.sample.be04.domain.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Random;

/**
 * ProductController
 *
 * @author before30
 * @since 2019/10/10
 */

@RestController
@RequestMapping(value = "/api/product")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public Product showOne(@PathVariable(name = "id") Long id) {
        Optional<Product> product = service.findById(id);

        return product.get();
    }

    @GetMapping("all")
    public Iterable<Product> showAll() {
        return service.findAll();
    }

    @GetMapping("/create/{name}")
    public Product create(@PathVariable("name") String name) {
        Product product = new Product();
        product.setName(name);
        Random random = new Random();
        product.setCount(random.nextInt(1000));
        product.setPrice(random.nextInt(10000));

        return service.insertOne(product);
    }

    @PutMapping("{id}/increase/{count}")
    public Product increaseStock(@PathVariable(name = "id") Long id,
                                 @PathVariable(name = "count") Integer count) {
        return service.increaseProudctCount(id, count);
    }

    @PutMapping("{id}/decrease/{count}")
    public Product decreaseStock(@PathVariable(name = "id") Long id,
                                 @PathVariable(name = "count") Integer count) {
        return service.decreaseProudctCount(id, count);
    }

    @DeleteMapping("{id}")
    public void deleteOne(@PathVariable(name = "id") Long id) {
        service.deleteOne(id);
    }

}
