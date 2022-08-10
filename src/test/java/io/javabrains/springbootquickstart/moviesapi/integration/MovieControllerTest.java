package io.javabrains.springbootquickstart.moviesapi.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.javabrains.springbootquickstart.moviesapi.genre.Genre;
import io.javabrains.springbootquickstart.moviesapi.movie.Movie;
import io.javabrains.springbootquickstart.moviesapi.movie.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MovieControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MovieRepository movieRepository;

    private final Faker faker = new Faker();

    @Test
    void canGetAllMovies() throws Exception {
        //Arrange
        List<Movie> movies = new ArrayList<>();
        //Act
        MvcResult result = mockMvc.perform(get("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(movies))).andExpect(status().isOk()).andReturn();
        //Assert
        String  list = objectMapper.writeValueAsString((List<Movie>) movieRepository.findAll());
        String actualList =(result.getResponse().getContentAsString());
        assertThat(list).isEqualTo(actualList);
    }

    @Test
    void canGetMovieById() throws Exception{
        //Arrange
        Movie movies = new Movie();
        //Act
        MvcResult result = mockMvc.perform(get("/movies?id=1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(movies))).andExpect(status().isOk()).andReturn();
        //Assert
        String  list = objectMapper.writeValueAsString(movieRepository.findById(1L));
        String actualList =(result.getResponse().getContentAsString());
        assertThat(list).isEqualTo(actualList);
    }

    @Test
    void canAddMovie() throws Exception {
        //Arrange
        String name=faker.name().title();
        String description = faker.random().toString();
        Movie movie = new Movie(null,name,description,new Genre(1L,null));
        //Act
        MvcResult result = mockMvc.perform(post("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(movie))).andExpect(status().isCreated()).andReturn();
        //Assert
        Movie added = movieRepository.findByName(name).get();
        assertThat(added.toString()).contains(movie.getName());
    }

    @Test
    void canGetMovieByName() throws Exception {
        //Arrange
        Movie movie = new Movie();
        //Act
        MvcResult result = mockMvc.perform(get("/movies?name=Shrek")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(movie))).andExpect(status().isOk()).andReturn();
        //Assert
        String  list = objectMapper.writeValueAsString(movieRepository.findByName("Shrek"));
        String actualList =(result.getResponse().getContentAsString());
        assertThat(list.toString()).contains("Shrek");
    }

    @Test
    void canGetMovieByGenreName() throws Exception {
        //Arrange
        Movie movie = new Movie();
        //Act
        MvcResult result = mockMvc.perform(get("/movies?genre=Comedy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(movie))).andExpect(status().isOk()).andReturn();
        //Assert
        String  list = objectMapper.writeValueAsString(movieRepository.findByGenreName("Comedy"));
        String actualList =(result.getResponse().getContentAsString());
        assertThat(list.toString()).contains("Comedy");
    }

    @Test
    void canUpdateMovie() throws Exception {
        //Arrange
        Movie movie = movieRepository.findById(1L).get();
        movie.setName(faker.name().username());
        //Act
        MvcResult result = mockMvc.perform(put("/movies?id="+movie.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(movie))).andExpect(status().isOk()).andReturn();
        //Assert
        Movie updated = movieRepository.findById(Long.valueOf(movie.getId())).get();
        assertThat(updated.toString()).isEqualTo(movie.toString());
    }

    @Test
    void canDeleteMovie() throws Exception {
        //Arrange
        Movie movie = movieRepository.findById(2L).get();
        //Act
        MvcResult result = mockMvc.perform(delete("/movies?id="+movie.getId())
                .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk()).andReturn();
        //Assert
        Boolean  exist = movieRepository.existsById(movie.getId());
        assertThat(exist).isFalse();
    }
}