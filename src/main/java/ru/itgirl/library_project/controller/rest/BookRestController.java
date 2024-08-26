package ru.itgirl.library_project.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itgirl.library_project.dto.BookCreateDto;
import ru.itgirl.library_project.dto.BookDto;
import ru.itgirl.library_project.dto.BookUpdateDto;
import ru.itgirl.library_project.service.BookService;

@SecurityRequirement(name = "library-users")
@Tag(name = "Контроллер для управления книгами", description = "Может искать книгу ID или по названию, создать, удалить, изменить книгу в БД")
@RestController
@RequiredArgsConstructor
public class BookRestController {
    private final BookService bookService;

    @GetMapping("/books/{id}")
    @Operation(summary = "Поиск книги по ID")
    BookDto getBookById(@PathVariable("id") Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/book")
    @Operation(summary = "Поиск книги по названию", description = "Поиск книги по названию с помощью автогенерации запроса в репозитории")
    BookDto getBookByNameV1(@RequestParam(value = "name", required = false) String name) {
        return bookService.getByNameV1(name);
    }

    @GetMapping("/book/v2")
    @Operation(summary = "Поиск книги по названию", description = "Поиск книги по названию с помощью SQL внутри аннотации")
    BookDto getBookByNameV2(@RequestParam(value = "name", required = false) String name) {
        return bookService.getByNameV2(name);
    }

    @GetMapping("/book/v3")
    @Operation(summary = "Поиск книги по названию", description = "Поиск книги по названию с помощью объекта Java")
    BookDto getBookByNameV3(@RequestParam(value = "name", required = false) String name) {
        return bookService.getByNameV3(name);
    }

    @PostMapping("/book/create")
    @Operation(summary = "Создание книги в БД")
    BookDto createBook(@RequestBody @Valid BookCreateDto bookCreateDto) {
        return bookService.createBook(bookCreateDto);
    }

    @PutMapping("/book/update")
    @Operation(summary = "Обновление информации по книге в БД")
    BookDto updateBook(@RequestBody @Valid BookUpdateDto bookUpdateDto) {
        return bookService.updateBook(bookUpdateDto);
    }

    @DeleteMapping("/book/delete/{id}")
    @Operation(summary = "Удаление книги по ID")
    void deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
    }
}