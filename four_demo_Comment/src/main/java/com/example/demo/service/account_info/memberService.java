package com.example.demo.service.account_info;

import com.example.demo.Entity.account_info.Member;

import java.util.List;

public interface memberService {
    //Email 또는 ID를 조회하여 튜플을 찾기
    Member getMemberWhereIdOrEmail(String email, String id);

    Member findEmail(String email);

    Member getMemberWhereIdAndROWNUL1(String id);

    List<Member> getMemberList();

    void insertMember(Member member);

    Member getMember(Member member);

    void  updateMember(Member member);

    void deleteMember(Member member);
}
