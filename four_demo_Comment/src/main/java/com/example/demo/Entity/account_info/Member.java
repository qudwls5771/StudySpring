package com.example.demo.Entity.account_info;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@ToString
@Entity // 디비랑 연결하기 위해 선언! (JPA가 이 객체를 기준으로 table을 만들어야 한다고 선언)
@Getter
@Setter
public class Member {


    //Select [*컬럼명=객체의 필드] from [table명]
    // create table [table명](
    // seq number primary key,
    // id varchar2(20) Not Null
    //)

    //JPA : 객체에 맞춰서 SQL문으로 바꿔주는 것(번역역)
    // //@Id : table을 만들 때, 테이블의 튜플을(row)를 식별할 수 있는 기본 키
    @Id
    @GeneratedValue
    private Long seq;

  //  @NotBlank(message = "ID를 입력해주세요.")
  //  @Size(min = 5, max=20, message = "ID는 5자 이상 20자이하로 입력해주세요.")
    @Column(length = 20, nullable = false)
    private String id;

  //  @NotBlank(message = "비밀번호를 입력해주세요.")
  //  @Size(min = 8, max = 20, message = "비밀번호 8자 이상 20자 이하로 입력해주세요.")
    @Column(length = 20, nullable = false)
    private String password;

 //   @NotBlank(message = "이메일 주소를 입력해주세요.")
 //   @Email(message = "올바른 이메일 주소를 입력해주세요.")
    private String email;

    //@Query()
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Temporal(TemporalType.DATE)
    private Date updateDate;


}
