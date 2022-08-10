package io.javabrains.springbootquickstart.moviesapi.unit.genre;

import io.javabrains.springbootquickstart.moviesapi.exception.CustomNotFoundException;
import io.javabrains.springbootquickstart.moviesapi.genre.Genre;
import io.javabrains.springbootquickstart.moviesapi.genre.GenreRepository;
import io.javabrains.springbootquickstart.moviesapi.genre.GenreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GenreServiceTest {
    @Mock
    private GenreRepository genreRepository;
    @InjectMocks
    private GenreService underTest;

    @Test
    void canGetAllGenres() {
        //Arrange
        //Act
        underTest.getAllGenres();
        //Assert
        verify(genreRepository).findAll();
    }

    @Test
    void canGetGenreById() {
        //Arrange
        Long id = 1L;
        //Act
        underTest.getGenreById(id);
        //Assert
        verify(genreRepository).findById(id);
    }

    @Test
    void canGetGenreByName() {
        //Arrange
        String name = "Comedy";
        //Act
        underTest.getGenreByName(name);
        //Assert
        verify(genreRepository).findByName(name);
    }

    @Test
    void canAddGenre() {
        //Arrange
        Genre genre = new Genre(1L,"Adventure");
        //Act
        underTest.addGenre(genre);
        //Assert
        ArgumentCaptor<Genre> genreArgumentCaptor = ArgumentCaptor.forClass(Genre.class);
        verify(genreRepository).save(genreArgumentCaptor.capture());
        Genre capturedGenre = genreArgumentCaptor.getValue();
        assertThat(capturedGenre).isEqualTo(genre);
    }

    @Test
    void canUpdateGenre() {
        //Arrange
        Genre genre = new Genre(10L,"Adventure");
        when(genreRepository.findById(genre.getId())).thenReturn(Optional.of(genre));
        //Act
        genre.setName("Modded");
        underTest.updateGenre(genre,genre.getId());
        //Assert
        assertThat(genreRepository.findById(genre.getId()).get().getName()).isEqualTo("Modded");
    }

    @Test
    void canAddInsteadOfUpdateGenre() {
        //Arrange
        Genre g = new Genre(1L,"A");
        //Act
        underTest.updateGenre(g,1L);
        //Assert
        ArgumentCaptor<Genre> genreArgumentCaptor = ArgumentCaptor.forClass(Genre.class);
        verify(genreRepository).save(genreArgumentCaptor.capture());
        Genre capturedGenre = genreArgumentCaptor.getValue();
        assertThat(capturedGenre).isEqualTo(g);
    }

    @Test
    void canDeleteGenre() {
        //Arrange
        Long id = 1L;
        given(genreRepository.existsById(id)).willReturn(true);
        //Act
        underTest.delete(id);
        //Assert
        verify(genreRepository).deleteById(id);
    }

    @Test
    void willThrowWhenDeteleGenreNotFound() {
        //Arrange
        Long id = 11L;
        given(genreRepository.existsById(id)).willReturn(false);
        //Act
        //Assert
        assertThatThrownBy(()->underTest.delete(id))
                .isInstanceOf(CustomNotFoundException.class)
                        .hasMessageContaining("Genre with id: "+id + " does not exists");
        verify(genreRepository,never()).deleteById(any());
    }
}