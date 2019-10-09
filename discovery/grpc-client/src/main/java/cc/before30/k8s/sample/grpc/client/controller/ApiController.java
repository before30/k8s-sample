package cc.before30.k8s.sample.grpc.client.controller;

import cc.before30.home.grpc.proto.GreeterGrpc;
import cc.before30.home.grpc.proto.GreeterOuterClass;
import io.grpc.ManagedChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * ApiController
 *
 * @author before30
 * @since 2019/10/10
 */

@RestController
@Slf4j
public class ApiController {
    private final ManagedChannel channel;

    public ApiController(ManagedChannel channel) {
        this.channel = channel;
    }

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name) {

        GreeterGrpc.GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(channel);
        GreeterOuterClass.HelloReply helloReply = stub.sayHello(GreeterOuterClass.HelloRequest.newBuilder().setName(name).build());
        log.info("Reply={}", helloReply);

        return helloReply.getMessage();
    }
}
