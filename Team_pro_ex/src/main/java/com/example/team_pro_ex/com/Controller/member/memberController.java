package com.example.team_pro_ex.com.Controller.member;

import com.example.team_pro_ex.com.Entity.member.Member;
import com.example.team_pro_ex.com.Service.member.memberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping(path = "/Member")
public class memberController {

    private final memberService memberService;

    @Autowired
    protected memberController(memberService memberservice){
        this.memberService = memberservice;
    }

    @GetMapping("/Member_Join/insert_Member")
    public String insertMember(){
        return "Member/Member_Join/insert_Member";
    }
    //회원 가입
    @PostMapping("/Member_Join/insert_Member")
    public String insertMember(Member member){
        System.out.println("------insert-------");
        memberService.insertMember(member);
        return "redirect:Member/Member_Join/insert_Member";
    }

    /* 회원가입 */    @PostMapping("/auth/joinProc")
    public String joinProc(@Valid Member member, Errors errors, Model model) {
        if (errors.hasErrors()) {            /* 회원가입 실패시 입력 데이터 값을 유지 */
            model.addAttribute("member", member);
            /* 유효성 통과 못한 필드와 메시지를 핸들링 */
            Map<String, String> validatorResult = memberService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }            /* 회원가입 페이지로 다시 리턴 */
            return "/user/user-join";        }
        memberService.insertMember(member);
        return "redirect:/auth/login";
    }




    //회원정보 수정
    @PostMapping("/Member_Update/update_Member")
    public String updateMember(Member member){
        System.out.println("------update--------");
        memberService.updateMember(member);
        return "redirect:Member/Member_Update/update_Member";
    }

    //회원을 삭제하는게 아니라 수정한다. ID, Name, Join_m 및 날짜 테이블의 join_O을 제외한 값 전부 Null
    @PostMapping("/deleteUp_Member")
    public String deleteUpdateMember(Member member){
        System.out.println("-------delete-------");
        memberService.deleteUpdateMember(member);
        return "redirect:Member/Member_Up_Del/deletrUp_Member";
    }

}
