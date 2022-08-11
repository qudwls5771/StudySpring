package com.example.demo.service.account_info;

import com.example.demo.domain.account_info.Member;
import com.example.demo.persistence.account_info.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class memberServiceImpl implements memberService{

    @Autowired //persistence.account_info => MemberRepository에 있는 CrudRepository<Member, Long> 사용
    private MemberRepository memberRepo;

    //모든 회원의 정보를 가져다 오는 것.
    //return List<Member> : 모든 회원의 정보를 List배열에 담아서 return
    @Override
    public List<Member> getMemberList() {
        //ArrayList를 List로 형변환!
        return (List<Member>) memberRepo.findAll();
    }
    //회원 1명의 정보를 Entity에 맞춰서 DB에 저장
    @Override
    public void insertMember(Member member) {
        memberRepo.save(member);
    }

    @Override
    public Member getMember(Member member) {
        return null;
    }

    @Override
    public void updateMember(Member member) {

    }

    @Override
    public void deleteMember(Member member) {

    }
}
