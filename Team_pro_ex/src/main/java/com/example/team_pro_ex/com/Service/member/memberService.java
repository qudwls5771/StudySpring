package com.example.team_pro_ex.com.Service.member;

import com.example.team_pro_ex.com.Entity.member.Member;

import java.util.List;

public interface memberService {
    
    //회원 목록
    List<Member> getMemberList(Member member);
    //회원가입
    void insertMember(Member member);
    //회원정보 수정
    void updateMember(Member member);
    //회원탈퇴
    void deleteMember(Member member);

}
