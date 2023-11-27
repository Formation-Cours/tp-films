package com.formation.film.services;

import com.formation.film.dto.FilmDTO;
import com.formation.film.entities.FilmEntity;
import com.formation.film.repositories.FilmRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
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

    public FilmEntity ajouterFilm(FilmDTO film) throws EntityExistsException {
        if (filmRepository.existsFilm(film.getTitre(), film.getAnnee(), film.getRealisateur())) {
            throw new EntityExistsException("Un film avec ce titre, année et réalisateur existe déjà.");
        }
        FilmEntity filmEntity = film.convertDtoToEntity();
        return filmRepository.save(filmEntity);
    }

    public FilmEntity obtenirFilmParId(Long id) {
        return filmRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Je n'ai pas trouvé le film"));
    }

    public FilmDTO mettreAJourFilm(Long id, FilmDTO filmDTO) {
        FilmEntity film = filmRepository.findById(id).orElse(null);
        if (film == null) {
            throw new EntityNotFoundException("Le film ne peut être mise à jour car il n'existe pas");
        }
        film.setTitre(filmDTO.getTitre());
        film.setGenre(filmDTO.getGenre());
        film.setAnnee(filmDTO.getAnnee());
        film.setRealisateur(filmDTO.getRealisateur());
        film.setDescription(filmDTO.getDescription());
        filmRepository.save(film);

        return film.convertEntityToDto();
    }

    public String supprimerFilm(Long id) {
        this.obtenirFilmParId(id);
        filmRepository.deleteById(id);
        if (!filmRepository.existsById(id)) {
            return null;
        }
        return "Il y a eu un problème. Le film ne s'est pas supprimé";
    }

    public Page<FilmDTO> rechercherParLeTitre(String titre, Pageable pageable) {
        return filmRepository.findByTitreContainingIgnoreCase(titre, pageable)
                .map(FilmEntity::convertEntityToDto);
    }

    public Page<FilmDTO> rechercherParAnnee(Integer annee, Pageable pageable) {
        return filmRepository.findByAnnee(annee, pageable)
                .map(FilmEntity::convertEntityToDto);
    }

    public Page<FilmDTO> rechercherParGenre(String genre, Pageable pageable) {
        return filmRepository.findByGenreContainingIgnoreCase(genre, pageable)
                .map(FilmEntity::convertEntityToDto);
    }
}
