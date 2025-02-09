package com.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DomainReservation extends DomainBase {


    private DomainSession session;

    private DomainCustomer customer;

    private DomainSeat seat;


}
