package com.domain.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DomainSession extends DomainBase {

    private LocalDateTime startTime;

    private DomainMovie movie;

    private DomainHall hall;

    private Double price;

}

