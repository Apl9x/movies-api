package io.javabrains.springbootquickstart.moviesapi.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javabrains.springbootquickstart.moviesapi.genre.Genre;
import io.javabrains.springbootquickstart.moviesapi.genre.GenreRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.github.javafaker.Faker;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@SpringBootTest
@AutoConfigureMockMvc
class GenreControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private GenreRepository genreRepository;

    private final Faker faker = new Faker();

    @Test
    void canGetAllGenres() throws Exception {
        //Arrange
        List<Genre> genres = new ArrayList<>();
        //Act
        MvcResult result = mockMvc.perform(get("/genres")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(genres))).andExpect(status().isOk()).andReturn();
        //Assert
        String  list = objectMapper.writeValueAsString((List<Genre>) genreRepository.findAll());
        String actualList =(result.getResponse().getContentAsString());
        assertThat(list).isEqualTo(actualList);
    }

    @Test
    void canGetGenreById() throws Exception{
        //Arrange
        Genre genres = new Genre();
        //Act
        MvcResult result = mockMvc.perform(get("/genres?id=1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(genres))).andExpect(status().isOk()).andReturn();
        //Assert
        String  list = objectMapper.writeValueAsString(genreRepository.findById(1L));
        String actualList =(result.getResponse().getContentAsString());
        assertThat(list).isEqualTo(actualList);
    }

    @Test
    void canAddGenre() throws Exception {
        //Arrange
        String name=faker.name().username();
        Genre genre = new Genre(null,name);
        //Act
        MvcResult result = mockMvc.perform(post("/genres")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(genre))).andExpect(status().isCreated()).andReturn();
        //Assert
        Genre added = genreRepository.findByName(name).get();
        assertThat(added.toString()).contains(genre.getName());
    }

    @Test
    void canGetGenreByName() throws Exception {
        //Arrange
        Genre genres = new Genre();
        //Act
        MvcResult result = mockMvc.perform(get("/genres?name=Action")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(genres))).andExpect(status().isOk()).andReturn();
        //Assert
        String  list = objectMapper.writeValueAsString(genreRepository.findByName("Action"));
        String actualList =(result.getResponse().getContentAsString());
        assertThat(list).isEqualTo(actualList);
    }

    @Test
    void canUpdateGenre() throws Exception {
        //Arrange
        Genre genre = genreRepository.findById(1L).get();
        genre.setName(faker.name().username());
        //Act
        MvcResult result = mockMvc.perform(put("/genres?id="+genre.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(genre))).andExpect(status().isOk()).andReturn();
        //Assert
        Genre updated = genreRepository.findById(Long.valueOf(genre.getId())).get();
        assertThat(updated.toString()).isEqualTo(genre.toString());
    }

    @Test
    void deleteGenre() throws Exception {
        //Arrange
        Genre genre = genreRepository.findById(2L).get();
        //Act
        MvcResult result = mockMvc.perform(delete("/genres?id="+genre.getId())
                .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk()).andReturn();
        //Assert
        Boolean  exist = genreRepository.existsById(genre.getId());
        assertThat(exist).isFalse();
    }
}