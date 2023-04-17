package com.yaganaste.excercise5.dtos;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CustomerTransferDto {
    private long customerSenderId;
    private long customerReceiverId;
    private BigDecimal value;
}