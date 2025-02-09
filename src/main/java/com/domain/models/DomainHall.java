package com.domain.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

public class DomainHall extends DomainBase {

    private String name;
    private Integer capacity;

    private String type;

    private List<DomainSession> sessions = new ArrayList<>();
    private List<DomainSeat> seats = new ArrayList<>();

}
