package io.javabrains.springbootquickstart.moviesapi.genre;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends CrudRepository<Genre,Long> {
    Optional<Genre> findByName(String name);
}
