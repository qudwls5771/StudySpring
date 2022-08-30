package com.example.team_pro_ex.com.Controller.business_Member;


import com.example.team_pro_ex.com.Entity.business_Member.business_Member;
import com.example.team_pro_ex.com.Service.business_Member.business_MemberService;
import com.example.team_pro_ex.com.persistence.business_Member.business_MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/businessMember")
public class business_MemberController {

    private final business_MemberService business_Memberservice;


    @Autowired
    protected business_MemberController(business_MemberService business_memberService){
        this.business_Memberservice = business_memberService;
    }

    @GetMapping("/business_Member_Join/insert_Business_Member")
    public String business_Insert(){
        return "/businessMember/business_Member_Join/insert_Business_Member";
    }

    @PostMapping("/business_Member_Join/insert_Business_Member")
    public String business_Insert(business_Member business_member){
        System.out.printf("----사업자_가입-----");
        business_Memberservice.insertBusiness_Member(business_member);
        return "redirect:/businessMember/business_Member_Join/insert_Business_Member";
    }

    @PostMapping("/business_Member_Update/business_Member_Update")
    public String update_business_Member(business_Member business_member){
        System.out.printf("---------업데이트---------");
        business_Memberservice.updateBusiness_Member(business_member);
        return "redirect:/businessMember/business_Member_Update/business_Member_Update";
    }





}
