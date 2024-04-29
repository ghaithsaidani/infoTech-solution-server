package org.example.repositories;

import org.example.models.AVA;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AVARepositoryTest {

    private static final long ID= 1L;
    private static final Character TYPE= 'E';
    private static final String ACTIVITE="Études et conseils";
    private static final String NOM="InfoTech";
    private static final String UPDATEDNOM="InfoTech1";
    private static final String PRENOM="Solutions";
    private static final String ADRESSE="Tunis";
    private static final Long TELEPHONE=12345678L;
    private static final float montant=5000;
    private static final float montantDevise=2000;
    private static final float montantDinar=1200;

    @Autowired
    private AVARepository avaRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void should_save_ava() {
        //Given
        AVA ava = givenAVA();
        //When
        ava = avaRepository.save(ava);
        //Then
        AVA actual = testEntityManager.find(AVA.class, ava.getId());
        assertEquals(actual, ava);
    }

    @Test
    public void should_find_ava_by_id() {
        //Given
        AVA ava = givenAVA();
        ava = testEntityManager.merge(ava);
        testEntityManager.persist(ava);
        //When
        Optional<AVA> actual = avaRepository.findById(ava.getId());
        //System.out.println(actual);
        //Then
        assertNotEquals(actual, Optional.empty());
        assertEquals(actual.get(), ava);
        ;
    }

    @Test
    public void should_find_all_avas() {
        //Given
        AVA ava = givenAVA();
        ava = testEntityManager.merge(ava);
        testEntityManager.persist(ava);
        //When
        List<AVA> avas = avaRepository.findAll();
        //Then
        assertThat(avas).contains(ava);
        System.out.println(avas);
    }

    @Test
    public void should_delete_ava_by_id() {
        //Given
        AVA ava = givenAVA();
        ava = testEntityManager.merge(ava);
        testEntityManager.persist(ava);
        //When
        avaRepository.deleteById(ava.getId());
        //Then
        AVA actual = testEntityManager.find(AVA.class, ava.getId());
        assertNull(actual);
    }

    @Test
    public void should_update_ava() {
        //Given
        AVA ava = givenAVA();
        ava = testEntityManager.merge(ava);
        testEntityManager.persist(ava);
        AVA updatedAVA = givenUpdatedAVA();
        updatedAVA.setId(ava.getId());
        //When
        updatedAVA = avaRepository.save(updatedAVA);
        //Then
        AVA actual = testEntityManager.find(AVA.class, ava.getId());
        assertEquals(actual, updatedAVA);

    }

    private AVA givenAVA() {
        AVA ava = new AVA();
        ava.setId(ID);
        ava.setType(TYPE);
        ava.setActivite(ACTIVITE);
        ava.setNom(NOM);
        ava.setPrenom(PRENOM);
        ava.setAdresse(ADRESSE);
        ava.setTelephone(TELEPHONE);
        ava.setMontant(montant);
        ava.setMontantDevise(montantDevise);
        ava.setMontantDinar(montantDinar);
        return ava;
    }

    private AVA givenUpdatedAVA() {
        AVA ava = new AVA();
        ava.setId(ID);
        ava.setType(TYPE);
        ava.setActivite(ACTIVITE);
        ava.setNom(UPDATEDNOM);
        ava.setPrenom(PRENOM);
        ava.setAdresse(ADRESSE);
        ava.setTelephone(TELEPHONE);
        ava.setMontant(montant);
        ava.setMontantDevise(montantDevise);
        ava.setMontantDinar(montantDinar);
        return ava;
    }

}