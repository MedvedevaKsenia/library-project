package ru.itgirl.library_project.controller.rest;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itgirl.library_project.dto.UsersCreateDto;
import ru.itgirl.library_project.dto.UsersDto;
import ru.itgirl.library_project.dto.UsersUpdateDto;
import ru.itgirl.library_project.service.UsersService;

@SecurityRequirement(name = "library-users")
@RestController
@RequiredArgsConstructor
public class UsersRestController {
    private final UsersService usersService;

    @GetMapping("/users/{id}")
    UsersDto getUsersById(@PathVariable("id") Long id) {
        return usersService.getUsersById(id);
    }

    @PostMapping("/users/create")
    UsersDto createUsers(@RequestBody UsersCreateDto usersCreateDto) {
        return usersService.createUsers(usersCreateDto);
    }

    @PutMapping("/users/update")
    UsersDto updateUsers(@RequestBody UsersUpdateDto usersUpdateDto) {
        return usersService.updateUsers(usersUpdateDto);
    }

    @DeleteMapping("/users/delete/{id}")
    void deleteUsers(@PathVariable("id") Long id) {
        usersService.deleteUsers(id);
    }
}