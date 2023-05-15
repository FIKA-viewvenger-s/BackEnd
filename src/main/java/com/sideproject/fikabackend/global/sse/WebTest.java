package com.sideproject.fikabackend.global.sse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebTest {

    @GetMapping("/sse")
    public String main() {
        return "sse";
    }
}
