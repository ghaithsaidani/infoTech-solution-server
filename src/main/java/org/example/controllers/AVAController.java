package org.example.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.AVADto;
import org.example.dto.AVAsDto;
import org.example.mapper.AVAMapper;
import org.example.models.AVA;
import org.example.request.AVARequest;
import org.example.services.AVAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/avas")
@CrossOrigin(origins = "http://localhost:4200")
public class AVAController {
    @Autowired
    private AVAService avaService;

    @Autowired
    private AVAMapper avaMapper;

    @GetMapping("")
    public AVAsDto findAll() {
        List<AVA> avas = avaService.findAll();
        List<AVADto> avaDtos = avaMapper.map(avas);
        return new AVAsDto(avaDtos);
    }

    @GetMapping("/{id}")
    public AVADto findById(@PathVariable Long id) {
        AVA ava = avaService.findById(id);
        if (ava == null) {
            return null;
        }
        return avaMapper.map(ava);
    }

    @PostMapping("")
    public AVADto save(@RequestBody AVARequest avaRequest) {
        AVA ava = avaMapper.map(avaRequest);
        ava = avaService.save(ava);
        if (ava == null) {
            return null;
        }
        return avaMapper.map(ava);
    }

    @DeleteMapping("/{id}")
    public AVADto deleteById(@PathVariable Long id) {
        AVA ava = avaService.deleteById(id);
        if (ava == null) {
            return null;
        }
        return avaMapper.map(ava);
    }

    @PutMapping("/{id}")
    public AVADto update(@PathVariable Long id, @RequestBody AVARequest avaRequest) {
        AVA ava = avaMapper.map(id, avaRequest);
        ava = avaService.update(ava);
        if (ava == null) {
            return null;
        }
        return avaMapper.map(ava);
    }
}
