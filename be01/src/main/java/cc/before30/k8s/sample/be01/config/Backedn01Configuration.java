package cc.before30.k8s.sample.be01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Backedn01Configuration
 *
 * @author before30
 * @since 08/10/2019
 */
@Configuration
public class Backedn01Configuration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
