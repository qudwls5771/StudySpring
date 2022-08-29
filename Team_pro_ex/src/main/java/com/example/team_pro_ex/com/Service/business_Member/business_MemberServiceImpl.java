package com.example.team_pro_ex.com.Service.business_Member;

import com.example.team_pro_ex.com.Entity.business_Member.business_Member;
import com.example.team_pro_ex.com.persistence.business_Member.business_MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class business_MemberServiceImpl implements business_MemberService{

    @Autowired
    private business_MemberRepository business_memberRepo;

    @Autowired
    protected business_MemberServiceImpl(business_MemberRepository business_memberRepo){
        this.business_memberRepo = business_memberRepo;
    }

    @Override
    public List<business_Member> getbusiness_MemberList(business_Member business_member) {
        System.out.println("--------사업자 회원 목록---------");
        return (List<business_Member>) business_memberRepo.findAll();
    }

    @Override
    public void insertBusiness_Member(business_Member business_member) {
        System.out.println("--------사업자 회원 가입---------");
        business_memberRepo.save(business_member);
    }

    @Override
    public void updateBusiness_Member(business_Member business_member) {
        business_Member findBusiness_member = new business_Member();
        System.out.println("--------회원정보 수정---------");
        System.out.println(findBusiness_member.getBusiness_Number());
        System.out.println(findBusiness_member.getId());
        System.out.println(findBusiness_member.getPassword());
        System.out.println(findBusiness_member.getPhone_number());
        System.out.println(findBusiness_member.getStore_number());
        System.out.println(findBusiness_member.getStore_Name());
        System.out.println(findBusiness_member.getAddress());
        System.out.println(findBusiness_member.getJoin_M());
        System.out.println("--------회원정보 수정---------");
        business_memberRepo.save(findBusiness_member);
    }

    @Override
    public void deleteUpdateBusiness_Member(business_Member business_member) {
        //business_memberRepo.(business_member);
        System.out.println("--------사업자 회원 삭제(수정  = Null처리)---------");
    }
}
