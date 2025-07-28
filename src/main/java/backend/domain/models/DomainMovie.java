package backend.domain.models;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DomainMovie extends DomainBase {

    private String title;
    private String genre;
    private Integer duration;
    private String imgUrl;


}
