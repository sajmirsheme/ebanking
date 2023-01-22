package com.ebanking.ebanking.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {

    private static final List<String > CURRENCIES= List.of("ALL","EUR","USD","BTC");


    public List<String> retrieveCurrencies(){
        return
                CURRENCIES;
    }
}
