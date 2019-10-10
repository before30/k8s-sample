package cc.before30.k8s.sample.grpc.client.controller;

import cc.before30.home.grpc.proto.GreeterGrpc;
import cc.before30.home.grpc.proto.GreeterOuterClass;
import cc.before30.k8s.sample.grpc.client.domain.GreeterService;
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
    private final GreeterService greeterService;

    public ApiController(ManagedChannel channel,
                         GreeterService greeterService) {
        this.channel = channel;
        this.greeterService = greeterService;
    }

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name) {

        GreeterGrpc.GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(channel);
        GreeterOuterClass.HelloReply helloReply = stub.sayHello(GreeterOuterClass.HelloRequest.newBuilder().setName(name).build());
        log.info("Reply={}", helloReply);

        return helloReply.getMessage();
    }

    @GetMapping("/v2/hello/{name}")
    public String hello2(@PathVariable("name") String name) {
        greeterService.greetOne(name);
        return "ok";
    }

    @GetMapping("v2/hello-all/{name}")
    public String hello2All(@PathVariable("name") String name) {
        greeterService.greetAll(name);

        return "ok";
    }
}
