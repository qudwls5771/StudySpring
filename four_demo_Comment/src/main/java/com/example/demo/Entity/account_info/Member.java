package com.example.demo.Entity.account_info;

import com.example.demo.Entity.account_info.base.BaseTimeEntity;
import com.example.demo.Entity.account_info.board.Board;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@AllArgsConstructor : 모든 매개변수를 갖는 생성자
//@NoArgsConstructor(access = AccessLevel.PROTECTED) : 매개변수 없는 생성자
//@Builder

//@Entity JPA가 이 객체를 기준으로 table을 만들어야 한다고 선언
@ToString
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member extends BaseTimeEntity implements Serializable {

    //SELCT [*컬럼명=객체의 필드] FROM TABLE_NAME*객체;
    //CREATE TABLE (
//        seq NUMBER primary key,
//        id  VARCHAR2(40) NOT NULL
//    )
    //JPA : 객체에 맞춰서 SQL문으로 바꿔주는 것 (번역)
    //@Id : table을 만들 때, 테이블의 튜플(row)를 식별할 수 있는 기본키
    @Id
    @GeneratedValue
    private Long seq;

    //table 끼리 조인을 하는 조건
    //1. Member.id는 member의 튜플마다 유일한 값 (유니크 키)
    //member마다 게시글(board)를 쓸 수 있다는 조건이 있으므로,
    //board입장에서는 member의 id가 유일해야지 식별할 수 있다
    //2. null처리 (null이 들어가면 board는 Id를 식별할 수 없다)
    @Column(length = 40, nullable = false, unique = true)
    private String id;


    //member는 여러개의 board를 가질 수 있다고 선언,
    //board들을 가지고 있다고 필드에 넣음 (JPA는 이 필드내용으로 테이블 연관관계(JOIN)으로 식별)
    //@OneToMany는 member 1튜플마다 여러개의 board를 가진다는 속성 선언과 다수 엔티티 연동에
    // Springboot는 Serializable 상속 요구함
    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<Board> boardList = new ArrayList<>();

    private String password;

    private String email;

    //deleteYn

}
