package com.ebanking.ebanking.model.model.request;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
public class CreateAccountRequest {


    private String accountName;

    private BigDecimal balance;

    private String currency;



}
