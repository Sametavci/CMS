package com.infrastructure.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "subs")
public class SubscriptedCustomer extends BaseEntity {
    @Column(name="mail", nullable = false, unique = true)
    private String mail;
    @Column(name = "age")
    private int age;
}
