package io.javabrains.springbootquickstart.moviesapi.unit.movie;

import io.javabrains.springbootquickstart.moviesapi.movie.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class MovieRepositoryTest {
    @Autowired
    private MovieRepository underTest;

    @Test
    void canFindByName() {
        //Arrange
        String name = "Shrek";

        //Act
        Boolean expected = underTest.findByName(name).isPresent();

        //Assert
        assertThat(expected).isTrue();
    }

    @Test
    void canFindByGenreName() {
        //Arrange
        String name = "Horror";

        //Act
        Boolean expected = underTest.findByGenreName(name).isPresent();

        //Assert
        assertThat(expected).isTrue();
    }
}