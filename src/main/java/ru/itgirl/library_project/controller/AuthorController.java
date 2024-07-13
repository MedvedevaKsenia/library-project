package ru.itgirl.library_project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itgirl.library_project.dto.AuthorDto;
import ru.itgirl.library_project.service.AuthorService;

@RestController
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("/author/{id}")
    AuthorDto getAuthorById(@PathVariable("id") Long id) {
        return authorService.getAuthorById(id);
    }

    @GetMapping("/author")
    AuthorDto getAuthorByNameAndSurnameV1(@RequestParam(value = "name", required = false) String name) {
        String[] fullname = name.split(" ");
        return authorService.getAuthorByNameAndSurnameV1(fullname[0], fullname[1]);
    }

    @GetMapping("/author/v2")
    AuthorDto getAuthorByNameAndSurnameV2(@RequestParam(value = "name", required = false) String name) {
        String[] fullname = name.split(" ");
        return authorService.getAuthorByNameAndSurnameV2(fullname[0], fullname[1]);
    }
}