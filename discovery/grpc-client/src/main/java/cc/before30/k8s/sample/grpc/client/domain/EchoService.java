package cc.before30.k8s.sample.grpc.client.domain;

import cc.before30.k8s.sample.grpc.common.EchoOuterClass;
import cc.before30.k8s.sample.grpc.common.EchoServiceGrpc;
import com.google.common.util.concurrent.ListenableFuture;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * EchoService
 *
 * @author before30
 * @since 2019/10/12
 */

@Service
public class EchoService {
    private final EurekaClient client;
    private final ManagedChannel roundRobinChannel;

    public EchoService(EurekaClient eurekaClient,
                       @Qualifier("roundRobinChannel") ManagedChannel roundRobinChannel) {
        this.client = eurekaClient;
        this.roundRobinChannel = roundRobinChannel;
    }

    public ListenableFuture echoToOne(String message) {
        InstanceInfo instanceInfo = client.getNextServerFromEureka("GRPC-SERVER", false);
        ManagedChannel channel = ManagedChannelBuilder.forAddress(instanceInfo.getIPAddr(),
                Integer.valueOf(instanceInfo.getMetadata().getOrDefault("grpc-port", "6565")))
                .usePlaintext()
                .build();
        EchoServiceGrpc.EchoServiceFutureStub stub = EchoServiceGrpc.newFutureStub(channel);
        return stub.echo(EchoOuterClass.Echo.newBuilder().setMessage(message).build());
    }

    public List<ListenableFuture<EchoOuterClass.Echo>> echoToAll(String message) {
        return client.getApplication("GRPC-SERVER").getInstances().stream()
                .map(it ->
                        ManagedChannelBuilder.forAddress(it.getIPAddr(),
                                Integer.valueOf(it.getMetadata().getOrDefault("grpc-port", "6556")))
                                .usePlaintext()
                                .build()
                ).map(channel -> {
                    EchoServiceGrpc.EchoServiceFutureStub stub = EchoServiceGrpc.newFutureStub(channel);
                    return stub.echo(EchoOuterClass.Echo.newBuilder().setMessage(message).build());
                }).collect(Collectors.toList());
    }

    public ListenableFuture echoToOneByNameResolver(String message) {
        EchoServiceGrpc.EchoServiceFutureStub stub = EchoServiceGrpc.newFutureStub(roundRobinChannel);
        return stub.echo(EchoOuterClass.Echo.newBuilder().setMessage(message).build());
    }
}
