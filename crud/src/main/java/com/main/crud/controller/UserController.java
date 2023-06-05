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

    @Autowired
    private UserService service;

    @GetMapping("/user")
    public String showUserList(Model model) {
        List<User> listUser = service.listAll();
        model.addAttribute("listUser", listUser);
        return "user";
    }

    @GetMapping("/user/new")
    public String showNewForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("title", "Adicionar");
        return "user_form";
    }

    @PostMapping("/user/save")
    public String saveUser(User user, RedirectAttributes ra) {
        service.save(user);
        ra.addFlashAttribute("message", "Usuário salvo com sucesso");
        return "redirect:/user";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error";
    }

    @GetMapping("/user/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            User user = service.get(id);
            model.addAttribute("user", user);
            model.addAttribute("title", "Editar (ID: " + id + ")");
            return "user_form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/user";
        }
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("Usuário deletado com sucesso");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/user";
    }
}