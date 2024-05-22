package com.BlissfulBite.BlissfulBite.service;

import com.BlissfulBite.BlissfulBite.dto.outlet.OutletListDTO;
import com.BlissfulBite.BlissfulBite.repository.OutletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class OutletService {

    @Autowired
    private OutletRepository outletRepository;

    public Page<OutletListDTO> getOutletList(Integer page){
        var pageable = PageRequest.of(page - 1, 10, Sort.by("id"));
        var rows=  outletRepository.getOutletList(pageable);
        return  rows;
    }
}
