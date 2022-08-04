package io.javabrains.springbootquickstart.moviesapi.movie;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MovieController {
    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public List<Movie> getAllMovies(){
        return this.movieService.getAllMovies();
    }

    @GetMapping("/movies/{id}")
    public Optional<Movie> getMovieById(@PathVariable("id") Long id){
        return this.movieService.getMovieById(id);
    }

    @GetMapping("/movies/name={name}")
    public Optional<Movie> getMovieByName(@PathVariable("name") String name){
        return this.movieService.getMovieByName(name);
    }

    @GetMapping("/movies/genre={genre}")
    public Optional<List<Movie>> getMoviesByGenre(@PathVariable("genre") String name){
        return this.movieService.getMovieByGenreName(name);
    }

    @PostMapping("/movies")
    public Movie addGenre(@Validated @RequestBody Movie g){
        return this.movieService.addGenre(g);
    }

    @PutMapping("/movies/{id}")
    public Movie updateGenre(@Validated @RequestBody Movie g, @PathVariable("id") Long id){
        return this.movieService.updateGenre(g,id);
    }

    @DeleteMapping("/movies/{id}")
    public void deleteGenre(@PathVariable("id") Long id){
        this.movieService.delete(id);
    }
}
