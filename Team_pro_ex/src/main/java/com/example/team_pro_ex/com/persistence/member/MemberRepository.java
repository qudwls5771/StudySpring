package com.example.team_pro_ex.com.persistence.member;

import com.example.team_pro_ex.com.Entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface MemberRepository extends JpaRepository<Member, String> {

    @Query(value = "select m from Member m where m.id = :id_1")
    Member findMemberByEmailOrId(String id_1);
}
