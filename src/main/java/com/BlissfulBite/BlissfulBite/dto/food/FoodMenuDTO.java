package com.BlissfulBite.BlissfulBite.dto.food;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodMenuDTO {
    private long id;
    private String name;
    private BigDecimal price;
    private String imagePath;
    private String description;
}
