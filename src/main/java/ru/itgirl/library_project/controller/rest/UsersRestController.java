package ru.itgirl.library_project.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itgirl.library_project.dto.UsersCreateDto;
import ru.itgirl.library_project.dto.UsersDto;
import ru.itgirl.library_project.dto.UsersUpdateDto;
import ru.itgirl.library_project.service.UsersService;

@SecurityRequirement(name = "library-users")
@Tag(name = "Контроллер для управления пользователями", description = "Может выводить пользователя по ID, создать, удалить, изменить пользователя в БД")
@RestController
@RequiredArgsConstructor
public class UsersRestController {
    private final UsersService usersService;

    @GetMapping("/users/{id}")
    @Operation(summary = "Поиск пользователя по ID")
    UsersDto getUsersById(@PathVariable("id") Long id) {
        return usersService.getUsersById(id);
    }

    @PostMapping("/users/create")
    @Operation(summary = "Создание пользователя в БД")
    UsersDto createUsers(@RequestBody @Valid UsersCreateDto usersCreateDto) {
        return usersService.createUsers(usersCreateDto);
    }

    @PutMapping("/users/update")
    @Operation(summary = "Обновление информации пользователя в БД")
    UsersDto updateUsers(@RequestBody @Valid UsersUpdateDto usersUpdateDto) {
        return usersService.updateUsers(usersUpdateDto);
    }

    @DeleteMapping("/users/delete/{id}")
    @Operation(summary = "Удаление пользователя по ID")
    void deleteUsers(@PathVariable("id") Long id) {
        usersService.deleteUsers(id);
    }
}