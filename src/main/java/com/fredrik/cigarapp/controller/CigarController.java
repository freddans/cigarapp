package com.fredrik.cigarapp.controller;

import ch.qos.logback.classic.Logger;
import com.fredrik.cigarapp.model.Cigar;
import com.fredrik.cigarapp.service.CigarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class CigarController {

    @Autowired
    CigarService service;

    @GetMapping({"/o", "/viewCigarList", "/viewCigarBrandList"})
    public String viewAllCigars(Model model, @ModelAttribute("message") String message,
                                @RequestParam(name = "brand", required = false) String selectedBrand) {
        if (selectedBrand != null && !selectedBrand.isEmpty()) {
            List<Cigar> cigarsByBrand = service.getCigarsByBrand(selectedBrand);
            model.addAttribute("list", cigarsByBrand);
        } else {
            model.addAttribute("list", service.getAllCigars());
        }
        model.addAttribute("brands", service.getAllBrands());
        model.addAttribute("message", message);
        return "ViewCigarList";
    }

    @GetMapping("/")
    public String test(Model model) {
        List<Cigar> cigars = service.getAllCigars();
        model.addAttribute("list", cigars);
        return "ViewCigarList";
    }

    @PostMapping("/add")
    public ResponseEntity<Cigar> save(@RequestBody Cigar cigar) {
        service.save(cigar);
        return ResponseEntity.ok(cigar);
    }

    @PostMapping("/addm")
    public ResponseEntity<List<Cigar>> addMultipleCigars(@RequestBody List<Cigar> cigars) {
        List<Cigar> multipleCigars = service.saveMultipleCigars(cigars);
        return ResponseEntity.ok(multipleCigars);
    }

    @GetMapping("/cigars")
    public ResponseEntity<List<Cigar>> getCigars() {
        List<Cigar> cigars = service.getAllCigars();
        return ResponseEntity.ok(cigars);
    }

    @GetMapping("/brands")
    public ResponseEntity<List<String>> getBrands() {
        List<String> cigarBrands = service.getAllBrands();
        return ResponseEntity.ok(cigarBrands);
    }

    @GetMapping("/celebrities")
    public ResponseEntity<List<String>> getFavoriteOf() {
        List<String> favoriteCigars = service.getAllFavoriteOf();

        // sort by name:
        Collections.sort(favoriteCigars, new Comparator<String>() {
            @Override
            public int compare(String cigar1, String cigar2) {
                String[] part1 = cigar1.split(":");
                String[] part2 = cigar2.split(":");

                // Compare brand names
                return part1[2].trim().compareTo(part2[2].trim());
            }
        });


        return ResponseEntity.ok(favoriteCigars);
    }
    @GetMapping("/edit")
    public String edit(Model model) {
        List<Cigar> cigarList = new ArrayList<>();
        for (Cigar cigar : service.getAllCigars()) {
            cigarList.add(cigar);
        }
        model.addAttribute("list", cigarList);

        return "EditCigar";
    }


}