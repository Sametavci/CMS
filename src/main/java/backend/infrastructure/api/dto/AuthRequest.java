package backend.infrastructure.api.dto;

import lombok.Data;

@Data
public class AuthRequest {
    String email;
    String password;
    String role;
}
