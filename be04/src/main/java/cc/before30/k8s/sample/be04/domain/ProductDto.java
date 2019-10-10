package cc.before30.k8s.sample.be04.domain;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

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

    @NotEmpty
    private String name;

    @Min(0)
    private Integer price;

    @Min(0)
    private Integer count;
}
