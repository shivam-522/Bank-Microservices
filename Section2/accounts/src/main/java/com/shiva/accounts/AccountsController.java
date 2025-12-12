package com.shiva.accounts;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsController {

    @GetMapping("sayHello")
    public String sayHello()
    {
        System.out.println("controller");
        return "Hi World";

    }
}
