package io.javabrains.springbootquickstart.moviesapi.genre;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class GenreController {
    private GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/genres")
    public List<Genre> getAllGenres(){
        return this.genreService.getAllGenres();
    }

    @GetMapping("/genres/{id}")
    public Optional<Genre> getGenreById(@PathVariable("id") Long id){
        return this.genreService.getGenreById(id);
    }

    @PostMapping("/genres")
    public Genre addGenre(@Validated @RequestBody Genre g){
        return this.genreService.addGenre(g);
    }
    /*
    @GetMapping("/genres/{name}")
    public Optional<Genre> getGenreByName(@PathVariable("name") String name){
        return this.genreService.getGenreByName(name);
    }

     */

    @PutMapping("/genres/{id}")
    public Genre updateGenre(@Validated @RequestBody Genre g, @PathVariable("id") Long id){
        return this.genreService.updateGenre(g,id);
    }

    @DeleteMapping("/genres/{id}")
    public void deleteGenre(@PathVariable("id") Long id){
        this.genreService.delete(id);
    }
}
