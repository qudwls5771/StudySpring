package com.example.team_pro_ex.com.Service.member;

import com.example.team_pro_ex.com.Entity.member.Member;
import com.example.team_pro_ex.com.persistence.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class memberServiceImpl implements memberService{

    //persistence.account_info => MemberRepository에 있는 CrudRepository<Member, Long> 사용
    @Autowired
    private MemberRepository memberRepo;

    @Autowired
    protected memberServiceImpl(MemberRepository memberRepo){
        this.memberRepo = memberRepo;
    }

    @Override
    public List<Member> getMemberList(Member member) {
        return null;
    }

    @Override
    public void insertMember(Member member) {

    }

    @Override
    public void updateMember(Member member) {

    }

    @Override
    public void deleteMember(Member member) {

    }
}
