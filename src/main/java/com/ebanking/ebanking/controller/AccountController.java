package com.ebanking.ebanking.controller;

import com.ebanking.ebanking.model.Account;
import com.ebanking.ebanking.model.model.request.CreateAccountRequest;
import com.ebanking.ebanking.repository.AccountRepository;
import com.ebanking.ebanking.service.AccountService;
import com.ebanking.ebanking.service.CurrencyService;
import com.ebanking.ebanking.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController extends BaseController {
    private final AccountService accountService;
    private final CurrencyService currencyService;

    //mund ta heqim Menuservice si dependency sepse e kemi future tek BaseController dhe i kemi bere extend
   // private final MenuService menuService;
    @GetMapping("")
    public String getMyAccountView(Model model){

      List<Account> accounts=  accountService.retreiveAllByCustomerUsername(extractUsername());
        model.addAttribute("accountList",accounts);
        return ok("Myaccounts",model);
    }


    @GetMapping("/details/{accountNumber}")
    public String  getAccountByAccountNumberView(@PathVariable String accountNumber, Model model){

        Account account=accountService.retrieveByAccountNumber(accountNumber,extractUsername());


        // first put account into model
        model.addAttribute("account",account);

        //shtimi i menus tek

      //  model.addAttribute("menu_list",menuService.retrieveMenusByAuthority("USER"));
        //rreshtin e siperm te kodit e bejme tek succes


     //this is the name of the file html file, .html file is not needed

       // return "accountDetails.html";
        return ok("accountDetails",model);

    }

    @GetMapping("/create")
  public String getCreateAccountView(Model model){

        model.addAttribute("CreateAccountRequest",new CreateAccountRequest());
        model.addAttribute("currencyList", currencyService.retrieveCurrencies());
        return ok("addaccount",model);
  }
    @PostMapping("/create")
    public String createAccount(@ModelAttribute  CreateAccountRequest request,Model model){


      String accountNumber=  accountService.createAccount(request,extractUsername());
      model.addAttribute("accountNumber",accountNumber);

        return ok("createAccountSuccess",model);
    }

}
