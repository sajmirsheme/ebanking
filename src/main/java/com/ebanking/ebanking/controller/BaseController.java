package com.ebanking.ebanking.controller;

import com.ebanking.ebanking.model.Authority;
import com.ebanking.ebanking.model.MyMenu;
import com.ebanking.ebanking.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Objects;

@Controller
public class BaseController {


    //Inject me autowired sepse do kemi probleme kur te bejme extend tek trashgimia
    @Autowired
    private   MenuService menuService;

    public String ok(String viewName, Model model){

        String username=extractUsername();
        String userAuthority=extractAuthority();
        List<MyMenu> menus=menuService.retrieveMenusByAuthority(userAuthority);

        model.addAttribute("menu_list",menus);
        model.addAttribute("username",username);

        return viewName;

    }
    protected String extractUsername() {
        if (Objects.isNull(getPrincipal())) {
            return "";
        }

        return getPrincipal().getName();
    }

    protected String extractAuthority() {
        if (Objects.isNull(getPrincipal())) {
            return Authority.ANONYMOUS.getValue();
        }

        // Cast authority to GrantedAuthority
        GrantedAuthority grantedAuthority = (GrantedAuthority)
                getPrincipal().getAuthorities().toArray()[0];

        // Return role
        return grantedAuthority.getAuthority();
    }

    private UsernamePasswordAuthenticationToken getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }

        return (UsernamePasswordAuthenticationToken) authentication;
    }




}
