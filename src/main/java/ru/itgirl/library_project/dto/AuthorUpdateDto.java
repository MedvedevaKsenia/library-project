package ru.itgirl.library_project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthorUpdateDto {
    private Long id;
    @Size(min = 3, max = 10)
    @NotBlank(message = "Name not specified")
    private String name;
    @Size(min = 2, max = 20)
    @NotBlank(message = "Surname not specified")
    private String surname;
}