package ru.itgirl.library_project.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "roles_name")
    private RoleType roleType;

    public Roles(RoleType roleType) {
        this.roleType = roleType;
    }
}