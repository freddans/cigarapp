package com.fredrik.cigarapp.service;

import com.fredrik.cigarapp.model.Cigar;
import com.fredrik.cigarapp.repo.CigarRepo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CigarService {

    private CigarRepo repo;
    private Logger logger = Logger.getLogger(CigarService.class);

    @Autowired
    public CigarService(CigarRepo repo) {
        this.repo = repo;
    }

    public List<Cigar> getAllCigars() {
        return repo.findAll();
    }

    public List<Cigar> getAllCigarsWithLog() {

        logger.info("Client gets a list of cigars\n");
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

    public String editCigar(Cigar existingCigar, Cigar updatedCigar) {

        StringBuilder responseMessage = new StringBuilder("Cigar with id: " + existingCigar.getId() + " has been updated:");

        if (existingCigar.getId() != 0) {
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
            if (updatedCigar.getProfilePicture() != null) {
                responseMessage.append("\nProfilePicture updated from " + existingCigar.getProfilePicture() + " to " + updatedCigar.getProfilePicture());
                existingCigar.setProfilePicture(updatedCigar.getProfilePicture());
            }

            logger.info("UPDATED Cigar with id: " + existingCigar.getId() + ":\n" + responseMessage + "\n");

            save(existingCigar);
        } else {
            logger.warn("There is no cigar with that ID in the database\n");
        }


        return responseMessage.toString();
    }

    public Cigar saveWithLog(Cigar newCigar) {
        if (newCigar.getId() != null) {
            logger.warn("Aborted: An id was provided from client\n");
            throw new RuntimeException("Aborted: an id was provided from client");
        }
        // We dont want ID, database has auto_increment

        Cigar cigar = repo.save(newCigar);
        logger.info("A client added a new Cigar: " + cigar.getName() + "\n");
        return cigar;
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

    public String deleteWithLog(Cigar cigarToDelete) {

        Cigar cigarInfo = cigarToDelete;

        if (cigarToDelete != null) {

            repo.delete(cigarToDelete);

            logger.info("DELETED: " + cigarInfo.getName() + " with ID: " + cigarInfo.getId() + "\n");

            return "Cigar with ID: " + cigarInfo.getId() + " has been deleted";
        } else {
            return "ERROR: Cigar with id: " + cigarInfo.getId() + ", does not exist";
        }
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