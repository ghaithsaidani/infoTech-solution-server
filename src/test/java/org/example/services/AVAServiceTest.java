package org.example.services;

import org.example.models.AVA;
import org.example.models.AVA;
import org.example.models.AVA;
import org.example.repositories.AVARepository;
import org.example.repositories.AVARepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AVAServiceTest {

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

    @InjectMocks
    private AVAService avaService;

    @Mock
    private AVARepository avaRepository;


    @Test
    public void should_find_all_avas() {
        //Given
        AVA ava = givenAVA();
        List<AVA> avas = Collections.singletonList(ava);
        when(avaRepository.findAll()).thenReturn(avas);
        //When
        //Then
        assertEquals(avas, avaService.findAll());
    }

    @Test
    public void should_find_ava_by_id() {
        //Given
        AVA ava = givenAVA();
        //When
        when(avaRepository.findById(ID)).thenReturn(Optional.of(ava));
        //Then
        assertEquals(ava, avaService.findById(ID));
    }

    @Test
    public void should_return_null_find_ava_by_id() {
        //Given
        when(avaRepository.findById(ID)).thenReturn(Optional.empty());
        //When
        //Then
        assertNull(avaService.findById(ID));
    }

    @Test
    public void should_save_ava() {
        //Given
        AVA ava = givenAVA();
        ava.setId(1L);
        when(avaRepository.save(ava)).thenReturn(ava);
        //When
        //Then
        assertEquals(ava, avaService.save(ava));
    }

    @Test
    public void should_update_ava() {
        //Given
        AVA ava = givenAVA();
        when(avaRepository.findById(ava.getId())).thenReturn(Optional.of(ava));
        when(avaRepository.save(ava)).thenReturn(ava);
        //When
        //Then
        assertEquals(ava, avaService.update(ava));
    }


    @Test
    public void should_delete_ava_by_id() {
        //Given
        AVA ava = givenAVA();
        when(avaRepository.findById(ava.getId())).thenReturn(Optional.of(ava));
        //When
        doNothing().when(avaRepository).deleteById(ID);
        //Then
        assertEquals(ava, avaService.deleteById(ID));
    }

    @Test
    public void should_return_null_delete_ava_by_id() {
        //Given
        AVA ava = givenAVA();
        when(avaRepository.findById(ava.getId())).thenReturn(Optional.empty());
        //When
        doNothing().when(avaRepository).deleteById(ID);
        //Then
        assertNull(avaService.deleteById(ID));
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

}