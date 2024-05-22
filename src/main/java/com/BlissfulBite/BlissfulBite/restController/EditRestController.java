package com.BlissfulBite.BlissfulBite.restController;

import com.BlissfulBite.BlissfulBite.dto.admin.UpsertFoodDTO;
import com.BlissfulBite.BlissfulBite.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;

@CrossOrigin
@RestController
@RequestMapping("/api/food-edit")
public class EditRestController {

    @Autowired
    private AdminService service;

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable Long id){
        var dto = service.findOne(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

//    @PostMapping("/upsert")
//    public String upsert(@Valid @ModelAttribute("dto") UpsertFoodDTO dto, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
//
//        if(!bindingResult.hasErrors()){
//            service.imageFileHandler(dto);
//            service.save(dto);
//            return "redirect:/edit/index";
//        }
//        return  "edit/index";
//    }

    @PostMapping("/upsert")
    public String handleFileUpload(
            @RequestParam("textIdValue") Long id,
            @RequestParam("textValue") String name,
            @RequestParam("numericValue") BigDecimal price,
            @RequestParam("checkBoxValue") Boolean availability,
            @RequestParam("textareaValue") String description,
            @RequestPart("file") MultipartFile file
            ){
        UpsertFoodDTO dto = new UpsertFoodDTO();
        dto.setId(id);
        dto.setName(name);
        dto.setPrice(price);
        dto.setAvailability(availability);
        dto.setDescription(description);
        dto.setImage(file);

        service.imageFileHandler(dto);
        service.save(dto);
        return "redirect:/edit/index";

    }
}
