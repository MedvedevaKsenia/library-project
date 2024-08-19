package ru.itgirl.library_project.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            inverseJoinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id", table = "users"),
            joinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id", table = "roles")
    )

    private Set<Roles> roles = new HashSet<>();

    public Users(String login) {
        this.login = login;
    }
}