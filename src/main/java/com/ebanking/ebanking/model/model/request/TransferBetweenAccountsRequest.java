package com.ebanking.ebanking.model.model.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferBetweenAccountsRequest {

    private String fromAccountNumber;
    private String toAccountnumber;
    private BigDecimal amount;
    private String  currency;

}
