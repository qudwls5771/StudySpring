package com.example.team_pro_ex.com.Entity.member;

import com.example.team_pro_ex.com.Entity.Base.member_BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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
public class Member extends member_BaseEntity {

    @Id
    @Generated
    @Column(name = "member_id", length = 20, nullable = false)
    private String id;  // 아이디

    @Column(name = "member_password",length = 20, nullable = false)
    private String password; // 비밀번호

    @Column(name = "member_name", length = 30, nullable = false)
    private String name; // 이름

    @Column(name = "member_year", nullable = false)
    private String year; // 펫주인 생년월일

    @Column(name = "member_phone_number", nullable = false)
    private String phone_number; // 핸드폰 번호

    @Column(name = "member_address", length = 50, nullable = false)
    private String address; // 주소

    @Column(name = "member_pet_T", length = 20, nullable = false)
    private String pet_T; //--펫 종류

    @Column(name = "member_pet_S", length = 1, nullable = false)
    private String pet_S; //--펫 성별

    @Column(name = "member_pet_D", nullable = false)
    private String pet_D; //-- 펫 출생

    @Column(name = "member_pet_W", length = 10, nullable = false)
    private Integer pet_W; //--펫 몸무게

    // columnDefinition(default)설정 어노테이션? = "VARCHAR@(1)/타입 default 값을 'Y'로 설장한다."
    @Column(name = "member_join_M", length = 1, nullable = false ,columnDefinition = "VARCHAR@(1) default 'Y'")
    private String join_M; //--가입상태


}
