package cc.before30.k8s.sample.grpc.common;

import com.netflix.discovery.EurekaClient;
import com.sun.corba.se.spi.orbutil.threadpool.Work;
import io.grpc.Attributes;
import io.grpc.EquivalentAddressGroup;
import io.grpc.NameResolver;
import org.apache.logging.log4j.util.Strings;

import java.net.InetSocketAddress;
import java.net.URI;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * EurekaNameResolver
 *
 * @author before30
 * @since 2019/10/11
 */
public class EurekaNameResolver extends NameResolver {

    private final String serviceName;
    private final EurekaClient eurekaClient;
    private final String portMetaKey;
    private final ScheduledExecutorService scheduledExecutorService;

    public EurekaNameResolver(EurekaClient eurekaClient, URI targetUri, String portMetaKey) {
        this.eurekaClient = eurekaClient;
        this.serviceName = targetUri.getAuthority();
        this.portMetaKey = portMetaKey;
        this.scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    }

    @Override
    public String getServiceAuthority() {
        return serviceName;
    }

    @Override
    public void start(Listener2 listener) {
        Worker worker = new Worker(eurekaClient, serviceName, portMetaKey, listener);
        scheduledExecutorService.scheduleAtFixedRate(worker,1, 30, TimeUnit.SECONDS);
    }

    private static class Worker implements Runnable {
        private Listener2 listener2;
        private final EurekaClient eurekaClient;
        private final String serviceName;
        private final String portMetaKey;

        public Worker(
                EurekaClient eurekaClient, String serviceName,
                String portMetaKey, Listener2 listener2) {
            this.listener2 = listener2;
            this.eurekaClient = eurekaClient;
            this.serviceName = serviceName;
            this.portMetaKey = portMetaKey;
        }

        @Override
        public void run() {
            List<EquivalentAddressGroup> addresses = eurekaClient.getApplication(serviceName).getInstances().stream()
                    .map(i -> {
                        int port;
                        if (Strings.isNotEmpty(portMetaKey)) {
                            String s = i.getMetadata().get(portMetaKey);
                            port = Integer.parseInt(s);
                        } else {
                            port = i.getPort();
                        }
                        return new EquivalentAddressGroup(
                                new InetSocketAddress(i.getIPAddr(), port), Attributes.EMPTY
                        );
                    }).collect(Collectors.toList());

            listener2.onResult(ResolutionResult.newBuilder().setAddresses(addresses).build());
        }
    }

    @Override
    public void shutdown() {
        scheduledExecutorService.shutdown();
    }
}
