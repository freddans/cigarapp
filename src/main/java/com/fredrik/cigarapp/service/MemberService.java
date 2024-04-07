package com.fredrik.cigarapp.service;

import com.fredrik.cigarapp.model.Cigar;
import com.fredrik.cigarapp.model.ContactPost;
import com.fredrik.cigarapp.model.Member;
import com.fredrik.cigarapp.repo.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private MemberRepo memberRepo;

    @Autowired
    public MemberService(MemberRepo memberRepo) {
        this.memberRepo = memberRepo;
    }

//    public Member save(Member member) {
//        return memberRepo.save(member);
//    }

    public Member save(Member member) {
        if (member.getRole() == null || member.getRole().isEmpty()) {
            member.setRole("ROLE_USER");
        }
        return memberRepo.save(member);
    }

    // Get all members
    public List<Member> getAllMembers() {
        List<Member> members = memberRepo.findAll();
        if (members.isEmpty()) {
            System.out.println("EMPTY");
            return null;
        }
        return members;
    }

    public Member getMemberById(Long id) {
        return memberRepo.findById(id).orElse(null);
    }

    public boolean getMemberByEmail(String email) {
        return memberRepo.findMemberByEmail(email) != null;
    }

//    public Member getMemberById(Long id) {
//        Optional<Member> member = memberRepo.findById(id);
//
//        return member.orElse(null);
//    }

    public void delete(Member member) {
        memberRepo.delete(member);
    }

    // Login
    public Member authenticate(String username, String password) {
        Member member = memberRepo.findMemberByUsername(username);
        if (member != null && member.getPassword().equals(password)) {
            return member;
        } else {
            return null;
        }
    }
}
