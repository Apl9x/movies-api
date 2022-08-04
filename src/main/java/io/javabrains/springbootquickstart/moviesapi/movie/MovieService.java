package io.javabrains.springbootquickstart.moviesapi.movie;

import io.javabrains.springbootquickstart.moviesapi.genre.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private MovieRepository movieRepository;
    private GenreRepository genreRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies(){
        return (List<Movie>) this.movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(Long id){
        return this.movieRepository.findById(id);
    }

    public Optional<Movie> getMovieByName(String name){
        return this.movieRepository.findByName(name);
    }

    public Optional<List<Movie>> getMovieByGenreName(String name){
        return this.movieRepository.findByGenreName(name);
    }

    public Movie addGenre(Movie g){
        return this.movieRepository.save(g);
    }
    public Movie updateGenre(Movie m, Long id){
        return this.movieRepository.findById(id).
                map(movie -> {
                    movie.setId(id);
                    movie.setName(m.getName());
                    movie.setGenre(m.getGenre());
                    movie.setDescription(m.getDescription());
                    return this.movieRepository.save(movie);
                }).orElseGet(()->{
                    m.setId(id);
                    return this.movieRepository.save(m);
                });
    }

    public void delete(Long id){
        this.movieRepository.deleteById(id);
    }
}
