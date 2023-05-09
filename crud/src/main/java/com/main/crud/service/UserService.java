package com.main.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.crud.exceptions.UserNotFoundException;
import com.main.crud.model.User;
import com.main.crud.repository.UserRepository;

@Service
public class UserService {

    // Injetando a dependência do UserRepository.
    @Autowired
    private UserRepository repo;

    // Método para recuperar todos os usuários do repositório.
    public List<User> listAll() {
        return (List<User>) repo.findAll();
    }

    // Este arquivo é uma definição de classe Java que contém métodos para manipular
    // usuários no banco de dados.
    public void save(User user) {
        // Salva o objeto User fornecido no banco de dados por meio do repositório.
        repo.save(user);
    }

    public User get(Integer id) throws UserNotFoundException {
        // Procura o usuário com o ID fornecido no banco de dados.
        Optional<User> result = repo.findById(id);
        // Se o usuário for encontrado, retorna-o.
        if (result.isPresent()) {
            return result.get();
        }
        // Se o usuário não for encontrado, lança uma exceção UserNotFoundException com
        // a mensagem de erro apropriada.
        throw new UserNotFoundException("Não foi possível encontrar o usuário com o ID " + id);
    }

    public void delete(Integer id) throws UserNotFoundException {
        // Conta o número de usuários com o ID fornecido no banco de dados.
        Long count = repo.countById(id);
        // Se não houver usuários com o ID fornecido, lança uma exceção
        // UserNotFoundException com a mensagem de erro apropriada.
        if (count == null || count == 0) {
            throw new UserNotFoundException("Não foi possível encontrar o usuário com o ID " + id);
        }
        // Caso contrário, exclui o usuário com o ID fornecido do banco de dados por
        // meio do repositório.
        repo.deleteById(id);
    }
}