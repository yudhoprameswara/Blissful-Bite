package com.BlissfulBite.BlissfulBite.controller;

import com.BlissfulBite.BlissfulBite.service.OutletService;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/outlet")
public class OutletController {

    @Autowired
    private OutletService service;

    @GetMapping("/index")
    public String getFood(@RequestParam(defaultValue = "1") Integer page,
                          Model model){
        var rows = service.getOutletList(page);
        model.addAttribute("grid",rows);
        model.addAttribute("totalPages", rows.getTotalPages());
        model.addAttribute("currentPage", page);
        return "outlet/index";
    }
}
