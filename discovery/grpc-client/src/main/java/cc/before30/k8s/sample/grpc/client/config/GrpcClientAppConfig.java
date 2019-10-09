package cc.before30.k8s.sample.grpc.client.config;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * GrpcClientAppConfig
 *
 * @author before30
 * @since 2019/10/10
 */

@Configuration
public class GrpcClientAppConfig {
    @Value("${grpc.server.host:localhost}")
    public String host;

    @Value("${grpc.server.port:6565}")
    public int port;

    @Bean
    public ManagedChannel channel() {
        return ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
    }
}
