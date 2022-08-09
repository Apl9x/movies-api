package io.javabrains.springbootquickstart.moviesapi.movie;

import io.javabrains.springbootquickstart.moviesapi.genre.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="MOVIE")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Movie {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
    @ManyToOne
    private Genre genre;
}
