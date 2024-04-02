package com.fredrik.cigarapp.service;

import com.fredrik.cigarapp.model.Contact;
import com.fredrik.cigarapp.repo.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
  private ContactRepo contactRepo;

  @Autowired
  public ContactService(ContactRepo contactRepo) {
    this.contactRepo = contactRepo;
  }

  public Contact save(Contact contact) {
    return contactRepo.save(contact);
  }

  public ResponseEntity<List<Contact>> getAllContacts() {
    List <Contact> allContacts = contactRepo.findAll();
    return ResponseEntity.ok(allContacts);
  }

  public String deleteContactbyId(Long id) {
    Optional<Contact> contactToBeDeleted = contactRepo.findById(id);
    if (contactToBeDeleted.isPresent()) {
      contactRepo.delete(contactToBeDeleted.get());
      return "Deleted contact with id: " + contactToBeDeleted.get().getId();
    } else {
      return "Contact not found";
    }
  }
}
