package com.fredrik.cigarapp.repo;

import com.fredrik.cigarapp.model.ContactPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactPostRepo extends JpaRepository<ContactPost, Long> {
}
