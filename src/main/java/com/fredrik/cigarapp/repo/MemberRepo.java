package com.fredrik.cigarapp.repo;

import com.fredrik.cigarapp.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepo extends JpaRepository<Member, Long> {
    Member findMemberByEmail(String email);

    Member findMemberByUsername(String username);
}
