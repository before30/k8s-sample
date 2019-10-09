package cc.before30.k8s.sample.grpc.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

/**
 * ServerAppConfiguration
 *
 * @author before30
 */
@Configuration
public class GrpcServerAppConfiguration {
    @Bean
    public Executor executor() {
        return ForkJoinPool.commonPool();
    }

}
