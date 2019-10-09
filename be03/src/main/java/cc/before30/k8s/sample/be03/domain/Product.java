package cc.before30.k8s.sample.be03.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash(value = "product", timeToLive = 30)
@ToString
@Getter
public class Product implements Serializable {

    private static final long serialVersionUID = 3988119627925061796L;

    @Id
    private String id;
    private String name;

    @Builder
    public Product(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
