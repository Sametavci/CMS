package backend.domain.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Getter
@Setter
public class DomainFeedback {

    private String clientName;
    private String movie;
    private String hall;
    private String description;

}
