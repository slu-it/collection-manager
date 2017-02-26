package collectionmanager.api.games;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.hateoas.ResourceSupport;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GameResource extends ResourceSupport {

    @NotEmpty
    private String name;

    @Min(0L)
    @Max(10L)
    private int rating;

    private String platform;

    @Min(0L)
    @Max(100L)
    private int progress;

}
