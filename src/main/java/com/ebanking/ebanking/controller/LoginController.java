package com.ebanking.ebanking.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class LoginController  extends BaseController {

    @GetMapping("/login")
    public String getLoginView(Model model){

       // return "login.html";
        return ok("login.html",model);


    }


}
