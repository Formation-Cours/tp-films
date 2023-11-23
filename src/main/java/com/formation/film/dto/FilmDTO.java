package com.formation.film.dto;

import com.formation.film.entities.FilmEntity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class FilmDTO {
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotBlank(message = "Le titre ne doit pas être vide.")
    @NotNull(message = "Le titre ne doit pas être null.")
    private String titre;

    @Size(max = 50, message = "Le genre ne doit pas dépasser 50 caractères.")
    private String genre;

    @NotNull(message = "L'année ne doit pas être nulle.")
    @Min(value = 1900, message = "L'année doit être supérieure à 1900.")
    private Integer annee;

    @Size(max = 100, message = "Le réalisateur ne doit pas dépasser 100 caractères.")
    private String realisateur;

    @NotBlank(message = "La description ne doit pas être vide.")
    @NotNull(message = "La description ne doit pas être null.")
    @Size(max = 500, message = "La description ne doit pas dépasser 500 caractères.")
    private String description;

    public FilmEntity convertDtoToEntity(){
        return new FilmEntity(id, titre, genre, annee, realisateur, description);
    }
}
