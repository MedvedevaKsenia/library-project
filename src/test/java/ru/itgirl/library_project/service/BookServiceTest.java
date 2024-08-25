package ru.itgirl.library_project.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import ru.itgirl.library_project.dto.BookCreateDto;
import ru.itgirl.library_project.dto.BookDto;
import ru.itgirl.library_project.dto.BookUpdateDto;
import ru.itgirl.library_project.model.Author;
import ru.itgirl.library_project.model.Book;
import ru.itgirl.library_project.model.Genre;
import ru.itgirl.library_project.repository.BookRepository;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BookServiceTest {
    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookServiceImpl bookService;

    @Test
    public void testGetBookById() {
        Long id = 1L;
        String name = "History of Russia";
        Genre genre = new Genre();
        Set<Author> authors = new HashSet<>();
        Book book = new Book(id, name, genre, authors);
        when(bookRepository.findById(id)).thenReturn(Optional.of(book));
        BookDto bookDto = bookService.getBookById(id);
        verify(bookRepository).findById(id);
        Assertions.assertEquals(bookDto.getId(), book.getId());
        Assertions.assertEquals(bookDto.getName(), book.getName());
    }

    @Test
    public void testGetBookByIdFailed() {
        Long id = 1L;
        when(bookRepository.findById(id)).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchElementException.class, () -> bookService.getBookById(id));
        verify(bookRepository).findById(id);
    }

    @Test
    public void testGetBookByNameV1() {
        Long id = 1L;
        String name = "History of Russia";
        Genre genre = new Genre();
        Set<Author> authors = new HashSet<>();
        Book book = new Book(id, name, genre, authors);
        when(bookRepository.findBookByName(name)).thenReturn(Optional.of(book));
        BookDto bookDto = bookService.getByNameV1(name);
        verify(bookRepository).findBookByName(name);
        Assertions.assertEquals(bookDto.getId(), book.getId());
        Assertions.assertEquals(bookDto.getName(), book.getName());
    }

    @Test
    public void testGetBookByNameV1Failed() {
        String name = "History of Russia";
        when(bookRepository.findBookByName(name)).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchElementException.class, () -> bookService.getByNameV1(name));
        verify(bookRepository).findBookByName(name);
    }

    @Test
    public void testGetBookByNameV2() {
        Long id = 1L;
        String name = "History of Russia";
        Genre genre = new Genre();
        Set<Author> authors = new HashSet<>();
        Book book = new Book(id, name, genre, authors);
        when(bookRepository.findBookByNameBySql(name)).thenReturn(Optional.of(book));
        BookDto bookDto = bookService.getByNameV2(name);
        verify(bookRepository).findBookByNameBySql(name);
        Assertions.assertEquals(bookDto.getId(), book.getId());
        Assertions.assertEquals(bookDto.getName(), book.getName());
    }

    @Test
    public void testGetBookByNameV2Failed() {
        String name = "History of Russia";
        when(bookRepository.findBookByNameBySql(name)).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchElementException.class, () -> bookService.getByNameV2(name));
        verify(bookRepository).findBookByNameBySql(name);
    }

    @Test
    public void testGetBookByNameV3() {
        Long id = 1L;
        String name = "History of Russia";
        Genre genre = new Genre();
        Set<Author> authors = new HashSet<>();
        Book book = new Book(id, name, genre, authors);
        when(bookRepository.findOne((Specification<Book>) any())).thenReturn(Optional.of(book));
        BookDto bookDto = bookService.getByNameV3(name);
        Assertions.assertNotNull(bookDto);
        Assertions.assertEquals(bookDto.getId(), book.getId());
        Assertions.assertEquals(bookDto.getName(), book.getName());
    }

    @Test
    public void testGetBookByNameV3Failed() {
        String name = "History of Russia";
        when(bookRepository.findOne((Specification<Book>) any())).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchElementException.class, () -> bookService.getByNameV3(name));
    }

    @Test
    public void testCreateBook() {
        Long id = 1L;
        String name = "History of Russia";
        Genre genre = new Genre();
        Set<Author> authors = new HashSet<>();
        Book book = new Book(id, name, genre, authors);
        BookCreateDto bookCreateDto = new BookCreateDto(name, genre.getId());
        when(bookRepository.save(any())).thenReturn(book);
        BookDto bookDto = bookService.createBook(bookCreateDto);
        verify(bookRepository).save(any());
        Assertions.assertEquals(bookDto.getName(), bookCreateDto.getName());
    }

    @Test
    public void testUpdateBook() {
        Long id = 1L;
        String name = "History of Russia";
        Genre genre = new Genre();
        Set<Author> authors = new HashSet<>();
        Book book = new Book(id, name, genre, authors);
        BookUpdateDto bookUpdateDto = new BookUpdateDto(id, name, genre.getId());
        when(bookRepository.findById(id)).thenReturn(Optional.of(book));
        when(bookRepository.save(any())).thenReturn(book);
        BookDto bookDto = bookService.updateBook(bookUpdateDto);
        Assertions.assertEquals(bookDto.getId(), bookUpdateDto.getId());
        Assertions.assertEquals(bookDto.getName(), bookUpdateDto.getName());
    }

    @Test
    public void testDeleteBook() {
        Long id = 1L;
        String name = "History of Russia";
        Genre genre = new Genre();
        Set<Author> authors = new HashSet<>();
        Book book = new Book(id, name, genre, authors);
        when(bookRepository.findById(id)).thenReturn(Optional.of(book));
        bookService.deleteBook(id);
        verify(bookRepository).deleteById(id);
    }

    @Test
    public void testDeleteBookFailed() {
        Long id = 1L;
        when(bookRepository.findById(id)).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchElementException.class, () -> bookService.getBookById(id));
    }

    @Test
    public void testGetAllBooks() {
        Long id = 1L;
        String name = "History of Russia";
        Genre genre = new Genre();
        Set<Author> authors = new HashSet<>();
        Book book = new Book(id, name, genre, authors);
        List<Book> books = new ArrayList<>();
        books.add(book);
        when(bookRepository.findAll()).thenReturn(books);
        List<BookDto> bookDtoList = bookService.getAllBooks();
        verify(bookRepository).findAll();
        Assertions.assertEquals(books.size(), bookDtoList.size());
    }
}