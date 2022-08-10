package io.javabrains.springbootquickstart.moviesapi.movie;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends CrudRepository<Movie,Long> {
    Optional<Movie> findByName(String name);
    Optional<List<Movie>> findByGenreName(String GenreName);
}
