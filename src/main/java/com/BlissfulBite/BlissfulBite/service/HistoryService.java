package com.BlissfulBite.BlissfulBite.service;

import com.BlissfulBite.BlissfulBite.dto.admin.FoodListDTO;
import com.BlissfulBite.BlissfulBite.dto.history.HistoryRowDTO;
import com.BlissfulBite.BlissfulBite.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class HistoryService {

    @Autowired
    private CartRepository cartRepository;

    public Page<HistoryRowDTO> GetList(String name, LocalDateTime startDate,LocalDateTime endDate, Integer page){
        var pageable = PageRequest.of(page - 1, 15, Sort.by("id"));
        var rows= cartRepository.getHistoryList(name,startDate,endDate,pageable);
        return rows;
    };

    public BigDecimal getTotalPrice(String name, LocalDateTime startDate, LocalDateTime endDate){
        return cartRepository.getTotalHistoryPrice(name,startDate,endDate);
    }
}
