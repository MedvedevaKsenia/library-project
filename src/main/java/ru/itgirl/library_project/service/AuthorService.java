package ru.itgirl.library_project.service;

import ru.itgirl.library_project.dto.AuthorDto;

public interface AuthorService {
    AuthorDto getAuthorById(Long id);

    AuthorDto getAuthorBySurnameV1(String surname);

    AuthorDto getAuthorBySurnameV2(String surname);
}