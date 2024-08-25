package ru.itgirl.library_project.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import ru.itgirl.library_project.dto.AuthorCreateDto;
import ru.itgirl.library_project.dto.AuthorDto;
import ru.itgirl.library_project.dto.AuthorUpdateDto;
import ru.itgirl.library_project.model.Author;
import ru.itgirl.library_project.model.Book;
import ru.itgirl.library_project.repository.AuthorRepository;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AuthorServiceTest {
    @Mock
    AuthorRepository authorRepository;

    @InjectMocks
    AuthorServiceImpl authorService;

    @Test
    public void testGetAuthorById() {
        Long id = 1L;
        String name = "John";
        String surname = "Doe";
        Set<Book> books = new HashSet<>();
        Author author = new Author(id, name, surname, books);

        when(authorRepository.findById(id)).thenReturn(Optional.of(author));

        AuthorDto authorDto = authorService.getAuthorById(id);
        verify(authorRepository).findById(id);
        Assertions.assertEquals(authorDto.getId(), author.getId());
        Assertions.assertEquals(authorDto.getName(), author.getName());
        Assertions.assertEquals(authorDto.getSurname(), author.getSurname());
    }

    @Test
    public void testGetAuthorByIdFailed() {
        Long id = 1L;
        when(authorRepository.findById(id)).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchElementException.class, () -> authorService.getAuthorById(id));
        verify(authorRepository).findById(id);
    }

    @Test
    public void testGetAuthorByNameAndSurnameV1() {
        Long id = 1L;
        String name = "John";
        String surname = "Doe";
        Set<Book> books = new HashSet<>();
        Author author = new Author(id, name, surname, books);

        when(authorRepository.findAuthorByNameAndSurname(name, surname)).thenReturn(Optional.of(author));

        AuthorDto authorDto = authorService.getAuthorByNameAndSurnameV1(name, surname);
        verify(authorRepository).findAuthorByNameAndSurname(name, surname);
        Assertions.assertEquals(authorDto.getId(), author.getId());
        Assertions.assertEquals(authorDto.getName(), author.getName());
        Assertions.assertEquals(authorDto.getSurname(), author.getSurname());
    }

    @Test
    public void testGetAuthorByNameAndSurnameV1Failed() {
        String name = "John";
        String surname = "Doe";
        when(authorRepository.findAuthorByNameAndSurname(name, surname)).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchElementException.class, () -> authorService.getAuthorByNameAndSurnameV1(name, surname));
        verify(authorRepository).findAuthorByNameAndSurname(name, surname);
    }

    @Test
    public void testGetAuthorByNameAndSurnameV2() {
        Long id = 1L;
        String name = "John";
        String surname = "Doe";
        Set<Book> books = new HashSet<>();
        Author author = new Author(id, name, surname, books);

        when(authorRepository.findAuthorByNameAndSurnameBySql(name, surname)).thenReturn(Optional.of(author));

        AuthorDto authorDto = authorService.getAuthorByNameAndSurnameV2(name, surname);
        verify(authorRepository).findAuthorByNameAndSurnameBySql(name, surname);
        Assertions.assertEquals(authorDto.getId(), author.getId());
        Assertions.assertEquals(authorDto.getName(), author.getName());
        Assertions.assertEquals(authorDto.getSurname(), author.getSurname());
    }

    @Test
    public void testGetAuthorByNameAndSurnameV2Failed() {
        String name = "John";
        String surname = "Doe";
        when(authorRepository.findAuthorByNameAndSurnameBySql(name, surname)).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchElementException.class, () -> authorService.getAuthorByNameAndSurnameV2(name, surname));
        verify(authorRepository).findAuthorByNameAndSurnameBySql(name, surname);
    }

    @Test
    public void testGetAuthorByNameAndSurnameV3() {
        Long id = 1L;
        String name = "John";
        String surname = "Doe";
        Set<Book> books = new HashSet<>();
        Author author = new Author(id, name, surname, books);
        when(authorRepository.findOne((Specification<Author>) any())).thenReturn(Optional.of(author));
        AuthorDto authorDto = authorService.getAuthorByNameAndSurnameV3(name, surname);
        Assertions.assertNotNull(authorDto);
        Assertions.assertEquals(authorDto.getName(), author.getName());
        Assertions.assertEquals(authorDto.getSurname(), author.getSurname());
    }

    @Test
    public void testGetAuthorByNameAndSurnameV3Failed() {
        String name = "John";
        String surname = "Doe";
        when(authorRepository.findOne((Specification<Author>) any())).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchElementException.class, () -> authorService.getAuthorByNameAndSurnameV3(name, surname));
    }

    @Test
    public void testCreateAuthor() {
        Long id = 1L;
        String name = "John";
        String surname = "Doe";
        Set<Book> books = new HashSet<>();
        AuthorCreateDto authorCreateDto = new AuthorCreateDto(name, surname);
        Author author = new Author(id, name, surname, books);

        when(authorRepository.save(any())).thenReturn(author);
        AuthorDto authorDto = authorService.createAuthor(authorCreateDto);

        verify(authorRepository).save(any());
        Assertions.assertEquals(authorDto.getName(), authorCreateDto.getName());
        Assertions.assertEquals(authorDto.getSurname(), authorCreateDto.getSurname());
    }

    @Test
    public void testUpdateAuthor() {
        Long id = 1L;
        String name = "John";
        String surname = "Doe";
        Set<Book> books = new HashSet<>();
        AuthorUpdateDto authorUpdateDto = new AuthorUpdateDto(id, name, surname);
        Author author = new Author(id, name, surname, books);

        when(authorRepository.findById(id)).thenReturn(Optional.of(author));
        when(authorRepository.save(any())).thenReturn(author);

        AuthorDto authorDto = authorService.updateAuthor(authorUpdateDto);
        Assertions.assertEquals(authorDto.getId(), authorUpdateDto.getId());
        Assertions.assertEquals(authorDto.getName(), authorUpdateDto.getName());
        Assertions.assertEquals(authorDto.getSurname(), authorUpdateDto.getSurname());
    }

    @Test
    public void testDeleteAuthor() {
        Long id = 1L;
        String name = "John";
        String surname = "Doe";
        Set<Book> books = new HashSet<>();
        Author author = new Author(id, name, surname, books);

        when(authorRepository.findById(id)).thenReturn(Optional.of(author));
        authorService.deleteAuthor(id);
        verify(authorRepository).deleteById(id);
    }

    @Test
    public void testDeleteAuthorFailed() {
        Long id = 1L;
        when(authorRepository.findById(id)).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchElementException.class, () -> authorService.getAuthorById(id));
    }

    @Test
    public void testGetAllAuthors() {
        Long id = 1L;
        String name = "John";
        String surname = "Doe";
        Set<Book> books = new HashSet<>();
        Author author = new Author(id, name, surname, books);
        List<Author> authors = new ArrayList<>();
        authors.add(author);

        when(authorRepository.findAll()).thenReturn(authors);
        List<AuthorDto> authorsdtolist = authorService.getAllAuthors();
        verify(authorRepository).findAll();
        Assertions.assertEquals(authors.size(), authorsdtolist.size());
    }
}