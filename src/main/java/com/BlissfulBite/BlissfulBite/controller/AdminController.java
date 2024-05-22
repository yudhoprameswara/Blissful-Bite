package com.BlissfulBite.BlissfulBite.controller;

import com.BlissfulBite.BlissfulBite.dto.admin.UpsertFoodDTO;
import com.BlissfulBite.BlissfulBite.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService service;

    @GetMapping("/edit")
    public String editPage(@RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "" ) String name,
                           Model model){
        var rows = service.GetList(name,page);
        var dto = new UpsertFoodDTO();
        model.addAttribute("upsertDto",dto);
        model.addAttribute("name",name);
        model.addAttribute("grid",rows);
        model.addAttribute("totalPages", rows.getTotalPages());
        model.addAttribute("currentPage", page);
        return "edit/index";
    }

    @PostMapping("/upsert")
    public String upsert(@ModelAttribute("dto") UpsertFoodDTO dto, Model model){
            if(dto.getImage() != null){
                service.imageFileHandler(dto);
            }
            service.save(dto);
            return "redirect:/admin/edit";

    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long id, Model model, RedirectAttributes redirectAttributes){
//        var dependencies = service.getCountProductByTransaction(id);
//        if(dependencies == 0){
            service.delete(id);
            return "redirect:/admin/edit";
//        }
//        model.addAttribute("dependencies", dependencies);
//        return "merchandise/product-edit-message";
    }

}
