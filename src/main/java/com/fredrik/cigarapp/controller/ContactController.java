package com.fredrik.cigarapp.controller;

import com.fredrik.cigarapp.model.Contact;
import com.fredrik.cigarapp.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ContactController {

  private ContactService contactService;
  private CigarController cigarController;

  @Autowired
  public ContactController(ContactService contactService, CigarController cigarController) {
    this.contactService = contactService;
    this.cigarController = cigarController;
  }

  // add contact

  @PostMapping("/saveContact")
  public String saveContact(Contact contact, RedirectAttributes redirectAttributes) {
    contactService.save(contact);
    redirectAttributes.addFlashAttribute("message", "Contact saved");
    return "index";
  }

  @GetMapping("/allContacts")
  public ResponseEntity<List<Contact>> getAll() {
    return contactService.getAllContacts();
  }

  @PostMapping("/deleteContact/{id}")
  public ResponseEntity<String> deleteContactById(@PathVariable Long id) {
    return ResponseEntity.ok(contactService.deleteContactbyId(id));
  }

}
