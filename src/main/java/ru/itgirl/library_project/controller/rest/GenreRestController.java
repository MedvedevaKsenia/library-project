package ru.itgirl.library_project.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.itgirl.library_project.dto.GenreDto;
import ru.itgirl.library_project.service.GenreService;

@SecurityRequirement(name = "library-users")
@Tag(name = "Контроллер для управления жанрами", description = "Может выводить жанр по ID")
@RestController
@RequiredArgsConstructor
public class GenreRestController {
    private final GenreService genreService;

    @GetMapping("/genre/{id}")
    @Operation(summary = "Поиск жанра по ID")
    GenreDto getGenreById(@PathVariable("id") Long id) {
        return genreService.getGenreById(id);
    }
}