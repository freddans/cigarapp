package com.fredrik.cigarapp.controller;
import com.fredrik.cigarapp.model.Cigar;
import com.fredrik.cigarapp.service.CigarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    private CigarService service;

    @Autowired
    public CigarController(CigarService service) {
        this.service = service;
    }

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

    @GetMapping("brands/{brand}")
    public ResponseEntity<List<Cigar>> getBrandsByBrand(@PathVariable("brand") String brand) {
        List<Cigar> test = new ArrayList<>();
        service.getCigarsByBrand(brand);

        for (Cigar cigar : service.getCigarsByBrand(brand)) {
            if (cigar.getBrand().contains(brand)) {
                test.add(cigar);
            }
        }

        return ResponseEntity.ok(test);
    }

    @GetMapping("/favorites")
    @ResponseBody
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

    // get Cigar by Id
    @GetMapping("/cigar/{id}")
    @ResponseBody
    public Cigar getCigarById(@PathVariable Long id) {
        return service.getCigarById(id);
    }

    // Edit in postman
    @PutMapping("/editcigar/{id}")
    public ResponseEntity<String> editById(@PathVariable Long id, @RequestBody Cigar updatedCigar) {
        Cigar existingCigar = getCigarById(id);

        StringBuilder responseMessage = new StringBuilder("Cigar with id: " + existingCigar.getId() + " has been updated:");

        if (updatedCigar.getName() != null) {
            responseMessage.append("\nName updated from " + existingCigar.getName() + " to " + updatedCigar.getName());
            existingCigar.setName(updatedCigar.getName());
        }
        if (updatedCigar.getBrand() != null) {
            responseMessage.append("\nBrand updated from " + existingCigar.getBrand() + " to " + updatedCigar.getBrand());
            existingCigar.setBrand(updatedCigar.getBrand());
        }
        if (updatedCigar.getOrigin() != null) {
            responseMessage.append("\nOrigin updated from " + existingCigar.getOrigin() + " to " + updatedCigar.getOrigin());
            existingCigar.setOrigin(updatedCigar.getOrigin());
        }
        if (updatedCigar.getDuration() != null) {
            responseMessage.append("\nDuration updated from " + existingCigar.getDuration() + " to " + updatedCigar.getDuration());
            existingCigar.setDuration(updatedCigar.getDuration());
        }
        if (updatedCigar.getBody() != null) {
            responseMessage.append("\nBody updated from " + existingCigar.getBody() + " to " + updatedCigar.getBody());
            existingCigar.setBody(updatedCigar.getBody());
        }
        if (updatedCigar.getLength() != null) {
            responseMessage.append("\nLength updated from " + existingCigar.getLength() + " to " + updatedCigar.getLength());
            existingCigar.setLength(updatedCigar.getLength());
        }
        if (updatedCigar.getHandRolled() != null) {
            responseMessage.append("\nHandrolled updated from " + existingCigar.getHandRolled() + " to " + updatedCigar.getHandRolled());
            existingCigar.setHandRolled(updatedCigar.getHandRolled());
        }
        if (updatedCigar.getRingGage() != null) {
            responseMessage.append("\nRing gage updated from " + existingCigar.getRingGage() + " to " + updatedCigar.getRingGage());
            existingCigar.setRingGage(updatedCigar.getRingGage());
        }
        if (updatedCigar.getStatus() != null) {
            responseMessage.append("\nStatus updated from " + existingCigar.getStatus() + " to " + updatedCigar.getStatus());
            existingCigar.setStatus(updatedCigar.getStatus());
        }
        if (updatedCigar.getPrice() != null) {
            responseMessage.append("\nPrice updated from " + existingCigar.getPrice() + " to " + updatedCigar.getPrice());
            existingCigar.setPrice(updatedCigar.getPrice());
        }
        if (updatedCigar.getBoxPrice() != null) {
            responseMessage.append("\nBoxprice updated from " + existingCigar.getBoxPrice() + " to " + updatedCigar.getBoxPrice());
            existingCigar.setBoxPrice(updatedCigar.getBoxPrice());
        }
        if (updatedCigar.getBoxAmount() != null) {
            responseMessage.append("\nBoxamount updated from " + existingCigar.getBoxAmount() + " to " + updatedCigar.getBoxAmount());
            existingCigar.setBoxAmount(updatedCigar.getBoxAmount());
        }
        if (updatedCigar.getRating() != null) {
            responseMessage.append("\nRating updated from " + existingCigar.getRating() + " to " + updatedCigar.getRating());
            existingCigar.setRating(updatedCigar.getRating());
        }
        if (updatedCigar.getFavoriteOf() != null) {
            responseMessage.append("\nFavoriteOf updated from " + existingCigar.getFavoriteOf() + " to " + updatedCigar.getFavoriteOf());
            existingCigar.setFavoriteOf(updatedCigar.getFavoriteOf());
        }
        if (updatedCigar.getImagePath() != null) {
            responseMessage.append("\nImgPath updated from " + existingCigar.getImagePath() + " to " + updatedCigar.getImagePath());
            existingCigar.setImagePath(updatedCigar.getImagePath());
        }
        if (updatedCigar.getBrandImagePath() != null) {
            responseMessage.append("\nBrandImagePath updated from " + existingCigar.getBrandImagePath() + " to " + updatedCigar.getBrandImagePath());
            existingCigar.setBrandImagePath(updatedCigar.getBrandImagePath());
        }
        if (updatedCigar.getOriginImagePath() != null) {
            responseMessage.append("\nOriginImagePath updated from " + existingCigar.getOriginImagePath() + " to " + updatedCigar.getOriginImagePath());
            existingCigar.setOriginImagePath(updatedCigar.getOriginImagePath());
//            force();
        }

        service.save(existingCigar);
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage.toString());
    }

    // Delete in postman
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCigarById(@PathVariable Long id) {
        Cigar cigarToDelete = getCigarById(id);

        if (cigarToDelete != null) {
            service.delete(cigarToDelete);
            return ResponseEntity.ok("Cigar with ID: " + id + " has been deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR: Cigar with id: " + id + ", does not exist");
        }
    }


    // Force
