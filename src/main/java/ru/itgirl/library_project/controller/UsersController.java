package ru.itgirl.library_project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itgirl.library_project.dto.UsersDto;
import ru.itgirl.library_project.service.UsersService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;

    @GetMapping("/users")
    String getUsersView(Model model) {
        List<UsersDto> users = usersService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}