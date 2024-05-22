package com.BlissfulBite.BlissfulBite.repository;

import com.BlissfulBite.BlissfulBite.dto.admin.FoodListDTO;
import com.BlissfulBite.BlissfulBite.dto.food.FoodMenuDTO;
import com.BlissfulBite.BlissfulBite.entity.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food,Long> {

    @Query("""
            SELECT new com.BlissfulBite.BlissfulBite.dto.food.FoodMenuDTO (
                fo.id, 
                fo.name,
                fo.price,
                fo.imagePath,
                fo.description
            )
            FROM Food AS fo
            WHERE fo.availability = true 
            AND fo.name LIKE %:name%
            AND (:category IS NULL OR :category = fo.category)
            """)
    List<FoodMenuDTO> getAll (@Param("name")String name,
                              @Param("category") String category);

    @Query("""
            SELECT new com.BlissfulBite.BlissfulBite.dto.admin.FoodListDTO(
                fo.id,
                fo.name,
                fo.availability
            )
            FROM Food AS fo
            WHERE fo.name LIKE %:name% 
            
            """)
    Page<FoodListDTO> getList (@Param("name")String name,
                               Pageable pageable);


    @Query("""
            SELECT fo.imagePath
            FROM Food AS fo
            WHERE fo.id = :id
            """)
    public String getImagePath(@Param("id") Long id);

    @Query("""
            SELECT fo.id
            FROM Food AS fo
            WHERE fo.name = :name         
           """)
    public Long getIdByName(@Param("name")String name);
}
