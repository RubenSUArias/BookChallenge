package com.alura.challengeBook.challengeBook.model;
import com.fasterxml.jackson.annotation.JsonAlias;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor(
        @JsonAlias("name") String autor,
        @JsonAlias("birth_year") Integer nacimiento,
        @JsonAlias("death_year") Integer muerte
        ) {
}
