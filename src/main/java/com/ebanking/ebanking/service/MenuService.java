package com.ebanking.ebanking.service;

import com.ebanking.ebanking.model.Authority;
import com.ebanking.ebanking.model.MyMenu;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    private static final List<MyMenu> ANONYMOUS_MENU=List.of(
            new MyMenu("/","Home"),
            new MyMenu("/contact","Contact"),
            new MyMenu("/login","Login")

    );

    private static final List<MyMenu> USER_MENU=List.of(
            new MyMenu("/","Home"),
            new MyMenu("/accounts","Accounts"),
            new MyMenu("/logout","Logout")

    );

    public List<MyMenu> retrieveMenusByAuthority(String authority) {
        return  Authority.USER.getValue().equals(authority)? USER_MENU: ANONYMOUS_MENU;
    }

}
