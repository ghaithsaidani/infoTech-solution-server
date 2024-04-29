package org.example.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class AVADto {
    private Long id;
    private Character type;
    private Integer etat;
    private String activite;
    private String nom;
    private String prenom;
    private String adresse;
    private Long telephone;
    private float montant;
    private float montantDevise;
    private float montantDinar;
    private Date createdAt;
    private Date updatedAt;
}
