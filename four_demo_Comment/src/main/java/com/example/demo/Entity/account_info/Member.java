package com.example.demo.Entity.account_info;

import com.example.demo.Entity.base.BaseTimeEntity;
import com.example.demo.Entity.board.Board;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.jpa.repository.Query;

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

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //*영속화 => 객체를 어딘가에 영원히 저장하겠다.
    //JVM밖에서도 객체를 영원히 저장하고 싶다.
    //Commit, flush, persist를 포괄하는 내용
    //SQL(MyBatis)는 DB틀에 맞춘 mapper라고 정의한다면,
    //JPA는 객체(Entity, 튜플)단위로 DB에 저장하는 개념을 영속화 한다고 정의

    //*IDENTITY : DB에 필드값을 저장 후에 기본키를 생성
    //Entity가 영속상태가 되기 위해서는 식별자가 필수

    //*Sequence : DB(Oracle) Sequence 함수 기는ㅇ을 활용하여 생성

    //*Table : Seq(시퀀스)를 정보로 갖고 있는 테이블을 만들고, seq칼럼값을 저장한 뒤 불러온다.
    //여타 위에 전략과 달리 임의의 seq Table을 만들기 때문에 table 성능이 좋지 않을경우(튜닝x)
    //속도적인 문제를 야기할 수 있다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @BatchSize(size= 100)
    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<Board> boardList = new ArrayList<>();

    private String password;


    private String email;

    //deleteYn

}
