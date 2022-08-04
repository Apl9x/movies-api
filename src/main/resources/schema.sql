CREATE TABLE GENRE(
    id BIGINT PRIMARY KEY,
    name VARCHAR(15) NOT NULL
);

CREATE TABLE MOVIE(
    id BIGINT PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    description VARCHAR(60) DEFAULT NULL,
    genre_id BIGINT NOT NULL,
    CONSTRAINT FK_GENRE_ID FOREIGN KEY (genre_id) REFERENCES GENRE(id)
);