package cc.before30.k8s.sample.be02;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Backend02Application
 *
 * @author before30
 * @since 08/10/2019
 */

@SpringBootApplication
@RestController
@Slf4j
public class Backend02Application {

    @GetMapping("/hello")
    public String hello(HttpServletRequest request) {
        log.info("request.addr={}", request.getRemoteHost());
        return "be02";
    }

    public static void main(String[] args) {
        SpringApplication.run(Backend02Application.class, args);
    }
}
