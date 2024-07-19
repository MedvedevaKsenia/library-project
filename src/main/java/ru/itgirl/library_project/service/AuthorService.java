package ru.itgirl.library_project.service;

import ru.itgirl.library_project.dto.AuthorCreateDto;
import ru.itgirl.library_project.dto.AuthorDto;
import ru.itgirl.library_project.dto.AuthorUpdateDto;

public interface AuthorService {
    AuthorDto getAuthorById(Long id);

    AuthorDto getAuthorByNameAndSurnameV1(String name, String surname);

    AuthorDto getAuthorByNameAndSurnameV2(String name, String surname);

    AuthorDto getAuthorByNameAndSurnameV3(String name, String surname);

    AuthorDto createAuthor(AuthorCreateDto authorCreateDto);

    AuthorDto updateAuthor(AuthorUpdateDto authorUpdateDto);
}