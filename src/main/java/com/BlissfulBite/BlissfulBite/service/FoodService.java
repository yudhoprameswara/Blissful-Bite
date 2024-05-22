package com.BlissfulBite.BlissfulBite.service;

import com.BlissfulBite.BlissfulBite.dto.food.AddToCartDTO;
import com.BlissfulBite.BlissfulBite.dto.food.FoodMenuDTO;
import com.BlissfulBite.BlissfulBite.entity.Cart;
import com.BlissfulBite.BlissfulBite.repository.CartRepository;
import com.BlissfulBite.BlissfulBite.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private CartRepository cartRepository;

    public List<FoodMenuDTO> getAll (String name, String category) {
        var rows = foodRepository.getAll(name,category);
        return rows;
    }

    public void addToCart(AddToCartDTO dto){
        var entity = new Cart();
        entity.setFoodId(dto.getFoodId());
        entity.setPurchaseDate(null);
        cartRepository.save(entity);
    }

    public Integer totalCart(){
        return  cartRepository.getTotalCart();
    }




}
