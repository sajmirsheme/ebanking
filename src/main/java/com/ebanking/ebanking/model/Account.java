package com.ebanking.ebanking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name ="ACCOUNTS")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String accountName;
    private String accountNumber;

    private BigDecimal balance;

    private String customerUsername;

    private String currency;



}
