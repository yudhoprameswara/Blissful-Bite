package com.BlissfulBite.BlissfulBite.controller;

import com.BlissfulBite.BlissfulBite.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    private HistoryService service;

    @GetMapping("/index")
    public String getHistory(@RequestParam(defaultValue = "")String name,
                             @RequestParam(required = false) LocalDateTime startDate,
                             @RequestParam(required = false) LocalDateTime endDate,
                             @RequestParam(defaultValue = "1") Integer page,
                             Model model){
        var rows = service.GetList(name,startDate,endDate,page);
        var total = service.getTotalPrice(name,startDate,endDate);
        model.addAttribute("startDate",startDate);
        model.addAttribute("endDate",endDate);
        model.addAttribute("name",name);
        model.addAttribute("totalPages", rows.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("grid",rows);
        model.addAttribute("total",total);
        return "history/index";
    }
}
