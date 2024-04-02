package com.fredrik.cigarapp.service;

import com.fredrik.cigarapp.model.Cigar;
import com.fredrik.cigarapp.model.ContactPost;
import com.fredrik.cigarapp.repo.ContactPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContactPostService {
  private ContactPostRepo contactPostRepo;

  @Autowired
  public ContactPostService(ContactPostRepo contactPostRepo) {
    this.contactPostRepo = contactPostRepo;
  }

  // Postman
  public ContactPost save(ContactPost contactPost) {
    return contactPostRepo.save(contactPost);
  }

  public List<ContactPost> saveMultipleContactPosts(List<ContactPost> contactPosts) {
    List<ContactPost> savedContactPosts = new ArrayList<>();
    for (ContactPost contactPost : contactPosts) {
      savedContactPosts.add(contactPostRepo.save(contactPost));
    }
    return savedContactPosts;
  }


  public ResponseEntity<List<ContactPost>> getAllContacts() {
    List <ContactPost> allContactPosts = contactPostRepo.findAll();
    return ResponseEntity.ok(allContactPosts);
  }

  public ContactPost getContactPostById(Long id) {

    return contactPostRepo.findById(id).orElse(null);
  }

  public String deleteContactbyId(Long id) {
    Optional<ContactPost> contactToBeDeleted = contactPostRepo.findById(id);
    if (contactToBeDeleted.isPresent()) {
      contactPostRepo.delete(contactToBeDeleted.get());
      return "Deleted contact with id: " + contactToBeDeleted.get().getId();
    } else {
      return "Contact not found";
    }
  }

  public void delete(ContactPost contactPost) {
    contactPostRepo.delete(contactPost);
  }

  // List for website
  public List<ContactPost> getAllContactPosts() {
    return contactPostRepo.findAll();
  }
}
