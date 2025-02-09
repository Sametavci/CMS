package com.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class DomainBase {
    protected Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
