package ru.itgirl.library_project.service;

import ru.itgirl.library_project.dto.AuthorCreateDto;
import ru.itgirl.library_project.dto.AuthorDto;

public interface AuthorService {
    AuthorDto getAuthorById(Long id);

    AuthorDto getAuthorByNameAndSurnameV1(String name, String surname);

    AuthorDto getAuthorByNameAndSurnameV2(String name, String surname);

    AuthorDto getAuthorByNameAndSurnameV3(String name, String surname);

    AuthorDto createAuthor(AuthorCreateDto authorCreateDto);
}