package com.formation.film.services;

import com.formation.film.dto.FilmDTO;
import com.formation.film.entities.FilmEntity;
import com.formation.film.repositories.FilmRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {

    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public Page<FilmEntity> obtenirTousLesFilms(Pageable pageable) {
        return filmRepository.findAll(pageable);
    }

    public FilmEntity ajouterFilm(FilmDTO film) {
        // TODO: vérifier que le film n'existe pas déjà => check titre, l'année et le réalisateur
        FilmEntity filmEntity = film.convertDtoToEntity();
        return filmRepository.save(filmEntity);
    }
}
