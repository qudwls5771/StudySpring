package com.example.demo.controller.account_info;

import com.example.demo.Entity.account_info.Member;
import com.example.demo.service.account_info.memberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

//디스패처 서블릿이 컨트롤러를 찾기 위해서 @Controller라고 선언
@Controller
@RequestMapping(path="/account")
public class memberController {
    @Autowired
    private memberService memberservice;


    //클라이언트 두 분류 : 사용자 관점
    //시스템관리관점(회원관리, 게시판 관리, 컨텐츠 관리)[웹솔루션을 관리하는 오너]
    //getAccountList(전체 외훤 목록 보기) : 웹솔루션에서 웹시스템을 관리하는 관리자 기능
    //public : 전부공개
    //String : 이 메소드가 실행 완료되면 최종적으로 리턴하는 타입(HTML 파일명을 찾기 위해)

    @GetMapping("/getAccountList")
    public String getAccountList(Model model) {
        //model : 컨트롤러에서 작업한 결과물을 HTML에 전달하기 위한 매개체
        //addAttribute : key/value으로 데이터를 저장하는 메서드
        //attributeName(key) : 뒤에 있는 value를 호출하기 위한 문자열(key)
        //memberService.getMemberList() : @Autowired로 선언된 MemberService 클래스를 호출하여
        //getMemberList()메서드 실행
        model.addAttribute("memberList", memberservice.getMemberList());
        return "/account/getAccountList";
    }

    //member : 클라이언트
    @GetMapping("/getAccount")
    public String getAccount(Member member, Model model) {
        System.out.println("-----------getAccount----------");
        model.addAttribute("member", memberservice.getMember(member));
        return "/account/getAccount";
    }

    //기준데이터의 무결성 체크를 위한 데이터 전체 조회와 일부 수정작업(sql 특정 컬럼의 값을 모두 gmail.com -> naver.com)

    //*백업 entity
    //회원정보가 일정 수치까지 다다르면(혹은 이벤트가 발생 updateAccountAll라는 메소드를 통해)
    //기존 entity의 테이블의 정보를 모두 백업 entity에 저장
    //+회원정보 1개의 테이블에서 관리하지 않아요 > 1년 지난 회원은 로그인을 안한 장기 휴식회원
    //+1년 미접속 계정은 따로 테이블에 옮겨서 저장 (예전 스타일)
    //날짜별로 1년이 지나면 새로 테이블을 생성해서 회원을 관리합니다.(회원가입한 날짜)
    // > 장점 : 최신회원들을 관리하는 마케팅부서에게 도움
    // > 장점 : DB 백업할 때도 테이블 파편화로 트랜잭션 하는 위험 또는 시간 절약
    // > 단점 : Admin(관리자)는 모든 회원정보를 볼 때 다수의 테이블을 동시에 봐야 하기 때문에
    //         Join을 써서 속도가 느림

    //retrun 타입이 String 이유 : HTMl 파일명을 찾기 위해
    //retrun 타입이 String이유 : HTML 파일명을 찾기 위해
    @GetMapping("/insertAccount")
    public String insertAccountView() {
        return "account/insertAccount";
    }

    //Member 라는 매개변수로 controller에 전달
    //Member(Entity)이고 DTO(Data Transfer Object)
    //어디선가 받거나 만든 데이터를 객체로 만드는 것 : DTO
    @PostMapping("/insertAccount")
    public String insertAccountView(Member member) {
        //클라이언트에서 ID/PW
        //createDate
        //updateDate
        member.setCreateDate(new Date());
        member.setUpdateDate(new Date());

        memberservice.insertMember(member);
        return "redirect:/account/getAccountList";
    }

    //deleteAccount : 회원정보 삭제

    @PostMapping("/updateAccount")
    public String updateAccount (Member member) {
        memberservice.updateMember(member);
        return "redirect:/account/getAccountList";
    }

    @GetMapping("/deleteAccount")
    public String deleteAccount(Member member) {
        System.out.println("-------delete-------");
        memberservice.deleteMember(member);
        return "redirect:/account/getAccountList";
    }

    @GetMapping("/selectAccount")
    public String selectAccount() {
        return "account/selectAccount";
    }

    @PostMapping("/selectAccount")
    public String resultAccount(Member member, Model model) {
        model.addAttribute("member",
                memberservice.getMemberWhereIdOrEmail(member.getEmail(), member.getId()));
        return "account/resultAccount";
    }

    @GetMapping("/selectEmail")
    public String selectEmail(){
        return "account/selectEmail";
    }

    @RequestMapping(value="/selectEmail", method = RequestMethod.POST)
    public String resultEmail(@RequestParam("email") String email, Member member, Model model){
        model.addAttribute("member", memberservice.findEmail(member.getEmail()));
        model.addAttribute("email", email);
        return "account/resultEmail";
    }

}
