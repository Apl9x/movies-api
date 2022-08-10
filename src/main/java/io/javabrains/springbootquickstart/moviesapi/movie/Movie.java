package io.javabrains.springbootquickstart.moviesapi.movie;

import io.javabrains.springbootquickstart.moviesapi.genre.Genre;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="MOVIE")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
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
