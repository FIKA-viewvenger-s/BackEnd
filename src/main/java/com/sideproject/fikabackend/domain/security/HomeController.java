package com.sideproject.fikabackend.domain.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(){
        return "securityTest";// template> securityTest.html 으로 보내줌
    }
}

