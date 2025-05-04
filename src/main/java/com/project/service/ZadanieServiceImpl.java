package com.project.service;

import com.project.model.Zadanie;
import com.project.repository.ZadanieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ZadanieServiceImpl implements ZadanieService {

    @Autowired
    private ZadanieRepository zadanieRepository;

    @Override
    public Zadanie setZadanie(Zadanie zadanie) {
        return zadanieRepository.save(zadanie);
    }

    @Override
    public Optional<Zadanie> getZadanieById(Integer id) {
        return zadanieRepository.findById(id);
    }

    @Override
    public void deleteZadanieById(Integer id) {
        zadanieRepository.deleteById(id);
    }

    @Override
    public Zadanie updateZadanie(Integer id, Zadanie updatedZadanie) {
        return zadanieRepository.findById(id)
                .map(zadanie -> {
                    zadanie.setNazwa(updatedZadanie.getNazwa());
                    zadanie.setOpis(updatedZadanie.getOpis());
                    zadanie.setKolejnosc(updatedZadanie.getKolejnosc());
                    zadanie.setDataczasDodania(updatedZadanie.getDataczasDodania());
                    return zadanieRepository.save(zadanie);
                })
                .orElseThrow(() -> new RuntimeException("nie znaleziono zadania o ID:" + id));
    }

    @Override
    public Page<Zadanie> getAllZadania(Pageable pageable) {
        return zadanieRepository.findAll(pageable);
    }

    @Override
    public Page<Zadanie> getZadaniaByProjektId(Integer projektId, Pageable pageable) {
        return zadanieRepository.findZadaniaProjektu(projektId, pageable);
    }
}