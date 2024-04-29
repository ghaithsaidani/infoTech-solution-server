package org.example.controllers;


import org.example.controllers.helper.JsonHelper;
import org.example.dto.AVADto;
import org.example.dto.AVAsDto;
import org.example.mapper.AVAMapper;
import org.example.models.AVA;
import org.example.request.AVARequest;
import org.example.services.AVAService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@WebMvcTest(AVAController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AVAControllerMVCTest {

    private static final long ID= 1L;
    private static final Character TYPE= 'E';
    private static final String ACTIVITE="Études et conseils";
    private static final String NOM="InfoTech";
    private static final String PRENOM="Solutions";
    private static final String ADRESSE="Tunis";
    private static final Long TELEPHONE=12345678L;
    private static final float montant=5000;
    private static final float montantDevise=2000;
    private static final float montantDinar=1200;

    private static final String USER_URL = "/api/avas";
    private static final String USER_ID_URL = "/api/avas/{id}";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AVAService avaService;

    @MockBean
    private AVAMapper avaMapper;

    @Test
    public void should_save_ava_returns_200() throws Exception{
        AVARequest createRequest = givenAVARequest();
        AVA ava = givenAVA();
        AVADto avaDto = givenAVADto();
        when(avaMapper.map(createRequest)).thenReturn(ava);
        when(avaService.save(ava)).thenReturn(ava);
        when(avaMapper.map(ava)).thenReturn(avaDto);
        String createRequestJson = JsonHelper.toJson(createRequest).orElse("");
        String expected = JsonHelper.toJson(avaDto).orElse("");
        //When
        mockMvc.perform(post(USER_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createRequestJson))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    public void should_find_all_returns_200() throws Exception{
        AVA ava = givenAVA();
        List<AVA> avas = Collections.singletonList(ava);
        when(avaService.findAll()).thenReturn(avas);
        AVADto avaDto = givenAVADto();
        List<AVADto> avaDtos = Collections.singletonList(avaDto);
        when(avaMapper.map(avas)).thenReturn(avaDtos);
        AVAsDto avasDto = new AVAsDto(avaDtos);
        String expected = JsonHelper.toJson(avasDto).orElse("");
        mockMvc.perform(get(USER_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));

    }

    @Test
    public void should_find_ava_by_id_returns_200() throws Exception{
        AVA ava = givenAVA();
        when(avaService.findById(ID)).thenReturn(ava);
        AVADto avaDto = givenAVADto();
        when(avaMapper.map(ava)).thenReturn(avaDto);
        String expected = JsonHelper.toJson(avaDto).orElse("");
        mockMvc.perform(get(USER_ID_URL, ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    public void should_update_ava_returns_200() throws Exception{
        AVA ava = givenAVA();
        AVARequest avaRequest = givenAVARequest();
        AVADto avaDto = givenAVADto();
        when(avaMapper.map(ID, avaRequest)).thenReturn(ava);
        when(avaService.update(ava)).thenReturn(ava);
        when(avaMapper.map(ava)).thenReturn(avaDto);
        String avaRequestJson = JsonHelper.toJson(avaRequest).orElse("");
        String expected = JsonHelper.toJson(avaDto).orElse("");
        //When
        mockMvc.perform(put(USER_ID_URL, ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(avaRequestJson))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    public void should_delete_ava_returns_200() throws Exception{
        AVA ava = givenAVA();
        when(avaService.deleteById(ID)).thenReturn(ava);
        AVADto avaDto = givenAVADto();
        when(avaMapper.map(ava)).thenReturn(avaDto);
        String expected = JsonHelper.toJson(avaDto).orElse("");

        mockMvc.perform(delete(USER_ID_URL, ID)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }


    private AVARequest givenAVARequest() {
        AVARequest avaRequest = new AVARequest();
        avaRequest.setType(TYPE);
        avaRequest.setActivite(ACTIVITE);
        avaRequest.setNom(NOM);
        avaRequest.setPrenom(PRENOM);
        avaRequest.setAdresse(ADRESSE);
        avaRequest.setTelephone(TELEPHONE);
        avaRequest.setMontant(montant);
        avaRequest.setMontantDevise(montantDevise);
        avaRequest.setMontantDinar(montantDinar);
        return avaRequest;
    }

    private AVADto givenAVADto() {
        AVADto avaDto = new AVADto();
        avaDto.setId(ID);
        avaDto.setType(TYPE);
        avaDto.setActivite(ACTIVITE);
        avaDto.setNom(NOM);
        avaDto.setPrenom(PRENOM);
        avaDto.setAdresse(ADRESSE);
        avaDto.setTelephone(TELEPHONE);
        avaDto.setMontant(montant);
        avaDto.setMontantDevise(montantDevise);
        avaDto.setMontantDinar(montantDinar);
        return avaDto;
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