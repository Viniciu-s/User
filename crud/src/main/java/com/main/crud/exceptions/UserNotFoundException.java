package com.main.crud.exceptions;


// Este arquivo é uma definição de classe Java que estende a classe Throwable, 
//que é a classe base para todas as exceções em Java. A classe 
//UserNotFoundException é usada para sinalizar que um usuário não foi encontrado no banco de dados.

public class UserNotFoundException extends Throwable {
    // Este construtor é responsável por criar uma nova instância de
    // UserNotFoundException com a mensagem de erro fornecida como argumento.
    public UserNotFoundException(String message) {
        super(message);
    }
}
