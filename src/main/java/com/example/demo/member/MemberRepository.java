package com.example.demo.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {

    @Query(value = "SELECT c FROM Member c WHERE c.memberId = :memberId")
    Optional<Member> findByMember(long memberId);
}
