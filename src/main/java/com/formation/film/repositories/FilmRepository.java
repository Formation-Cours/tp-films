package com.formation.film.repositories;

import com.formation.film.entities.FilmEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface FilmRepository extends JpaRepository<FilmEntity, Long> {
    // SELECT * FROM film WHERE UPPER(titre) = UPPER(?) AND UPPER(annee) = UPPER(?) AND UPPER(realisateur) = UPPER(?);
    @Query(value = "SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM FilmEntity as f WHERE UPPER(titre) = UPPER(:t) AND annee = :a AND UPPER(realisateur) = UPPER(:r)")
    boolean existsFilm(@Param("t") String titre, @Param("a") Integer annee, @Param("r") String realisateur);

    // SELECT * FROM film WHERE annee BETWEEN ? AND ?;
    @Query("FROM FilmEntity WHERE annee BETWEEN :start AND :end")
    Collection<FilmEntity> searchFilmBetweenYear(@Param("start") Integer startYear, @Param("end") Integer endYear);

    Page<FilmEntity> findByTitreContainingIgnoreCase(String titre, Pageable pageable);

    Page<FilmEntity> findByAnnee(Integer anne, Pageable pageable);

    Page<FilmEntity> findByGenreContainingIgnoreCase(String genre, Pageable pageable);
}
