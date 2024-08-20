package ru.itgirl.library_project.controller.rest;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.*;
import ru.itgirl.library_project.dto.AuthorCreateDto;
import ru.itgirl.library_project.dto.AuthorDto;
import ru.itgirl.library_project.dto.AuthorUpdateDto;
import ru.itgirl.library_project.service.AuthorService;

@SecurityRequirement(name = "library-users")
@RestController
@RequiredArgsConstructor
public class AuthorRestController {
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
    AuthorDto createAuthor(@RequestBody @Valid AuthorCreateDto authorCreateDto) {
        return authorService.createAuthor(authorCreateDto);
    }

    @PutMapping("/author/update")
    AuthorDto updateAuthor(@RequestBody AuthorUpdateDto authorUpdateDto) {
        return authorService.updateAuthor(authorUpdateDto);
    }

    @DeleteMapping("/author/delete/{id}")
    void deleteAuthor(@PathVariable("id") Long id) {
        authorService.deleteAuthor(id);
    }
}