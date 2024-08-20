package ru.itgirl.library_project.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itgirl.library_project.dto.AuthorCreateDto;
import ru.itgirl.library_project.dto.AuthorDto;
import ru.itgirl.library_project.dto.AuthorUpdateDto;
import ru.itgirl.library_project.service.AuthorService;

@SecurityRequirement(name = "library-users")
@Tag(name = "Контроллер для управления авторами", description = "Может выводить автора по ID, искать автора по имени, создать, удалить, изменить автора в БД")
@RestController
@RequiredArgsConstructor
public class AuthorRestController {
    private final AuthorService authorService;

    @GetMapping("/author/{id}")
    @Operation(summary = "Поиск автора по ID")
    AuthorDto getAuthorById(@PathVariable("id") Long id) {
        return authorService.getAuthorById(id);
    }

    @GetMapping("/author")
    @Operation(summary = "Поиск автора по имени и фамилии", description = "Поиск автора по имени и фамилии с помощью автогенерации запроса в репозитории")
    AuthorDto getAuthorByNameAndSurnameV1(@RequestParam(value = "name", required = false) String name) {
        String[] fullname = name.split(" ");
        return authorService.getAuthorByNameAndSurnameV1(fullname[0], fullname[1]);
    }

    @GetMapping("/author/v2")
    @Operation(summary = "Поиск автора по имени и фамилии", description = "Поиск автора по имени и фамилии с помощью SQL внутри аннотации")
    AuthorDto getAuthorByNameAndSurnameV2(@RequestParam(value = "name", required = false) String name) {
        String[] fullname = name.split(" ");
        return authorService.getAuthorByNameAndSurnameV2(fullname[0], fullname[1]);
    }

    @GetMapping("/author/v3")
    @Operation(summary = "Поиск автора по имени и фамилии", description = "Поиск автора по имени и фамилии с помощью объекта Java")
    AuthorDto getAuthorByNameAndSurnameV3(@RequestParam(value = "name", required = false) String name) {
        String[] fullname = name.split(" ");
        return authorService.getAuthorByNameAndSurnameV3(fullname[0], fullname[1]);
    }

    @PostMapping("/author/create")
    @Operation(summary = "Создание автора в БД")
    AuthorDto createAuthor(@RequestBody @Valid AuthorCreateDto authorCreateDto) {
        return authorService.createAuthor(authorCreateDto);
    }

    @PutMapping("/author/update")
    @Operation(summary = "Обновление информации автора в БД")
    AuthorDto updateAuthor(@RequestBody @Valid AuthorUpdateDto authorUpdateDto) {
        return authorService.updateAuthor(authorUpdateDto);
    }

    @DeleteMapping("/author/delete/{id}")
    @Operation(summary = "Удаление автора по ID")
    void deleteAuthor(@PathVariable("id") Long id) {
        authorService.deleteAuthor(id);
    }
}