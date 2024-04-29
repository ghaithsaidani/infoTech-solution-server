package org.example.controllers;

import org.example.Main;
import org.example.config.PasswordEncoder;
import org.example.controllers.helper.HttpHelper;
import org.example.dto.AVADto;
import org.example.dto.AVAsDto;
import org.example.request.AVARequest;
import org.example.request.AVARequest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(classes = Main.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AVAControllerIntegrationTest {
    private static final String USER_URL = "http://localhost:%s/api/avas";
    private static final String USER_ID_URL = "http://localhost:%s/api/avas/%s";

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

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void should_save_ava() {

        String url = String.format(USER_URL, port);
        AVARequest createRequest = givenAVARequest();
        HttpEntity<AVARequest> request = HttpHelper.getHttpEntity(createRequest);

        ResponseEntity<AVADto> response = testRestTemplate.exchange(url, HttpMethod.POST, request, AVADto.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        //assertEquals(response.getBody().getId(),ID,0);
        assertEquals(response.getBody().getType(), TYPE);
        assertEquals(response.getBody().getActivite(), ACTIVITE);
        assertEquals(response.getBody().getNom(), NOM);
        assertEquals(response.getBody().getPrenom(), PRENOM);
        assertEquals(response.getBody().getAdresse(), ADRESSE);
        assertEquals(response.getBody().getTelephone(), TELEPHONE,0);
        assertEquals(response.getBody().getMontant(), montant, 0.0);
        assertEquals(response.getBody().getMontantDevise(), montantDevise, 0.0);
        assertEquals(response.getBody().getMontantDinar(), montantDinar, 0.0);
    }

    @Test
    public void should_find_ava_by_id() {

        String url = String.format(USER_ID_URL, port, ID);
        HttpEntity<String> request = HttpHelper.getHttpEntity();

        ResponseEntity<AVADto> response = testRestTemplate.exchange(url, HttpMethod.GET, request, AVADto.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getId(),ID,0);
        assertEquals(response.getBody().getType(), TYPE);
        assertEquals(response.getBody().getActivite(), ACTIVITE);
        assertEquals(response.getBody().getNom(), NOM);
        assertEquals(response.getBody().getPrenom(), PRENOM);
        assertEquals(response.getBody().getAdresse(), ADRESSE);
        assertEquals(response.getBody().getTelephone(), TELEPHONE,0);
        assertEquals(response.getBody().getMontant(), montant, 0.0);
        assertEquals(response.getBody().getMontantDevise(), montantDevise, 0.0);
        assertEquals(response.getBody().getMontantDinar(), montantDinar, 0.0);
    }

    @Test
    public void should_find_all_avas() {

        String url = String.format(USER_URL, port);
        HttpEntity<String> request = HttpHelper.getHttpEntity();

        ResponseEntity<AVAsDto> response = testRestTemplate.exchange(url, HttpMethod.GET, request, AVAsDto.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        List<AVADto> avaDtos = response.getBody().getAvaDtos();
        assertEquals(1, avaDtos.size());
        assertEquals(ID, avaDtos.get(0).getId(),0);
        assertEquals(TYPE,avaDtos.get(0).getType());
        assertEquals(ACTIVITE,avaDtos.get(0).getActivite());
        assertEquals(NOM,avaDtos.get(0).getNom());
        assertEquals(PRENOM,avaDtos.get(0).getPrenom());
        assertEquals(ADRESSE,avaDtos.get(0).getAdresse());
        assertEquals(TELEPHONE,avaDtos.get(0).getTelephone(),0);
        assertEquals(montant,avaDtos.get(0).getMontant(),0.0);
        assertEquals(montantDevise,avaDtos.get(0).getMontantDevise(),0.0);
        assertEquals(montantDinar,avaDtos.get(0).getMontantDinar(),0.0);
    }

    @Test
    public void should_update_ava() {

        String url = String.format(USER_ID_URL, port, ID);
        AVARequest avaRequest = givenAVARequest();
        HttpEntity<AVARequest> request = HttpHelper.getHttpEntity(avaRequest);

        ResponseEntity<AVADto> response = testRestTemplate.exchange(url, HttpMethod.PUT, request, AVADto.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(ID,response.getBody().getId(),0);
        assertEquals(TYPE,response.getBody().getType());
        assertEquals(ACTIVITE,response.getBody().getActivite());
        assertEquals(NOM,response.getBody().getNom());
        assertEquals(PRENOM,response.getBody().getPrenom());
        assertEquals(ADRESSE,response.getBody().getAdresse());
        assertEquals(TELEPHONE,response.getBody().getTelephone(),0);
        assertEquals(montant,response.getBody().getMontant(),0.0);
        assertEquals(montantDevise,response.getBody().getMontantDevise(),0.0);
        assertEquals(montantDinar,response.getBody().getMontantDinar(),0.0);
    }

    @Test
    public void should_delete_ava_by_id() {

        String url = String.format(USER_ID_URL, port, ID);
        HttpEntity<String> request = HttpHelper.getHttpEntity();

        ResponseEntity<AVADto> response = testRestTemplate.exchange(url, HttpMethod.DELETE, request, AVADto.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getId(),ID,0);
        assertEquals(response.getBody().getType(), TYPE);
        assertEquals(response.getBody().getActivite(), ACTIVITE);
        assertEquals(response.getBody().getNom(), NOM);
        assertEquals(response.getBody().getPrenom(), PRENOM);
        assertEquals(response.getBody().getAdresse(), ADRESSE);
        assertEquals(response.getBody().getTelephone(), TELEPHONE,0);
        assertEquals(response.getBody().getMontant(), montant, 0.0);
        assertEquals(response.getBody().getMontantDevise(), montantDevise, 0.0);
        assertEquals(response.getBody().getMontantDinar(), montantDinar, 0.0);
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
}
