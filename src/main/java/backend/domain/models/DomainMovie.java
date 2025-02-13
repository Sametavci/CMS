package backend.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class DomainMovie extends DomainBase {

    private String title;
    private String genre;
    private Integer duration;
    private Double price;

}
