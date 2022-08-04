package io.javabrains.springbootquickstart.moviesapi.genre;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {
    private GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getAllGenres(){
        return (List<Genre>) this.genreRepository.findAll();
    }

    public Optional<Genre> getGenreById(Long id){
        return this.genreRepository.findById(id);
    }

    public Optional<Genre> getGenreByName(String name){
        return this.genreRepository.findByName(name);
    }

    public Genre addGenre(Genre g){
        return this.genreRepository.save(g);
    }
    public Genre updateGenre(Genre g, Long id){
        return this.genreRepository.findById(id).
                map(genre -> {
                    genre.setId(id);
                    genre.setName(g.getName());
                    return this.genreRepository.save(genre);
                }).orElseGet(()->{
                    g.setId(id);
                    return this.genreRepository.save(g);
                });
    }

    public void delete(Long id){
        this.genreRepository.deleteById(id);
    }
}
