package io.javabrains.springbootquickstart.moviesapi.unit.movie;

import io.javabrains.springbootquickstart.moviesapi.exception.CustomNotFoundException;
import io.javabrains.springbootquickstart.moviesapi.movie.Movie;
import io.javabrains.springbootquickstart.moviesapi.movie.MovieRepository;
import io.javabrains.springbootquickstart.moviesapi.movie.MovieService;
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
class MovieServiceTest {
    @Mock
    private MovieRepository movieRepository;
    @InjectMocks
    private MovieService underTest;

    @Test
    void canGetAllMovies() {
        //Arrange
        //Act
        underTest.getAllMovies();
        //Assert
        verify(movieRepository).findAll();
    }

    @Test
    void canGetMovieById() {
        //Arrange
        Long id = 1L;
        //Act
        underTest.getMovieById(id);
        //Assert
        verify(movieRepository).findById(id);
    }

    @Test
    void canGetMovieByName() {
        //Arrange
        String name = "Shrek";
        //Act
        underTest.getMovieByName(name);
        //Assert
        verify(movieRepository).findByName(name);
    }

    @Test
    void canGetMovieByGenreName() {
        //Arrange
        String name = "Comedy";
        //Act
        underTest.getMovieByGenreName(name);
        //Assert
        verify(movieRepository).findByGenreName(name);
    }

    @Test
    void canAddMovie() {
        //Arrange
        Movie movie = new Movie(1L,"Example","This is just for example...",null);
        //Act
        underTest.addMovie(movie);
        //Assert
        ArgumentCaptor<Movie> movieArgumentCaptor = ArgumentCaptor.forClass(Movie.class);
        verify(movieRepository).save(movieArgumentCaptor.capture());
        Movie capturedGenre = movieArgumentCaptor.getValue();
        assertThat(capturedGenre).isEqualTo(movie);
    }

    @Test
    void canUpdateMovie() {
        //Arrange
        Movie movie = new Movie(1L,"Example","This is just for example...",null);
        when(movieRepository.findById(movie.getId())).thenReturn(Optional.of(movie));
        //Act
        movie.setName("Modded");
        underTest.updateMovie(movie,movie.getId());
        //Assert
        assertThat(movieRepository.findById(movie.getId()).get().getName()).isEqualTo("Modded");
    }

    @Test
    void canAddInsteadOfUpdateMovie() {
        //Arrange
        Movie movie = new Movie(1L,"Example","This is just for example...",null);
        //Act
        underTest.updateMovie(movie,1L);
        //Assert
        ArgumentCaptor<Movie> movieArgumentCaptor = ArgumentCaptor.forClass(Movie.class);
        verify(movieRepository).save(movieArgumentCaptor.capture());
        Movie capturedMovie = movieArgumentCaptor.getValue();
        assertThat(capturedMovie).isEqualTo(movie);
    }

    @Test
    void canDeleteMovie() {
        //Arrange
        Long id = 1L;
        given(movieRepository.existsById(id)).willReturn(true);
        //Act
        underTest.delete(id);
        //Assert
        verify(movieRepository).deleteById(id);
    }

    @Test
    void willThrowWhenDeteleMovieNotFound() {
        //Arrange
        Long id = 11L;
        given(movieRepository.existsById(id)).willReturn(false);
        //Act
        //Assert
        assertThatThrownBy(()->underTest.delete(id))
                .isInstanceOf(CustomNotFoundException.class)
                        .hasMessageContaining("Movie with id: "+id + " does not exists");
        verify(movieRepository,never()).deleteById(any());
    }
}