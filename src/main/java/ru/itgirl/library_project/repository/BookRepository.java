package ru.itgirl.library_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itgirl.library_project.model.Book;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findBookByName(String name);
}