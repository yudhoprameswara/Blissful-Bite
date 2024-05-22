package com.BlissfulBite.BlissfulBite.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartDTO {
    private String name;
    private BigDecimal price;
    private Long total;
    private BigDecimal totalPrice;
}
