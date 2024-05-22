package com.BlissfulBite.BlissfulBite.service;

import com.BlissfulBite.BlissfulBite.dto.admin.FoodListDTO;
import com.BlissfulBite.BlissfulBite.dto.cart.CartDTO;
import com.BlissfulBite.BlissfulBite.entity.Cart;
import com.BlissfulBite.BlissfulBite.repository.CartRepository;
import com.BlissfulBite.BlissfulBite.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    FoodRepository foodRepository;

    public List<CartDTO> GetList(){
        var rows= cartRepository.getCartList();
        return rows;
    };

    public BigDecimal getTotalPrice(){
        return cartRepository.getTotalPrice();
    }

    public void purchase(){
        var cartIds = cartRepository.getCartId();
        for(var id :cartIds){
            var entity = cartRepository.findById(id).get();
            entity.setPurchaseDate(LocalDateTime.now());
            cartRepository.save(entity);
        }

    }

    public void deleteOneItem(String name) {

        var pageable = PageRequest.of(0,1);
        var selectedId = cartRepository.getOneByName(name,pageable);
        Long id = new Long(0);
        for (var ids : selectedId){
             id = ids;
        }
        if (selectedId != null) {
            cartRepository.deleteById(id);
        }
    }

    public void addOneItem(String name){
        var foodId = foodRepository.getIdByName(name);
        var entity = new Cart();
        entity.setFoodId(foodId);
        entity.setPurchaseDate(null);
        cartRepository.save(entity);
    }
}
