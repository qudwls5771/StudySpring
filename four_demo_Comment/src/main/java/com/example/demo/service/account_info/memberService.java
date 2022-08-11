package com.example.demo.service.account_info;

import com.example.demo.domain.account_info.Member;

import java.util.List;

public interface memberService {

    List<Member> getMemberList();

    void insertMember(Member member);

    Member getMember(Member member);

    void  updateMember(Member member);

    void deleteMember(Member member);
}
