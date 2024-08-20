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
public class BookUpdateDto {
    private Long id;
    @Size(min = 2, max = 30)
    @NotBlank(message = "Name not specified")
    private String name;
    private Long genre_id;
}