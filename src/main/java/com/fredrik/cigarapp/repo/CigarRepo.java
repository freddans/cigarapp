package com.fredrik.cigarapp.repo;

import com.fredrik.cigarapp.model.Cigar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CigarRepo extends JpaRepository<Cigar, Long> {
    List<Cigar> findAllByBrand(String brand);
    List<Cigar> findByName(String name);
}