//    @PutMapping("/forceFlag")
//    public void force() {
//        List<Cigar> cigarList = service.getAllCigars();
//        for (Cigar cigar : cigarList) {
//            if (cigar.getOrigin().contains("Nicaragua")) {
//                cigar.setOriginImagePath("/icons/origin/nicaragua.png");
//            } else if (cigar.getOrigin().contains("Cuba")) {
//                cigar.setOriginImagePath("/icons/origin/cuba.png");
//            } else if (cigar.getOrigin().contains("Dominican Republic")) {
//                cigar.setOriginImagePath("/icons/origin/dominicanrepublic.png");
//            }
//            if (!cigar.getImagePath().contains("/images/cigar/cigar_placeholder.png")) {
//                cigar.setImagePath("/images/cigar/cigar_placeholder.png");
//            }
//
//            service.save(cigar);
//        }
//        System.out.println("done");
//    }


    // WEBPAGE
    @GetMapping("/main")
    public String mainPage() {
        return "MainPage";
    }

    @GetMapping("/test")
    public String testPage(Model model) {
        return "TestPage";
    }

    @GetMapping("/toggleModal")
    public String toggleModal(Model model) {
        return "modalcontent :: modal";
    }

    // Test for table
    @GetMapping("/cigardb")
    public String getCigarDbPage(Model model) {
        model.addAttribute("showModal", true);
        model.addAttribute("cigars", service.getAllCigars());
        return "CigarDB";
    }

    // cigarprofile webtest
    @GetMapping("/data")
    public String getData() {
        return "{\"name\":\"John\", \"age\":30}";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }


}