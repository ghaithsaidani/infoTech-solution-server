package org.example.mapper;

import org.example.dto.AVADto;
import org.example.models.AVA;
import org.example.request.AVARequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AVAMapper {
    public AVADto map(AVA ava) {
        AVADto avaDto = new AVADto();
        avaDto.setId(ava.getId());
        avaDto.setType(ava.getType());
        avaDto.setEtat(ava.getEtat());
        avaDto.setActivite(ava.getActivite());
        avaDto.setNom(ava.getNom());
        avaDto.setPrenom(ava.getPrenom());
        avaDto.setAdresse(ava.getAdresse());
        avaDto.setTelephone(ava.getTelephone());
        avaDto.setMontant(ava.getMontant());
        avaDto.setMontantDevise(ava.getMontantDevise());
        avaDto.setMontantDinar(ava.getMontantDinar());
        return avaDto;
    }

    public AVA map(AVADto avaDto) {
        AVA ava = new AVA();
        ava.setId(avaDto.getId());
        ava.setType(avaDto.getType());
        ava.setEtat(avaDto.getEtat());
        ava.setActivite(avaDto.getActivite());
        ava.setNom(avaDto.getNom());
        ava.setPrenom(avaDto.getPrenom());
        ava.setAdresse(avaDto.getAdresse());
        ava.setTelephone(avaDto.getTelephone());
        ava.setMontant(avaDto.getMontant());
        ava.setMontantDevise(avaDto.getMontantDevise());
        ava.setMontantDinar(avaDto.getMontantDinar());
        return ava;
    }

    public AVA map(AVARequest avaRequest) {
        AVA ava = new AVA();
        ava.setType(avaRequest.getType());
        ava.setActivite(avaRequest.getActivite());
        ava.setNom(avaRequest.getNom());
        ava.setPrenom(avaRequest.getPrenom());
        ava.setAdresse(avaRequest.getAdresse());
        ava.setTelephone(avaRequest.getTelephone());
        ava.setMontant(avaRequest.getMontant());
        ava.setMontantDevise(avaRequest.getMontantDevise());
        ava.setMontantDinar(avaRequest.getMontantDinar());
        return ava;
    }

    public AVA map(Long id, AVARequest avaRequest) {
        AVA ava = new AVA();
        ava.setId(id);
        ava.setType(avaRequest.getType());
        ava.setActivite(avaRequest.getActivite());
        ava.setNom(avaRequest.getNom());
        ava.setPrenom(avaRequest.getPrenom());
        ava.setAdresse(avaRequest.getAdresse());
        ava.setTelephone(avaRequest.getTelephone());
        ava.setMontant(avaRequest.getMontant());
        ava.setMontantDevise(avaRequest.getMontantDevise());
        ava.setMontantDinar(avaRequest.getMontantDinar());
        return ava;
    }

    public List<AVADto> map(List<AVA> avas) {
        return avas
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
