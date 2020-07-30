package cc.before30.k8s.sample.be04.domain;

import lombok.*;

/**
 * ProductDto
 *
 * @author before30
 * @since 2019/10/10
 */

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private String name;

    private Integer price;

    private Integer count;
}
