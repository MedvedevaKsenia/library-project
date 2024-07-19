package ru.itgirl.library_project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itgirl.library_project.dto.AuthorCreateDto;
import ru.itgirl.library_project.dto.AuthorDto;
import ru.itgirl.library_project.dto.AuthorUpdateDto;
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

    @GetMapping("/author/v3")
    AuthorDto getAuthorByNameAndSurnameV3(@RequestParam(value = "name", required = false) String name) {
        String[] fullname = name.split(" ");
        return authorService.getAuthorByNameAndSurnameV3(fullname[0], fullname[1]);
    }

    @PostMapping("/author/create")
    AuthorDto createAuthor(@RequestBody AuthorCreateDto authorCreateDto) {
        return authorService.createAuthor(authorCreateDto);
    }

    @PutMapping("/author/update")
    AuthorDto updateAuthor(@RequestBody AuthorUpdateDto authorUpdateDto) {
        return authorService.updateAuthor(authorUpdateDto);
    }
}