package com.progresssoft.deals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FXDealController {

    @GetMapping("/")
    public String sendMessage(@RequestParam("message") String message) {
        return "Hire Me";
    }
}
