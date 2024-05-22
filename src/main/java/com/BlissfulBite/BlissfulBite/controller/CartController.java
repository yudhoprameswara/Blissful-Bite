package com.BlissfulBite.BlissfulBite.controller;

import com.BlissfulBite.BlissfulBite.dto.admin.UpsertFoodDTO;
import com.BlissfulBite.BlissfulBite.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService service;

    @GetMapping("/index")
    public String cartPage(Model model){
        var rows = service.GetList();
        if (rows.size() == 0){
            rows = null;
        }

        var total = service.getTotalPrice();
        BigDecimal tax = new BigDecimal(0);
        BigDecimal finalPrice = new BigDecimal(0);

        if(total != null){
            tax = total.multiply(new BigDecimal(0.1));
            finalPrice = total.add(tax);
        }
        model.addAttribute("grid",rows);
        model.addAttribute("totalPrice",total);
        model.addAttribute("tax",tax);
        model.addAttribute("final",finalPrice);
        return "cart/index";
    }
    @GetMapping("/purchaseAll")
    public String purchaseCart(){
        service.purchase();
        return "cart/index";
    }

    @GetMapping("/deleteOne")
    public String deleteOne(@RequestParam String name){
        if(name != null) {
            service.deleteOneItem(name);
        }
        return "redirect:/cart/index";
    }

    @GetMapping("/addOne")
    public String addOne(@RequestParam String name){
        service.addOneItem(name);
        return "redirect:/cart/index";
    }
}
