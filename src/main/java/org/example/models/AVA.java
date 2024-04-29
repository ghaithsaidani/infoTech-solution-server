package org.example.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="avas")
public class AVA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 10)
    private Character type;

    @Column(nullable = false,columnDefinition = "integer default 0")
    private Integer etat=0;

    @Column(length = 80)
    private String activite;

    @Column(nullable = false,length = 30)
    private String nom;

    @Column(nullable = false,length = 30)
    private String prenom;

    @Column(nullable = false,length = 50)
    private String adresse;

    @Column(nullable = false)
    private Long telephone;

    @Column(nullable = false)
    private float montant;

    @Column(nullable = false)
    private float montantDevise;

    @Column(nullable = false)
    private float montantDinar;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt=new Date();

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt=new Date();
}
