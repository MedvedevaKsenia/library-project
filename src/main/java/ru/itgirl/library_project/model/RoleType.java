package ru.itgirl.library_project.model;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
public enum RoleType {
    ADMIN,
    EDITOR,
    USER
}