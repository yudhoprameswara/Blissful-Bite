package com.BlissfulBite.BlissfulBite.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodListDTO {
    private long id;
    private String name;
    private Boolean availability;
}
