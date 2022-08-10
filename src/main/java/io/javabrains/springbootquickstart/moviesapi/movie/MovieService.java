package io.javabrains.springbootquickstart.moviesapi.movie;

import io.javabrains.springbootquickstart.moviesapi.exception.CustomNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MovieService {
    private MovieRepository movieRepository;

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

    public Movie addMovie(Movie g){
        return this.movieRepository.save(g);
    }
    public Movie updateMovie(Movie m, Long id){
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
        if(!movieRepository.existsById(id)){
            throw new CustomNotFoundException(
                    "Movie with id: "+id + " does not exists");
        }
        this.movieRepository.deleteById(id);
    }
}
