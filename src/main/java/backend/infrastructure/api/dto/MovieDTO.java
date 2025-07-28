package backend.infrastructure.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO extends BaseDTO {
    private String title;
    private String genre;
    private int duration;
    private String imgUrl;
}
