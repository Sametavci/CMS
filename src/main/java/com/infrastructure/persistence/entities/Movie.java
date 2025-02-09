package com.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "movies")
public class Movie extends BaseEntity {

    @Column(name = "movie_id")
    private String title;
    private String genre;
    private Integer duration;
    private Double price;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Session> sessions = new ArrayList<>();

}
