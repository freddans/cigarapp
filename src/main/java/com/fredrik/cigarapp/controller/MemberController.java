package com.fredrik.cigarapp.controller;

import com.fredrik.cigarapp.model.Cigar;
import com.fredrik.cigarapp.model.ContactPost;
import com.fredrik.cigarapp.model.Member;
import com.fredrik.cigarapp.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/member/add")
    public String addMember(Member member, RedirectAttributes redirectAttributes) {
        memberService.save(member);
        redirectAttributes.addFlashAttribute("message", "Contact saved");
        return "redirect:/";
    }

    @GetMapping("/member/all")
    public ResponseEntity<List<Member>> allMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @GetMapping("/member/{id}")
    @ResponseBody
    public Member getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id);
    }

    @DeleteMapping("/member/delete/{id}")
    public ResponseEntity<String> deleteMemberById(@PathVariable Long id) {
        Member memberToDelete = getMemberById(id);

        if (memberToDelete != null) {
            memberService.delete(memberToDelete);
            return ResponseEntity.ok("Member with ID: " + id + " has been deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR: Member with id: " + id + ", does not exist");
        }
    }

    // Login
    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        RedirectAttributes redirectAttributes) {
        Member member = memberService.authenticate(username, password);
        if (member != null) {
            return "redirect:/";
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/login";
        }
    }
}
