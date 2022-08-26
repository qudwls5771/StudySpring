package com.example.team_pro_ex.com.Controller.member;

import com.example.team_pro_ex.com.Entity.member.Member;
import com.example.team_pro_ex.com.Service.member.memberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/Member/Member_Join")
public class memberController {

    private final memberService memberService;

    @Autowired
    protected memberController(memberService memberservice){
        this.memberService = memberservice;
    }

    @GetMapping("/insert_Member")
    public String insertMember(){
        return "Member/Member_Join/insert_Member";
    }
    //회원 가입
    @PostMapping("/insert_Member")
    public String insertMember(Member member){
        System.out.println("------insert-------");
        memberService.insertMember(member);
        return "redirect:Member/Member_Join/insert_Member";
    }
    //회원정보 수정
    @PostMapping("/update_Member")
    public String updateMember(Member member){
        System.out.println("------update--------");
        memberService.updateMember(member);
        return "redirect:Member/Member_Up_Del/update_Member";
    }

    //회원을 삭제하는게 아니라 수정한다. ID, Name, Join_m 및 날짜 테이블의 join_O을 제외한 값 전부 Null
    @PostMapping("/deleteUp_Member")
    public String deleteUpdateMember(Member member){
        System.out.println("-------delete-------");
        memberService.deleteUpdateMember(member);
        return "redirect:Member/Member_Up_Del/deletrUp_Member";
    }

}