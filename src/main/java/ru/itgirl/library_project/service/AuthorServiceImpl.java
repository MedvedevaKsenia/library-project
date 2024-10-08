package ru.itgirl.library_project.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.itgirl.library_project.dto.AuthorCreateDto;
import ru.itgirl.library_project.dto.AuthorDto;
import ru.itgirl.library_project.dto.AuthorUpdateDto;
import ru.itgirl.library_project.dto.BookDto;
import ru.itgirl.library_project.model.Author;
import ru.itgirl.library_project.repository.AuthorRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public AuthorDto getAuthorById(Long id) {
        log.info("Try to find author by id {}", id);
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            AuthorDto authorDto = convertEntityToDto(author.get());
            log.info("Author: {}", authorDto.toString());
            return authorDto;
        } else {
            log.error("Author with {id} not found", id);
            throw new NoSuchElementException("No value present");
        }
    }

    @Override
    public AuthorDto getAuthorByNameAndSurnameV1(String name, String surname) {
        log.info("Try to find V1 author by name {} and surname {}", name, surname);
        Optional<Author> author = authorRepository.findAuthorByNameAndSurname(name, surname);
        if (author.isPresent()) {
            AuthorDto authorDto = convertEntityToDto(author.get());
            log.info("Author: {} ", authorDto.toString());
            return authorDto;
        } else {
            log.error("Author with name {} and surname {} not found", name, surname);
            throw new NoSuchElementException("No value present");
        }
    }

    @Override
    public AuthorDto getAuthorByNameAndSurnameV2(String name, String surname) {
        log.info("Try to find V2 author by name {} and surname {}", name, surname);
        Optional<Author> author = authorRepository.findAuthorByNameAndSurnameBySql(name, surname);
        if (author.isPresent()) {
            AuthorDto authorDto = convertEntityToDto(author.get());
            log.info("Author: {} ", authorDto.toString());
            return authorDto;
        } else {
            log.error("Author with name {} and surname {} not found", name, surname);
            throw new NoSuchElementException("No value present");
        }
    }

    @Override
    public AuthorDto getAuthorByNameAndSurnameV3(String name, String surname) {
        Specification<Author> authorSpecification = Specification.where(new Specification<Author>() {
            @Override
            public Predicate toPredicate(Root<Author> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("name"), name);
            }
        }.and(new Specification<Author>() {
            @Override
            public Predicate toPredicate(Root<Author> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("surname"), surname);
            }
        }));
        Author author = authorRepository.findOne(authorSpecification).orElseThrow();
        return convertEntityToDto(author);
    }

    @Override
    public AuthorDto createAuthor(AuthorCreateDto authorCreateDto) {
        Author author = authorRepository.save(convertDtoToEntity(authorCreateDto));
        AuthorDto authorDto = convertEntityToDto(author);
        return authorDto;
    }

    @Override
    public AuthorDto updateAuthor(AuthorUpdateDto authorUpdateDto) {
        Author author = authorRepository.findById(authorUpdateDto.getId()).orElseThrow();
        author.setName(authorUpdateDto.getName());
        author.setSurname(authorUpdateDto.getSurname());
        Author savedAuthor = authorRepository.save(author);
        AuthorDto authorDto = convertEntityToDto(savedAuthor);
        return authorDto;
    }

    @Override
    public void deleteAuthor(Long id) {
        log.info("Try to delete author with id {}", id);
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            log.info("Found author with id {}", id);
            authorRepository.deleteById(id);
            log.info("Deleted author with id {}", id);
        } else {
            log.error("Failed to find author with id {}", id);
            throw new NoSuchElementException("No value present");
        }
    }

    @Override
    public List<AuthorDto> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    private Author convertDtoToEntity(AuthorCreateDto authorCreateDto) {
        return Author.builder()
                .name(authorCreateDto.getName())
                .surname(authorCreateDto.getSurname())
                .build();
    }

    private AuthorDto convertEntityToDto(Author author) {
        List<BookDto> bookDtoList = null;
        if (author.getBooks() != null) {
            bookDtoList = author.getBooks().stream()
                    .map(book -> BookDto.builder()
                            .genre(book.getGenre().getName())
                            .name(book.getName())
                            .id(book.getId())
                            .build())
                    .toList();
        }
        AuthorDto authorDto = AuthorDto.builder()
                .id(author.getId())
                .name(author.getName())
                .surname(author.getSurname())
                .books(bookDtoList)
                .build();
        return authorDto;
    }
}