package com.BlissfulBite.BlissfulBite.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpsertFoodDTO {
    private Long id;

    private String name;

    private String category;

    private BigDecimal price;

    private Boolean availability;

    private String description;

    private MultipartFile image;

    private String imagePath;
}
