package com.main.crud.repository;

import org.springframework.data.repository.CrudRepository;

import com.main.crud.model.User;

public interface UserRepository extends CrudRepository<User, Integer>{

    public Long countById(Integer id);
    
}