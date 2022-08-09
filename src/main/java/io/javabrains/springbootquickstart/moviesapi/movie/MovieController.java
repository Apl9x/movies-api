package io.javabrains.springbootquickstart.moviesapi.movie;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class MovieController {
    private MovieService movieService;

    @GetMapping(value = "/movies", params = {})
    public List<Movie> getAllMovies(){
        return this.movieService.getAllMovies();
    }

    @GetMapping(value = "/movies", params = "id")
    public Optional<Movie> getMovieById(@RequestParam Long id){
        return this.movieService.getMovieById(id);
    }

    @GetMapping(value = "/movies", params = "name")
    public Optional<Movie> getMovieByName(@RequestParam String name){
        return this.movieService.getMovieByName(name);
    }

    @GetMapping(value = "/movies", params = "genre")
    public Optional<List<Movie>> getMoviesByGenre(@RequestParam String genre){
        return this.movieService.getMovieByGenreName(genre);
    }

    @PostMapping("/movies")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Movie addGenre(@Validated @RequestBody Movie g){
        return this.movieService.addGenre(g);
    }

    @PutMapping(value = "/movies", params = "id")
    public Movie updateGenre(@Validated @RequestBody Movie g, @RequestParam Long id){
        return this.movieService.updateGenre(g,id);
    }

    @DeleteMapping(value = "/movies", params = "id")
    public void deleteGenre(@RequestParam Long id){
        this.movieService.delete(id);
    }
}
