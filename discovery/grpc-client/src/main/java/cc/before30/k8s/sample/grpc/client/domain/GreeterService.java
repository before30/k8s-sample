package cc.before30.k8s.sample.grpc.client.domain;

import cc.before30.home.grpc.proto.GreeterGrpc;
import cc.before30.home.grpc.proto.GreeterOuterClass;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

/**
 * GreetService
 *
 * @author before30
 * @since 2019/10/11
 */

@Service
public class GreeterService {
    private final EurekaClient client;

    public GreeterService(EurekaClient client) {
        this.client = client;
    }

    public void greetOne(String name) {
        InstanceInfo instanceInfo = client.getNextServerFromEureka("GRPC-SERVER", false);
        ManagedChannel channel = ManagedChannelBuilder.forAddress(instanceInfo.getIPAddr(),
                Integer.valueOf(instanceInfo.getMetadata().getOrDefault("grpcport", "6565")))
                .usePlaintext()
                .build();
        GreeterGrpc.GreeterFutureStub stub = GreeterGrpc.newFutureStub(channel);
        stub.sayHello(GreeterOuterClass.HelloRequest.newBuilder().setName(name).build());
    }

    public void greetAll(String name) {
        client.getApplication("GRPC-SERVER").getInstances().stream()
                .map(it ->
                    ManagedChannelBuilder.forAddress(it.getIPAddr(),
                            Integer.valueOf(it.getMetadata().getOrDefault("grpcport", "6556")))
                            .usePlaintext()
                            .build()
                ).forEach(channel -> {
                    GreeterGrpc.GreeterFutureStub stub = GreeterGrpc.newFutureStub(channel);
                    stub.sayHello(GreeterOuterClass.HelloRequest.newBuilder().setName(name).build());
        });
    }
}
