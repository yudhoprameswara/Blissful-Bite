package com.BlissfulBite.BlissfulBite.service;

import com.BlissfulBite.BlissfulBite.dto.account.UpsertAccountDTO;
import com.BlissfulBite.BlissfulBite.entity.Account;
import com.BlissfulBite.BlissfulBite.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired
    private AccountRepository accountRepository;

    public void save (UpsertAccountDTO dto){
        var entity = new Account();

        if (!accountRepository.findById(dto.getUsername()).isPresent()){
            entity.setUsername(dto.getUsername());

            if(dto.getPassword().equals(dto.getConfirmPassword())){
                entity.setPassword(dto.getPassword());
                accountRepository.save(entity);
            }

        }

    }
}
