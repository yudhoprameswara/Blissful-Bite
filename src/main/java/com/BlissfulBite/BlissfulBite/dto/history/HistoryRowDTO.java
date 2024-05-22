package com.BlissfulBite.BlissfulBite.dto.history;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoryRowDTO {
    private Long id;
    private String name;
    private String category;
    private LocalDateTime purchaseDate;
    private BigDecimal price;
}
