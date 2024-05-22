package com.BlissfulBite.BlissfulBite.dto.outlet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OutletListDTO {

    private Long id;
    private String name;
    private String city;
    private String address;
    private String contact;
    private String linkAddress;
}
