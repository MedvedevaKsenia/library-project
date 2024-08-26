package ru.itgirl.library_project.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.itgirl.library_project.dto.BookDto;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class BookRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetBookById() throws Exception {
        Long id = 1L;
        BookDto bookDto;
        bookDto = BookDto.builder()
                .id(id)
                .name("Война и мир")
                .build();
        mockMvc.perform(MockMvcRequestBuilders.get("/books/{id}", id))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(bookDto.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(bookDto.getName()));
    }

    @Test
    public void testGetBookByNameV1() throws Exception {
        String name = "Война и мир";
        BookDto bookDto = BookDto.builder()
                .id(1L)
                .name("Война и мир")
                .genre("Роман")
                .build();
        mockMvc.perform(MockMvcRequestBuilders.get("/book?name={name}", name))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(bookDto.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(bookDto.getName()));
    }

    @Test
    public void testGetBookByNameV2() throws Exception {
        String name = "Война и мир";
        BookDto bookDto = BookDto.builder()
                .id(1L)
                .name("Война и мир")
                .genre("Роман")
                .build();
        mockMvc.perform(MockMvcRequestBuilders.get("/book/v2?name={name}", name))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(bookDto.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(bookDto.getName()));
    }

    @Test
    public void testGetBookByNameV3() throws Exception {
        String name = "Война и мир";
        BookDto bookDto = BookDto.builder()
                .id(1L)
                .name("Война и мир")
                .genre("Роман")
                .build();
        mockMvc.perform(MockMvcRequestBuilders.get("/book/v3?name={name}", name))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(bookDto.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(bookDto.getName()));
    }

    @Test
    public void testDeleteBook() throws Exception {
        Long id = 1L;
        BookDto bookDto = BookDto.builder()
                .id(1L)
                .name("Война и мир")
                .genre("Роман")
                .build();
        mockMvc.perform(MockMvcRequestBuilders.delete("/book/delete/{id}", id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}