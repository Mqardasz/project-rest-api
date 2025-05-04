package com.project.controller;

import com.project.model.Zadanie;
import com.project.service.ZadanieService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/zadania")
public class ZadanieController {

    private final ZadanieService zadanieService;

    @Autowired
    public ZadanieController(ZadanieService zadanieService) {
        this.zadanieService = zadanieService;
    }

    @GetMapping("/{zadanieId}")
    public ResponseEntity<Zadanie> getZadanieById(@PathVariable Integer zadanieId) {
        return ResponseEntity.of(zadanieService.getZadanieById(zadanieId));
    }

    @PostMapping
    public ResponseEntity<Void> createZadanie(@Valid @RequestBody Zadanie zadanie) {
        Zadanie createdZadanie = zadanieService.setZadanie(zadanie);
        URI location = URI.create(String.format("/api/zadania/%d", createdZadanie.getZadanieId()));
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{zadanieId}")
    public ResponseEntity<Void> updateZadanie(@PathVariable Integer zadanieId, @Valid @RequestBody Zadanie zadanie) {
        try {
            zadanieService.updateZadanie(zadanieId, zadanie);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{zadanieId}")
    public ResponseEntity<Void> deleteZadanie(@PathVariable Integer zadanieId) {
        try {
            zadanieService.deleteZadanieById(zadanieId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public Page<Zadanie> getAllZadania(Pageable pageable) {
        return zadanieService.getAllZadania(pageable);
    }

    @GetMapping(params = "projektId")
    public Page<Zadanie> getZadaniaByProjektId(@RequestParam Integer projektId, Pageable pageable) {
        return zadanieService.getZadaniaByProjektId(projektId, pageable);
    }
}