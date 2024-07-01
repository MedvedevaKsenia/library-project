package ru.itgirl.library_project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itgirl.library_project.dto.BookDto;
import ru.itgirl.library_project.dto.GenreDto;
import ru.itgirl.library_project.model.Book;
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
        //List<BookDto> bookDtoList = BookDto.builder
            //    .map(book -> BookDto.
             //           builder()
             //           .id(book.getId())
             //           .name(book.getName())
             //   .authors(book.getAuthors())
              //          .build()
      //  ).toList();

        GenreDto genreDto = GenreDto.builder()
                .id(genre.getId())
                .name(genre.getName())
               // .books(bookDtoList)
                .build();
        return genreDto;
    }
}