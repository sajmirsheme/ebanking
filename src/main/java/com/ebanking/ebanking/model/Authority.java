package com.ebanking.ebanking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Authority {

    USER("USER"),
    ANONYMOUS("ANONYMOUS");

    private  String value;
}
