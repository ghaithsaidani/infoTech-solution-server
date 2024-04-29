package org.example.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.models.AVA;
import org.example.models.AVA;
import org.example.repositories.AVARepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AVAService {
    private final AVARepository avaRepository;


    public AVA save(AVA ava){
        return avaRepository.save(ava);
    }

    public List<AVA> findAll(){
        return avaRepository.findAll();
    }

    public AVA findById(Long id){
        return avaRepository.findById(id).orElse(null);
    }


    public AVA update(AVA ava){
        AVA existingAVA=avaRepository.findById(ava.getId()).orElse(null);
        assert existingAVA != null;
        existingAVA= ava;
        return avaRepository.save(existingAVA);
    }

    public AVA deleteById(Long id){
        AVA ava = findById(id);
        if (ava == null) {
            return null;
        }
        avaRepository.deleteById(id);
        return ava;
    }
}
