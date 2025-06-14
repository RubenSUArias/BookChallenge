package com.alura.challengeBook.challengeBook.repository;

import com.alura.challengeBook.challengeBook.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;

public interface LibroRepository extends JpaRepository <Libro, Long> {
    Optional<Libro> findByTitulo(String titulo);

    @Query("SELECT l FROM Libro l WHERE l.idiomas LIKE %:idioma%")
    List<Libro> findByIdioma(@Param("idioma") String idioma);

    @Query("SELECT DISTINCT l.idiomas FROM Libro l WHERE l.idiomas IS NOT NULL AND l.idiomas <> ''")
    List<String> findIdiomasUnicos();

    @Query("SELECT a.autor FROM Libro a WHERE a.fechaNacimiento <= :anio AND (a.fechaDeceso IS NULL OR a.fechaDeceso >= :anio)")
    List<String> findAutoresVivosEnAnio(@Param("anio") int anio);
}
