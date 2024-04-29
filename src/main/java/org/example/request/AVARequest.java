package org.example.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AVARequest {
    private Character type;
    private String activite;
    private String nom;
    private String prenom;
    private String adresse;
    private Long telephone;
    private float montant;
    private float montantDevise;
    private float montantDinar;
}
