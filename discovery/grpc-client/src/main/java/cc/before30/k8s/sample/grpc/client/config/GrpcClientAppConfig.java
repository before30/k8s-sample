package cc.before30.k8s.sample.grpc.client.config;

import cc.before30.k8s.sample.grpc.common.EurekaNameResolverProvider;
import com.netflix.discovery.EurekaClient;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * GrpcClientAppConfig
 *
 * @author before30
 * @since 2019/10/10
 */

@Configuration
public class GrpcClientAppConfig {

    @Value("${grpc.eureka.service-id}")
    private String eurekaServiceId;

    @Bean(name = "roundRobinChannel")
    @Primary
    public ManagedChannel roundRobinChannel(EurekaClient eurekaClient) {
        return ManagedChannelBuilder
                .forTarget("eureka://" + eurekaServiceId)
                .nameResolverFactory(new EurekaNameResolverProvider(eurekaClient, "grpc-port"))
                .defaultLoadBalancingPolicy("round_robin")
                .usePlaintext()
                .build();
    }

//    @Bean(name = "broadcastChannel")
//    public ManagedChannel broadcastChannel(EurekaClient eurekaClient) {
//        return ManagedChannelBuilder
//                .forTarget("eureka://" + eurekaServiceId)
//                .nameResolverFactory(new EurekaBroadcastNameResolverProvider(eurekaClient, "grpc-port"))
//                .usePlaintext()
//                .build();
//    }
}
