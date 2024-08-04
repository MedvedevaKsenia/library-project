package ru.itgirl.library_project.service;

import ru.itgirl.library_project.dto.UsersCreateDto;
import ru.itgirl.library_project.dto.UsersDto;
import ru.itgirl.library_project.dto.UsersUpdateDto;

import java.util.List;

public interface UsersService {
    UsersDto getUsersById(Long id);

    UsersDto createUsers(UsersCreateDto usersCreateDto);

    UsersDto updateUsers(UsersUpdateDto usersUpdateDto);

    void deleteUsers(Long id);

    List<UsersDto> getAllUsers();
}