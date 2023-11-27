package com.formation.film.controllers;

import com.formation.film.dto.FilmDTO;
import com.formation.film.entities.FilmEntity;
import com.formation.film.services.FilmService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

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
    public FilmEntity ajouterFilm(@Valid @RequestBody FilmDTO film) throws Exception {
        return filmService.ajouterFilm(film);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmDTO> obtenirFilmParId(@PathVariable Long id) {
        FilmEntity film = filmService.obtenirFilmParId(id);
        return ResponseEntity.ok(film.convertEntityToDto());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> mettreAJourFilm(@PathVariable(name = "id") Long id, @Valid @RequestBody FilmDTO film) {
        return ResponseEntity.ok(filmService.mettreAJourFilm(id, film));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> supprimerFilm(@PathVariable Long id) {
        String resp = filmService.supprimerFilm(id);
        if (resp == null) {
            return ResponseEntity.ok("Tout s'est bien passé");
        }
        return ResponseEntity.internalServerError().body(resp);
    }

    @GetMapping("/search")
    public ResponseEntity<?> rechercher(
            @RequestParam(name = "titre", required = false) String titre,
            @RequestParam(name = "annee", required = false) Integer annee,
            @RequestParam(name = "genre", required = false) String genre,
            Pageable pageable) {

        if (titre != null && !titre.isEmpty()) {
            return ResponseEntity.ok(filmService.rechercherParLeTitre(titre, pageable));
        } else if (annee != null && annee > 1900) {
            return ResponseEntity.ok(filmService.rechercherParAnnee(annee, pageable));
        } else if (genre != null && !genre.isEmpty()) {
            return ResponseEntity.ok(filmService.rechercherParGenre(genre, pageable));
        }

        return ResponseEntity.badRequest().body("Il manque des informations dans les paramètres d'URL!");
    }

    @GetMapping("/search/titre")
    public ResponseEntity<?> rechercherParLeTitre(@RequestParam(name = "titre") String titre, Pageable pageable) {
        return ResponseEntity.ok(filmService.rechercherParLeTitre(titre, pageable));
    }

    @GetMapping("/search/annee")
    public ResponseEntity<?> rechercherParAnnee(@RequestParam(name = "annee") Integer annee, Pageable pageable) {
        return ResponseEntity.ok(filmService.rechercherParAnnee(annee, pageable));
    }

    @GetMapping("/search/genre")
    public ResponseEntity<?> rechercherParGenre(@RequestParam(name = "genre") String genre, Pageable pageable) {
        return ResponseEntity.ok(filmService.rechercherParGenre(genre, pageable));
    }
}
