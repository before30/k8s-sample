package cc.before30.k8s.sample.be04.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Product
 *
 * @author before30
 * @since 2019/10/10
 */

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer price;

    private Integer count;

    public Product(final String name, final Integer price, final Integer count) {
        this.name = name;
        this.price = price;
        this.count = count;
    }

}
