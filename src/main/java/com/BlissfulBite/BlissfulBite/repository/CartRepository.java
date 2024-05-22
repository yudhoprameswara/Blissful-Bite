package com.BlissfulBite.BlissfulBite.repository;

import com.BlissfulBite.BlissfulBite.dto.cart.CartDTO;
import com.BlissfulBite.BlissfulBite.dto.history.HistoryRowDTO;
import com.BlissfulBite.BlissfulBite.entity.Cart;
import com.BlissfulBite.BlissfulBite.entity.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.sql.In;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

    @Query("""
            SELECT COUNT(car.id)
            FROM Cart AS car
            WHERE car.purchaseDate IS NULL
            """)
    public Integer getTotalCart();

    @Query("""
            SELECT new com.BlissfulBite.BlissfulBite.dto.cart.CartDTO(
                fo.name,
                fo.price,
                COUNT(car.foodId),
                SUM(fo.price)
            )
            FROM Cart AS car
            JOIN Food AS fo ON car.foodId = fo.id
            WHERE car.purchaseDate IS NULL
            GROUP BY fo.name, fo.price, car.foodId
            """)
    public List<CartDTO> getCartList();

    @Query("""
            SELECT SUM(fo.price)
            FROM Cart AS car
            JOIN Food AS fo ON car.foodId = fo.id
            WHERE car.purchaseDate IS NULL
            """)
    public BigDecimal getTotalPrice ();

    @Query("""
            SELECT car.id
            FROM Cart AS car
            WHERE car.purchaseDate IS NULL
            """)
    public List<Long> getCartId();

    @Query("""
            SELECT car.id
            FROM Cart AS car
            JOIN Food AS fo ON car.foodId = fo.id
            WHERE
             fo.name = :name
             AND car.purchaseDate IS NULL
          
      
            """)
    public Page<Long> getOneByName(@Param("name")String name, Pageable pageable);

    @Query("""
            SELECT new com.BlissfulBite.BlissfulBite.dto.history.HistoryRowDTO(
                car.id,
                fo.name,
                fo.category,
                car.purchaseDate,
                fo.price
            )
            FROM Cart AS car
            JOIN Food AS fo ON car.foodId = fo.id
            WHERE car.purchaseDate IS NOT NULL
            AND fo.name LIKE %:name%
            AND (:startDate IS NULL OR car.purchaseDate >= :startDate )
            AND (:endDate IS NULL OR car.purchaseDate <= :endDate )
            """)
    public Page<HistoryRowDTO> getHistoryList(@Param("name")String name,
                                              @Param("startDate") LocalDateTime startDate,
                                              @Param("endDate")LocalDateTime endDate,
                                              Pageable pageable);

    @Query("""
            SELECT
     
                SUM(fo.price)
            FROM Cart AS car
            JOIN Food AS fo ON car.foodId = fo.id
            WHERE car.purchaseDate IS NOT NULL
            AND fo.name LIKE %:name%
            AND (:startDate IS NULL OR car.purchaseDate >= :startDate )
            AND (:endDate IS NULL OR car.purchaseDate <= :endDate )
           
            """)
    public BigDecimal getTotalHistoryPrice(@Param("name")String name,
                                           @Param("startDate")LocalDateTime startDate,
                                           @Param("endDate")LocalDateTime endDate);
}
