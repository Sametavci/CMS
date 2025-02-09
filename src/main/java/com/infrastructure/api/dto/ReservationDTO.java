package com.infrastructure.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO extends BaseDTO{
    private SeatDTO seats;
    private CustomerDTO customer;
    private SessionDTO sessions;
    private Double price;
}
