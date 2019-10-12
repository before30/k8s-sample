package cc.before30.k8s.sample.grpc.client;

import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
@Slf4j
public class GrpcClientApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(GrpcClientApplication.class, args);
    }

    private EurekaClient eurekaClient;

    public GrpcClientApplication(EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
    }

    @Override
    public void run(String... args) throws Exception {
        Application application = eurekaClient.getApplication("GRPC-SERVER");
        application.getInstances().stream().forEach(it ->
                log.info("{}", it.getMetadata().get("grpc-port")));

    }
}
