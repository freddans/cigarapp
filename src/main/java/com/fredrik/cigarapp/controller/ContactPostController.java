package com.fredrik.cigarapp.controller;

import com.fredrik.cigarapp.model.Cigar;
import com.fredrik.cigarapp.model.ContactPost;
import com.fredrik.cigarapp.service.ContactPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ContactPostController {

    private ContactPostService contactPostService;

    @Autowired
    public ContactPostController(ContactPostService contactPostService) {
        this.contactPostService = contactPostService;
    }

    // add contactpost through website form
    @PostMapping("/saveContactPost")
    public String saveContactPost(ContactPost contactPost, RedirectAttributes redirectAttributes) {
        contactPostService.save(contactPost);
        redirectAttributes.addFlashAttribute("message", "Contact saved");
        return "index";
    }

    // add contactpost through postman
    @PostMapping("/addContactPost")
    public ResponseEntity<ContactPost> save(@RequestBody ContactPost contactPost) {
        contactPostService.save(contactPost);
        return ResponseEntity.ok(contactPost);
    }

    // Add multiple contactposts by list in postman
    @PostMapping("/addMultipleContactPosts")
    public ResponseEntity<List<ContactPost>> addMultipleCigars(@RequestBody List<ContactPost> contactPosts) {
        List<ContactPost> multipleContactPosts = contactPostService.saveMultipleContactPosts(contactPosts);
        return ResponseEntity.ok(contactPosts);
    }

    @GetMapping("/allContactPosts")
    public ResponseEntity<List<ContactPost>> getAllContactPosts() {
        return contactPostService.getAllContacts();
    }

    @GetMapping("/contactPosts/{id}")
    @ResponseBody
    public ContactPost getContactPostById(@PathVariable Long id) {
        return contactPostService.getContactPostById(id);
    }

    // Edit in postman
    @PutMapping("/editContactPost/{id}")
    public ResponseEntity<String> editContactPostById(@PathVariable Long id, @RequestBody ContactPost updatedContactPost) {
        ContactPost existingContactPost = getContactPostById(id);

        StringBuilder responseMessage = new StringBuilder("ContactPost with id: " + existingContactPost.getId() + " has been updated:");

        if (updatedContactPost.getName() != null) {
            responseMessage.append("\nName updated from: '" + existingContactPost.getName() + "' to: '" + updatedContactPost.getName() + "'");
            existingContactPost.setName(updatedContactPost.getName());
        }
        if (updatedContactPost.getEmail() != null) {
            responseMessage.append("\nE-Mail updated from: '" + existingContactPost.getEmail() + "' to: '" + updatedContactPost.getEmail() + "'");
            existingContactPost.setEmail(updatedContactPost.getEmail());
        }
        if (updatedContactPost.getMessage() != null) {
            responseMessage.append("\nMessage updated from: '" + existingContactPost.getMessage() + "' to: '" + updatedContactPost.getMessage() + "'");
            existingContactPost.setMessage(updatedContactPost.getMessage());
        }

        contactPostService.save(existingContactPost);
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage.toString());
    }

    // Delete in postman
//    @DeleteMapping("/deleteContactPost/{id}")
//    public ResponseEntity<String> deleteContactPostById(@PathVariable Long id) {
//        ContactPost contactPostToDelete = getContactPostById(id);
//
//        if (contactPostToDelete != null) {
//            contactPostService.delete(contactPostToDelete);
//            return ResponseEntity.ok("ContactPost with ID: " + id + " has been deleted");
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR: ContactPost with id: " + id + ", does not exist");
//        }
//    }

    @PostMapping("/deleteContactPost/{id}")
    public ResponseEntity<Void> deleteContactPostById(@PathVariable Long id) {
        ContactPost contactPostToDelete = getContactPostById(id);

        if (contactPostToDelete != null) {
            contactPostService.delete(contactPostToDelete);
            return ResponseEntity.status(HttpStatus.FOUND).header(HttpHeaders.LOCATION, "/contactpost").build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // WEBPAGE
    // Test for table
    @GetMapping("/contactpost")
    public String contactPostPage(Model model) {
        model.addAttribute("contactPosts", contactPostService.getAllContactPosts());
        return "contactpost";
    }

    // get specific id for site when clicked
    @GetMapping("/contactpost/{id}")
    @ResponseBody
    public ContactPost getContactPostDetails(@PathVariable Long id) {
        return contactPostService.getContactPostById(id);
    }

}
