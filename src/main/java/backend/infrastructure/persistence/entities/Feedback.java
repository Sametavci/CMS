package backend.infrastructure.persistence.entities;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class Feedback extends BaseEntity{

    private String clientName;
    private String movie;
    private String hall;
    private String description;

}
