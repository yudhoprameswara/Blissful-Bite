package com.BlissfulBite.BlissfulBite.dto.account;

import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpsertAccountDTO {

    @Max(value = 100,message = "Username cannot be more than 100 Character")
    private String username;

    @Max(value = 100,message = "Password cannot be more than 100 Character")
    private String password;

    @Max(value = 100,message = "Password cannot be more than 100 Character")
    private String confirmPassword;
}
