package com.example.team_pro_ex.com.Entity.member;

import com.example.team_pro_ex.com.Entity.Base.member_BaseEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String id;  // 아이디

    @Column(name = "member_password",length = 18)
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password; // 비밀번호

    @Column(name = "member_name")
    private String name; // 이름

    @Column(name = "member_year")
    private String year; // 펫주인 생년월일

    @Column(name = "member_phone_number")
    private String phone_number; // 핸드폰 번호

    @Column(name = "member_address", length = 50)
    private String address; // 주소

    @Column(name = "member_pet_T", length = 20)
    private String pet_T; //--펫 종류

    @Column(name = "member_pet_S", length = 1)
    private String pet_S; //--펫 성별

    @Column(name = "member_pet_D")
    private String pet_D; //-- 펫 출생

    @Column(name = "member_pet_W", length = 10)
    private Integer pet_W; //--펫 몸무게

    @Column(name = "member_join_M", length = 1, nullable = false)
    @ColumnDefault("'Y'")
    private String join_M; //--가입상태


}
