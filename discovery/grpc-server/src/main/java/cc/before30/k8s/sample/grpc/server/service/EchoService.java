package cc.before30.k8s.sample.grpc.server.service;

import cc.before30.k8s.sample.grpc.common.EchoOuterClass;
import cc.before30.k8s.sample.grpc.common.EchoServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;

/**
 * EchoService
 *
 * @author before30
 * @since 2019/10/12
 */

@Slf4j
@GRpcService
public class EchoService extends EchoServiceGrpc.EchoServiceImplBase {
    @Override
    public void echo(EchoOuterClass.Echo request, StreamObserver<EchoOuterClass.Echo> responseObserver) {
        log.info("Request={}", request.getMessage());
        responseObserver.onNext(request);
        responseObserver.onCompleted();
        log.info("Response={}", request.getMessage());
    }
}
