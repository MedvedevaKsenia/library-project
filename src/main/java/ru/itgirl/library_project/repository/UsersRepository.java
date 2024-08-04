package ru.itgirl.library_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itgirl.library_project.model.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
}