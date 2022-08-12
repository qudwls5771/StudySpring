package com.example.demo.domain.account_info;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
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


    @Column(length = 20, nullable = false)
    private String id;

    @Column(length = 20, nullable = false)
    private String password;

    private String email;

    //@Query()
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Temporal(TemporalType.DATE)
    private Date updateDate;


}
