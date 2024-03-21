package com.fredrik.cigarapp.service;

import com.fredrik.cigarapp.model.Cigar;
import com.fredrik.cigarapp.repo.CigarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CigarService {

    private CigarRepo repo;

    @Autowired
    public CigarService(CigarRepo repo) {
        this.repo = repo;
    }

    public List<Cigar> getAllCigars() {
        return repo.findAll();
    }

    public Cigar getCigarById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<String> getAllBrands() {
        List<String> brandList = new ArrayList<>();

        for (Cigar cigar : repo.findAll()) {
            if (!brandList.contains(cigar.getBrand())) {
                brandList.add(cigar.getBrand());
            }
        }

        return brandList;
    }

    public List<String> getAllFavoriteOf() {
        List<String> favoriteOfList = new ArrayList<>();

        for (Cigar cigar : repo.findAll()) {
            favoriteOfList.add(cigar.getBrand() + ": " + cigar.getName() + ": " + cigar.getFavoriteOf());
        }

        return favoriteOfList;
    }

    public List<Cigar> getCigarsByBrand(String brand) {
        return repo.findAllByBrand(brand);
    }

    public void save(Cigar cigar) {
        repo.save(cigar);
    }

    public List<Cigar> saveMultipleCigars(List<Cigar> cigars) {
        List<Cigar> savedCigars = new ArrayList<>();
        for (Cigar cigar : cigars) {
            savedCigars.add(repo.save(cigar));
        }
        return savedCigars;
    }

    public void delete(Cigar cigar) {
        repo.delete(cigar);
    }

    // WEB EXTRA
    // Search bar
    // Method to search cigars by name
    public List<Cigar> searchCigarsByName(String name) {
        List <Cigar> cigarList = repo.findAll();
        return cigarList.stream()
                .filter(cigar -> cigar.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }
}