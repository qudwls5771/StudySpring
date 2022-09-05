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

    @GetMapping("/mJoin/Join")
    public String insertMember(Member input_member, Model model){
        System.out.println("get mapping account !!");
        System.out.println(input_member.getName());
        System.out.println(input_member.getPetW());
        Member member = new Member("","","","","","","","","", 0, "Y");
        member.getPetD();
        member.getPassword();
        member.getName();
        member.getPhoneNumber();
        member.getAddress();
        member.getPetT();
        member.getPetS();
        member.getPetD();
        member.getPetW();
        System.out.println("아이디 : "+ member.getId());
        System.out.println("비밀번호 : "+ member.getPassword());
        System.out.println("이름 : "+ member.getName());
        System.out.println("폰번 : "+ member.getPhoneNumber());
        System.out.println("주소 : "+ member.getAddress());
        System.out.println("펫 종류 :"+ member.getPetT());
        System.out.println("펫 성별 : "+ member.getPetS());
        System.out.println("펫 생년 : "+ member.getPetD());
        System.out.println("펫 몸무게 :" +member.getPetW());
        System.out.println("가입상태 : " +member.getJoinM());
        model.addAttribute("member", member);
        return "Member/mJoin/Join";
    }


    @PostMapping("/mJoin/Join")
    public String insertMember(@Valid Member member, Errors errors, Model model){
        System.out.println("---check---");
        System.out.println(member.getId());
        //@Valid : 클라이언트 입력 데이터가 dto클래스로 캡슐화되어 넘어올 때, 유효성을 체크하라는 어노테이션
        //Member에서 작성한 어노테이션을 기준으로 유효성 체크
        //여기서 Errors객체는 Member의 필드 유효성 검사 오류에 대한 정보를 저장하고 노출한다.
        //errors.hasErrors() : 유효성 검사에 실패한 필드가 있는지 확인
        if(errors.hasErrors()){
            //회원가입 실패 시, 입력 데이터를 유지
            model.addAttribute("member", member);
            System.out.println(member.getId());
            //회원가입 실패 시, 회원가입 페이지에서 입력했던 정보들을 그대로 유지하기 위해 입력받았던 데이터를 그대로 할당합니다.
            //insertMember(Member member) 함수에 파라미터를 정의해준 이유입니다.
            //Validation 관점에서는 필요없는 부분이지만, UX 측면에서 구현해주는 것이 좋다.
            //물론, thymeleaf에서도 코드가 들어가야 한다.
            
            //유효성 통과 못한 필드와 메세지를 핸들링
            Map<String, String> member_Availability = memberService.member_Availability(errors);
            for(String key : member_Availability.keySet()){
                model.addAttribute(key, member_Availability.get(key));
            }
            return "/Member/mJoin/Join";
        }
        memberService.insertMember(member);
        return "redirect:/index";
    }

    //회원정보 수정
    @PostMapping("/mUpdate/Update")
    public String updateMember(Member member){
        System.out.println("------update--------");
        System.out.println("--되는건가??--");
        memberService.updateMember(member);
        return "redirect:Member/mUpdate/Update";
    }

    //회원을 삭제하는게 아니라 수정한다. ID, Name, Join_m 및 날짜 테이블의 join_O을 제외한 값 전부 Null
    @PostMapping("/mDelete/upDelete")
    public String deleteUpdateMember(Member member){
        System.out.println("-------delete-------");
        memberService.deleteUpdateMember(member);
        return "redirect:Member/mDelete/upDelete";
    }

}
