package cc.before30.k8s.sample.be01;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * Backend01Application
 *
 * @author before30
 * @since 08/10/2019
 */

@SpringBootApplication
@RestController
@Slf4j
public class Backend01Application {

    private final RestTemplate restTemplate;

    public Backend01Application(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${k8s.be02.addr:localhost:8080}")
    private String addr;

    @GetMapping("/hello")
    public String hello(HttpServletRequest request) {
        log.info("request.addr={}", request.getRemoteHost());
        return "be01";
    }

    @GetMapping("/api/call/be02")
    public String callBackend02(HttpServletRequest request) {
        log.info("request.addr={}", request.getRemoteHost());
        ResponseEntity<String> resp = restTemplate.getForEntity("http://" + addr + "/hello", String.class);
        return resp.getBody();
    }

    public static void main(String[] args) {
        SpringApplication.run(Backend01Application.class, args);
    }

}
