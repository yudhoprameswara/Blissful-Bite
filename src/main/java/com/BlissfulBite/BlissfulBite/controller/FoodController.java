package com.BlissfulBite.BlissfulBite.controller;

import com.BlissfulBite.BlissfulBite.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/food")
public class FoodController {
    @Autowired
    private FoodService service;

    @GetMapping("/index")
    public String getFood(@RequestParam(defaultValue = "") String name,
                          @RequestParam(required = false) String category,
                          Model model){
        var rows = service.getAll(name, category);
        var totalCart = service.totalCart();
        model.addAttribute("total",totalCart);
        model.addAttribute("name",name);
        model.addAttribute("grid",rows);
        return "food/index";
    }
    @GetMapping("/food-admin")
    public String foodAdmin(){
        return "edit/index";
    }


}
