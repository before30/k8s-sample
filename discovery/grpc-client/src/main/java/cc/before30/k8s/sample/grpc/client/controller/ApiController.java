package cc.before30.k8s.sample.grpc.client.controller;

import cc.before30.k8s.sample.grpc.client.domain.EchoService;
import cc.before30.k8s.sample.grpc.common.EchoOuterClass;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import io.grpc.ManagedChannel;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    private final EchoService echoService;

    public ApiController(ManagedChannel channel,
                         EchoService echoService) {
        this.channel = channel;
        this.echoService = echoService;
    }

    @GetMapping("/hello-one/{message}")
    public String helloOne(@PathVariable("message") String message) {
        ListenableFuture future = echoService.echoToOneByNameResolver(message);
        Futures.addCallback(future, new FutureCallback<EchoOuterClass.Echo>() {
            @Override
            public void onSuccess(@NullableDecl EchoOuterClass.Echo result) {
                log.info("Success={}", result.getMessage());
            }

            @Override
            public void onFailure(Throwable t) {
                log.error("Fail={}", t.getMessage());
            }
        }, MoreExecutors.directExecutor());

        return "OK";
    }
}
