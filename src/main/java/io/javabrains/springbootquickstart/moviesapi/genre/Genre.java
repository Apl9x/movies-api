package io.javabrains.springbootquickstart.moviesapi.genre;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="GENRE")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Genre {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
}
