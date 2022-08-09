package io.javabrains.springbootquickstart.moviesapi.genre;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.sun.javaws.JnlpxArgs.verify;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class GenreRepositoryTest {
    @Autowired
    private GenreRepository underTest;

    @Test
    void    itShouldFindByName() {
        //Arrange
        String name = "Horror";

        //Act
        Boolean expected = underTest.findByName(name).isPresent();

        //Assert
        assertThat(expected).isTrue();
    }
}