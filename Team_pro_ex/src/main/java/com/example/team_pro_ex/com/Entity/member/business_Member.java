package com.example.team_pro_ex.com.Entity.member;

import com.example.team_pro_ex.com.Entity.Base.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
@ToString
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class business_Member extends BaseEntity {

    @Id
    @Generated
    @Column(name = "business_member_id", length = 20, nullable = false)
    String id;  // 아이디

    @Column(name = "business_member_password",length = 20, nullable = false)
    String password; // 비밀번호

    @Column(name = "business_member_name", length = 30, nullable = false)
    String name; // 이름

    @Column(name = "business_member_year", nullable = false)
    Integer year; // 펫주인 생년월일

    @Column(name = "business_member_phone_number", nullable = false)
    String phone_number; // 핸드폰 번호

    @Column(name = "business_member_address", length = 50, nullable = false)
    String address; // 주소

}
