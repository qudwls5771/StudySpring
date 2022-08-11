package com.example.demo.controller.account_info;

import com.example.demo.domain.account_info.Member;
import com.example.demo.service.account_info.memberService;
import com.example.demo.service.account_info.memberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

//디스패처 서블릿이 컨트롤러를 찾기 위해서 @Controller라고 선언
@Controller
public class memberController {
    @Autowired
    private memberService memberservice;

    @GetMapping("index")
    public String index(){
        return "index";
    }

    //retrun 타입이 String 이유 : HTMl 파일명을 찾기 위해
    @GetMapping("/account/insertAccount")
    public String insertAccountview(){
        return "/account/insertAccount";
    }

    @PostMapping("/account/insertAccount")
    public String insertAccountview(Member member){
        //클라이언트에서 ID/PWD
        member.setCreateDate(new Date());
        member.setUpdateDate(new Date());
        memberservice.insertMember(member);
        return "index";
    }

}
