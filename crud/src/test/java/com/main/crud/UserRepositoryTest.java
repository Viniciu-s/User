package com.main.crud;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.main.crud.model.User;
import com.main.crud.repository.UserRepository;

// Este arquivo é uma classe de teste JUnit que testa as operações CRUD do repositório 
//UserRepository. A anotação @DataJpaTest é usada para configurar o ambiente de teste do 
//Spring Data JPA.

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

    // O repositório UserRepository é injetado por meio da anotação @Autowired para
    // usar no teste.
    @Autowired
    private UserRepository repo;

    @Test
    public void testAddNew() {
        // Cria um novo objeto User.
        User user = new User();
        user.setEmail("email@email.com");
        user.setPassword("1234");
        user.setFirstname("Usuário");
        user.setLastname("Usuário");

        // Salva o objeto User no banco de dados por meio do repositório.
        User savedUser = repo.save(user);

        // Verifica se o objeto User foi salvo corretamente.
        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);

    }

    @Test
    public void testListAll() {
        // Busca todos os usuários do banco de dados por meio do repositório.
        Iterable<User> users = repo.findAll();
        // Verifica se há mais de um usuário no banco de dados.
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        // Exibe cada usuário encontrado no console.
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testUpdate() {
        // Define o ID do usuário que será atualizado.
        Integer userId = 4;
        // Busca o usuário com o ID fornecido no banco de dados por meio do repositório.
        Optional<User> OptionalUser = repo.findById(userId);
        // Verifica se o usuário foi encontrado.
        Assertions.assertThat(OptionalUser).isPresent();
        // Obtém o usuário encontrado.
        User user = OptionalUser.get();
        // Atualiza a senha do usuário.
        user.setPassword("salv");
        // Salva as alterações no banco de dados por meio do repositório.
        repo.save(user);

        // Obtém o usuário atualizado do banco de dados por meio do repositório.
        User updateUser = repo.findById(userId).get();
        // Verifica se a senha do usuário foi atualizada corretamente.
        Assertions.assertThat(updateUser.getPassword()).isEqualTo("salv");
    }

    @Test
    public void testGet() {
        // Define o ID do usuário que será buscado.
        Integer userId = 4;
        // Busca o usuário com o ID fornecido no banco de dados por meio do repositório.
        Optional<User> optionalUser = repo.findById(userId);
        // Verifica se o usuário foi encontrado.
        Assertions.assertThat(optionalUser).isPresent();
        // Exibe o usuário encontrado no console.
        System.out.println(optionalUser.get());
    }

    @Test
    public void testDelete() {
        // Define o ID do usuário que será excluído.
        Integer userId = 1;
        // Exclui o usuário com o ID fornecido do banco de dados por meio do
        // repositório.
        repo.deleteById(userId);

        // Busca o usuário com o ID fornecido no banco de dados por meio do repositório.
        Optional<User> optionalUser = repo.findById(userId);
        // Verifica se o usuário não foi encontrado após a exclusão.
        Assertions.assertThat(optionalUser).isNotPresent();
    }
}