package com.main.crud.repository;

import org.springframework.data.repository.CrudRepository;

import com.main.crud.model.User;

// Interface que estende CrudRepository para gerenciar a entidade User no banco de dados.
public interface UserRepository extends CrudRepository<User, Integer>{

    // Este método é responsável por contar o número de usuários com o ID fornecido.
    public Long countById(Integer id);
    
}