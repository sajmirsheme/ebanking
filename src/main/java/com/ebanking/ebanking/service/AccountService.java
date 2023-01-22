package com.ebanking.ebanking.service;

import com.ebanking.ebanking.model.Account;
import com.ebanking.ebanking.model.model.request.CreateAccountRequest;
import com.ebanking.ebanking.model.model.request.TransferBetweenAccountsRequest;
import com.ebanking.ebanking.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public List<Account> retreiveAllByCustomerUsername(String customerUsername){

        return accountRepository.findAllByCustomerUsername(customerUsername);

    }

    public Account retrieveByAccountNumber(String accountNumber,String username){

        return accountRepository.findByAccountNumberAndCustomerUsername(accountNumber,username).
                orElseThrow(()->new RuntimeException("ACCOUNT NOT FOUND"));

    }

    public String createAccount (CreateAccountRequest request,String username){
        Account account=Account.
                builder()
                .accountName(request.getAccountName())
                .balance(request.getBalance())
                .currency(request.getCurrency())
                .customerUsername(username)
                .accountNumber(generateAccountNumber())
                .build();

        accountRepository.save(account);

        return account.getAccountNumber();
    }

    private String generateAccountNumber(){
        Random random=new Random();
        String generatedaccountNumber=String.format("%04d",random.nextInt(10000));
      if(accountRepository.findByaccountNumber(generatedaccountNumber).isPresent())
      {
          return this.generateAccountNumber();
      }
        return generatedaccountNumber;

    }


    public void TransferbetwwenMyaccounts(TransferBetweenAccountsRequest request){
        Account fromaccount=accountRepository.findByaccountNumber(request.getFromAccountNumber())
                .orElseThrow(()->new RuntimeException("ACCOUNT NOT FOUND"));

        Account toaccount=accountRepository.findByaccountNumber(request.getToAccountnumber())
                .orElseThrow(()->new RuntimeException("ACCOUNT NUMBER DOES NOT EXSIST "));


        //KONTRLLOJME NQS BALANCA E LLOGARISE ESHTE ME E VOGEL SE SHUMA QE DUAM TE TRANSFEROJME
        if(fromaccount.getBalance().compareTo(request.getAmount())<0){
            throw new RuntimeException("BALANCE IS NOT ENOUGH");
        }
        //kontrollojme currencies
        if( ! fromaccount.getCurrency().equals(toaccount.getCurrency())){
            throw new RuntimeException("CURRENCIES DOES NOT MATCH");
        }

        //heqim leket nga llogaria qe dergon leket
        fromaccount.setBalance(fromaccount.getBalance().subtract(request.getAmount()));
        accountRepository.save(fromaccount);

        //shtojme leket tek llogaria qe merr leket
        toaccount.setBalance(toaccount.getBalance().add(request.getAmount()));
        accountRepository.save(toaccount);



    }
}



