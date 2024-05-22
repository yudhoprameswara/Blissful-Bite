package com.BlissfulBite.BlissfulBite.repository;


import com.BlissfulBite.BlissfulBite.dto.outlet.OutletListDTO;
import com.BlissfulBite.BlissfulBite.entity.Outlet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OutletRepository  extends JpaRepository<Outlet,Long> {

    @Query("""
            SELECT new com.BlissfulBite.BlissfulBite.dto.outlet.OutletListDTO (
                out.id,
                out.name,
                out.city,
                out.address,
                out.contact,
                out.linkAddress
            )
            FROM Outlet AS out
            """)
    Page<OutletListDTO> getOutletList(Pageable pageable);
}
