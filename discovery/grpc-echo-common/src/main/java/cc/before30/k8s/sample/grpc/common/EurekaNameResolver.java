package cc.before30.k8s.sample.grpc.common;

import com.netflix.discovery.EurekaClient;
import io.grpc.Attributes;
import io.grpc.EquivalentAddressGroup;
import io.grpc.NameResolver;
import org.apache.logging.log4j.util.Strings;

import java.net.InetSocketAddress;
import java.net.URI;
import java.util.List;
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

    public EurekaNameResolver(EurekaClient eurekaClient, URI targetUri, String portMetaKey) {
        this.eurekaClient = eurekaClient;
        this.serviceName = targetUri.getAuthority();
        this.portMetaKey = portMetaKey;
    }

    @Override
    public String getServiceAuthority() {
        return serviceName;
    }

    @Override
    public void start(Listener listener) {
        update(listener);
    }

    private void update(Listener listener) {
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

        listener.onAddresses(addresses, Attributes.EMPTY);
    }

    @Override
    public void shutdown() {

    }
}
