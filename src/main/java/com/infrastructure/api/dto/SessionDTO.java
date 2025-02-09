package com.infrastructure.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionDTO extends BaseDTO
{
    private LocalDateTime time;
    private MovieDTO movieDTO;
    private HallDTO hallDTO;
    private double price;
}
