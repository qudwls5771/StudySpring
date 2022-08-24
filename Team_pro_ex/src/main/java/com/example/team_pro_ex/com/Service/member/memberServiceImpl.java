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

    //회원 전체조회
    @Override
    public List<Member> getMemberList(Member member) {
        return (List<Member>) memberRepo.findAll();
    }

    //회원가입
    @Override
    public void insertMember(Member member) {
        memberRepo.save(member);
    }

    //회원정보 업데이트
    @Override
    public void updateMember(Member member) {
        Member findMember = memberRepo.findById(member.getId()).get();
        System.out.println(findMember.getId());
        System.out.println(findMember.getPassword());
        System.out.println(findMember.getName());
        System.out.println(findMember.getAddress());
        System.out.println(findMember.getPet_D());
        System.out.println(findMember.getPet_S());
        System.out.println(findMember.getPet_T());
        System.out.println(findMember.getPet_W());
        System.out.println(findMember.getJoin_M());
        System.out.println(findMember.getYear());
        System.out.println(findMember.getPhone_number());
        memberRepo.save(findMember);
    }

    //회원탈퇴 (겉으로는)
   // @Override
   // public void deleteMember(Member member) {
   //     memberRepo.deleteById(member.getId());
    //}




}
