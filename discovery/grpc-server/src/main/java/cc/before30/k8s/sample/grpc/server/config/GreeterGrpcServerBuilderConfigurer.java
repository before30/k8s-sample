package cc.before30.k8s.sample.grpc.server.config;

import io.grpc.ServerBuilder;
import org.lognet.springboot.grpc.GRpcServerBuilderConfigurer;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

/**
 * MyGrpcServerBuilderConfigurer
 *
 * @author before30
 */

@Component
public class GreeterGrpcServerBuilderConfigurer extends GRpcServerBuilderConfigurer {

    private final Executor executor;

    public GreeterGrpcServerBuilderConfigurer(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void configure(ServerBuilder<?> serverBuilder) {
        serverBuilder
                .executor(executor);
    }
}
