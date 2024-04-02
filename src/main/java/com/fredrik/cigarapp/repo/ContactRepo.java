package com.fredrik.cigarapp.repo;

import com.fredrik.cigarapp.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactRepo extends JpaRepository<Contact, Long> {
}
