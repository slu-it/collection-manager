package collectionmanager.api.movies;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.hateoas.ResourceSupport;

import lombok.Data;


@Data
class MovieDto extends ResourceSupport {

    @NotEmpty
    private String name;

    @Min(0L)
    @Max(10L)
    private int rating;

}
