package com.fredrik.cigarapp.service;

import com.fredrik.cigarapp.model.Cigar;
import com.fredrik.cigarapp.repo.CigarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CigarService {

    @Autowired
    CigarRepo repo;

    public List<Cigar> getAllCigars() {
        return repo.findAll();
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
}