package com.bank.bank.data.dto;

import com.bank.bank.OperationType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferMoneyDto {
    private String toNumber;
    private OperationType operationType;
    private BigDecimal amount;
}
