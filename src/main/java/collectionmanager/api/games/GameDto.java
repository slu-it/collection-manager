package collectionmanager.api.games;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.hateoas.ResourceSupport;

import lombok.Data;


@Data
class GameDto extends ResourceSupport {

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
