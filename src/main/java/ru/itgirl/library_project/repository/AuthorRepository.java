package ru.itgirl.library_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itgirl.library_project.model.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findAuthorByNameAndSurname(String name, String surname);

    @Query(nativeQuery = true, value = "Select * from author where name = ? and surname = ?")
    Optional<Author> findAuthorByNameAndSurnameBySql(String name, String surname);
}