package ru.itgirl.library_project.service;

import ru.itgirl.library_project.dto.UsersCreateDto;
import ru.itgirl.library_project.dto.UsersDto;

public interface UsersService {
    UsersDto getUsersById(Long id);

    UsersDto createUsers(UsersCreateDto usersCreateDto);

    UsersDto updateUsers(UsersCreateDto )
}