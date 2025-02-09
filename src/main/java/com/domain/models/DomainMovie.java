package com.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
    private List<DomainSession> sessions = new ArrayList<>();

}
