package cc.before30.k8s.sample.be04.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HealthCheckController
 *
 * @author before30
 * @since 2019/10/10
 */

@RestController
public class HealthCheckController {

    @GetMapping("/hello")
    public String hello() {
        return "world";
    }
}
