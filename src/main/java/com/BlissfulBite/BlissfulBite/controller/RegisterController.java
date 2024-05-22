package com.BlissfulBite.BlissfulBite.controller;

import com.BlissfulBite.BlissfulBite.dto.account.UpsertAccountDTO;
import com.BlissfulBite.BlissfulBite.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private RegisterService service;

    @GetMapping("/index")
    public String index()
    {
        var dto = new UpsertAccountDTO();
        return "register/index";
    }

    @PostMapping("/upsert")
    public String upsert(@ModelAttribute("dto") UpsertAccountDTO dto,Model model){
        service.save(dto);
        return  "redirect:/register/index";
    }
}
