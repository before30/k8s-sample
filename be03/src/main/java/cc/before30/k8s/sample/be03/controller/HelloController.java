package cc.before30.k8s.sample.be03.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloController
 *
 * @author before30
 * @since 2020/07/31
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "be03";
    }
}
