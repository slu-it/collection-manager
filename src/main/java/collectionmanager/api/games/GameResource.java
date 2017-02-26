package collectionmanager.api.games;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.hateoas.ResourceSupport;

import collectionmanager.business.games.Game;
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

    /** See {@link Game#name}. */
    @NotEmpty
    private String name;

    /** See {@link Game#platform}. */
    @NotEmpty
    private String platform;

    /** See {@link Game#rating}. */
    @Min(0L)
    @Max(10L)
    private Integer rating;

    /** See {@link Game#progress}. */
    @Min(0L)
    @Max(100L)
    private int progress;

    /** See {@link Game#done}. */
    private boolean done;

}
