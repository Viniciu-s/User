package com.main.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.main.crud.exceptions.UserNotFoundException;
import com.main.crud.model.User;
import com.main.crud.service.UserService;

@Controller
public class UserController {

    // Injeta o UserService através da anotação @Autowired
    @Autowired
    private UserService service;

    // Mapeia a URL "/user" com o método GET e retorna a view "user"
    @GetMapping("/user")
    public String showUserList(Model model) {
        // Obtém a lista de usuários do UserService
        List<User> listUser = service.listAll();
        // Adiciona a lista de usuários ao model da view
        model.addAttribute("listUser", listUser);
        // Retorna o nome da view
        return "user";
    }

    // Mapeia a URL "/user/new" com o método GET e retorna a view "user_form"
    @GetMapping("/user/new")
    public String showNewForm(Model model) {
        // Adiciona um novo usuário vazio ao model da view
        model.addAttribute("user", new User());
        // Adiciona o título "Adicionar" ao model da view
        model.addAttribute("title", "Adicionar");
        // Retorna o nome da view
        return "user_form";
    }

    // Mapeia a URL "/user/save" com o método POST e salva o usuário
    @PostMapping("/user/save")
    public String saveUser(User user, RedirectAttributes ra) {
        // Salva o usuário no UserService
        service.save(user);
        // Adiciona uma mensagem flash de sucesso ao RedirectAttributes
        ra.addFlashAttribute("message", "Usuário salvo com sucesso");
        // Redireciona para a página "/user"
        return "redirect:/user";
    }

    // Trata exceções do tipo Exception
    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        // Adiciona a mensagem de erro ao model da view
        model.addAttribute("error", ex.getMessage());
        // Retorna o nome da view de erro
        return "error";
    }

    // Mapeia a URL "/user/edit/{id}" com o método GET e retorna a view "user_form"
    // com os dados do usuário a ser editado
    @GetMapping("/user/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            // Obtém o usuário a ser editado do UserService
            User user = service.get(id);
            // Adiciona o usuário ao model da view
            model.addAttribute("user", user);
            // Adiciona o título "Editar (ID: {id})" ao model da view
            model.addAttribute("title", "Editar (ID: " + id + ")");
            // Retorna o nome da view "user_form"
            return "user_form";
        } catch (UserNotFoundException e) {
            // Se o usuário não for encontrado, adiciona uma mensagem flash de erro ao
            // RedirectAttributes
            ra.addFlashAttribute("message", e.getMessage());
            // Redireciona para a página "/user"
            return "redirect:/user";
        }
    }

    // Este método é responsável por lidar com a solicitação GET de exclusão de um
    // usuário com o ID fornecido na URL.
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            // Chama o método de exclusão de usuário do serviço, passando o ID fornecido na
            // URL como argumento.
            service.delete(id);
            // Se o usuário foi excluído com sucesso, adiciona uma mensagem de sucesso aos
            // atributos flash para serem exibidos na próxima página.
            ra.addFlashAttribute("Usuário deletado com sucesso");
        } catch (UserNotFoundException e) {
            // Se o usuário não for encontrado, adiciona a mensagem de erro correspondente
            // aos atributos flash.
            ra.addFlashAttribute("message", e.getMessage());
        }
        // Redireciona o usuário para a página principal de usuários após a exclusão.
        return "redirect:/user";
    }
}