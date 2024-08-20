package ru.itgirl.library_project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UsersCreateDto {
    @Size(min = 3, max = 30)
    @NotBlank(message = "Login not specified")
    private String login;
    @Size(min = 60, max = 100)
    @NotBlank(message = "Password not specified")
    private String password;
    @Size(min = 4, max = 13)
    @NotBlank(message = "Role not specified")
    private String role;
}