package com.BlissfulBite.BlissfulBite.restController;

import com.BlissfulBite.BlissfulBite.dto.food.AddToCartDTO;
import com.BlissfulBite.BlissfulBite.service.FoodService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin
@RestController
@RequestMapping("/api/menu")
public class MenuRestController {

    @Autowired
    private FoodService service;

    @PostMapping("/addToCart")
    public ResponseEntity<Object> addToCart (@Valid @RequestBody AddToCartDTO dto){
            service.addToCart(dto);
            return ResponseEntity.status(201).body("updated");
    }
}
