package com.BlissfulBite.BlissfulBite.dto.food;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddToCartDTO {
    private Long foodId;
    private LocalDateTime purchaseDate;

}
