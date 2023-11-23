package com.formation.film.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "film")
@EntityListeners(AuditingEntityListener.class)
public class FilmEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    private String titre;

    @Column(length = 50)
    private String genre;

    @Column(columnDefinition = "smallint", nullable = false)
    private Integer annee;

    @Column(nullable = false, length = 100)
    private String realisateur;

    @Column(length = 500, columnDefinition = "TEXT")
    private String description;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModifiedDate;

    public FilmEntity(Long id, String titre, String genre, Integer annee, String realisateur, String description) {
        this.id = id;
        this.titre = titre;
        this.genre = genre;
        this.annee = annee;
        this.realisateur = realisateur;
        this.description = description;
    }
}
