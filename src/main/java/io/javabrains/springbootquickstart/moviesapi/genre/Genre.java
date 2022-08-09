package io.javabrains.springbootquickstart.moviesapi.genre;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="GENRE")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Genre {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
}
