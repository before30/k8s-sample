package cc.before30.k8s.sample.grpc.common;

import com.netflix.discovery.EurekaClient;
import io.grpc.NameResolver;
import io.grpc.NameResolverProvider;

import java.net.URI;

/**
 * EurekaNameResolverProvider
 *
 * @author before30
 * @since 2019/10/11
 */
public class EurekaNameResolverProvider extends NameResolverProvider {

    private static final String EUREKA = "eureka";
    private EurekaClient eurekaClient;
    private final String portMetaKey;

    public EurekaNameResolverProvider(EurekaClient eurekaClient) {
        this(eurekaClient, "grpc_port");
    }

    public EurekaNameResolverProvider(EurekaClient eurekaClient, String portMetaKey) {
        this.eurekaClient = eurekaClient;
        this.portMetaKey = portMetaKey;
    }

    @Override
    protected boolean isAvailable() {
        return true;
    }

    @Override
    protected int priority() {
        return 6;
    }

    @Override
    public String getDefaultScheme() {
        return EUREKA;
    }

    @Override
    public NameResolver newNameResolver(URI targetUri, NameResolver.Args args) {
        return new EurekaNameResolver(eurekaClient, targetUri, portMetaKey);
    }
}
