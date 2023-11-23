package com.formation.film.repositories;

import com.formation.film.entities.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<FilmEntity, Long> {

}
