package io.javabrains.springbootquickstart.moviesapi.genre;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class GenreController {
    private GenreService genreService;

    @GetMapping(value = "/genres", params = {})
    public List<Genre> getAllGenres(){
        return this.genreService.getAllGenres();
    }

    @GetMapping(value = "/genres", params = "id")
    public Optional<Genre> getGenreById(@RequestParam Long id){
        return this.genreService.getGenreById(id);
    }

    @PostMapping("/genres")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Genre addGenre(@Validated @RequestBody Genre g){
        return this.genreService.addGenre(g);
    }

    @GetMapping(value = "/genres", params = "name")
    public Optional<Genre> getGenreByName(@RequestParam String name){
        return this.genreService.getGenreByName(name);
    }

    @PutMapping(value = "/genres", params = "id")
    public Genre updateGenre(@Validated @RequestBody Genre g, @RequestParam Long id){
        return this.genreService.updateGenre(g,id);
    }

    @DeleteMapping(value = "/genres", params = "id")
    public void deleteGenre(@RequestParam Long id){
        this.genreService.delete(id);
    }
}
