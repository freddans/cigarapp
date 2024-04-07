package com.fredrik.cigarapp.controller;

import com.fredrik.cigarapp.model.ContactPost;
import com.fredrik.cigarapp.service.ContactPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class UserController {

    @Autowired
    ContactPostService contactPostService;

    @GetMapping("/site")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public String index(Authentication authentication, Model model) {
        if (authentication != null && authentication.isAuthenticated()) {
            if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                model.addAttribute("isAdmin", true);
            }
            if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
                model.addAttribute("isUser", true);
            }
        }
        return "index"; // This returns the logical view name of the Thymeleaf template
    }

    @GetMapping("/welcome")
    public String welcome() {

//        return "Welcome this endpoint is not secure";
        return "index";
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile() {

        return "Welcome to User Profile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {

        return "Welcome to Admin Profile";
    }

}