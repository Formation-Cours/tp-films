package com.formation.film.controllers;

import com.formation.film.dto.FilmDTO;
import com.formation.film.entities.FilmEntity;
import com.formation.film.services.FilmService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/films")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public Page<FilmEntity> obtenirTousLesFilms(Pageable pageable) {
        return filmService.obtenirTousLesFilms(pageable);
    }

    @PostMapping
    public FilmEntity ajouterFilm(@Valid @RequestBody FilmDTO film) {
        return filmService.ajouterFilm(film);
    }
}
