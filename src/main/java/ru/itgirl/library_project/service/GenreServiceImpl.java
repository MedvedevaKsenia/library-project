package ru.itgirl.library_project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itgirl.library_project.dto.AuthorDto;
import ru.itgirl.library_project.dto.BookDto;
import ru.itgirl.library_project.dto.GenreDto;
import ru.itgirl.library_project.model.Genre;
import ru.itgirl.library_project.repository.GenreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public GenreDto getGenreById(Long id) {
        Genre genre = genreRepository.findById(id).orElseThrow();
        return convertEntityGenreToDto(genre);
    }

    private GenreDto convertEntityGenreToDto(Genre genre) {
        List<BookDto> bookDtoList = genre.getBooks().stream()
                .map(book -> BookDto.builder()
                        .id(book.getId())
                        .name(book.getName())
                        .genre(book.getGenre().getName())
                        .authors(book.getAuthors().stream().map(author -> AuthorDto.builder()
                                .id(author.getId())
                                .name(author.getName())
                                .surname(author.getSurname())
                                .build()
                        ).toList())
                        .build()
                ).toList();

        GenreDto genreDto = GenreDto.builder()
                .id(genre.getId())
                .name(genre.getName())
                .books(bookDtoList)
                .build();
        return genreDto;
    }
}