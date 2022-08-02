package io.javabrains.springbootquickstart.moviesapi.genre;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class GenreController {
    @RequestMapping("/genres")
    public List<Genre> getAllGenres(){
        return Arrays.asList(
                new Genre("horror","Horror"),
                new Genre("action","Action"),
                new Genre("comedy","Comedy")
                );
    }
}
