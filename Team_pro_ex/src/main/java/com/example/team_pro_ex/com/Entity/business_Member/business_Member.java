package com.example.team_pro_ex.com.Entity.business_Member;

import com.example.team_pro_ex.com.Entity.Base.member_BaseEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@ToString
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class business_Member extends member_BaseEntity {

    @Id
    @Generated
    @Column(name = "business_member_id", length = 20, nullable = false)
    private String id;  // 아이디

    @Column(name = "business_member_number", length = 20)
    private String business_Number; //사업자 번호

    @Column(name = "business_member_password",length = 20)
    private String password; // 비밀번호

    @Column(name = "business_member_phone_number")
    private String phone_number; // 핸드폰 번호

    @Column(name = "business_member_store_number")
    private String store_number; // 핸드폰 번호

    @Column(name = "business_store_name", length = 30)
    private String store_Name; // 가게이름

    @Column(name = "business_member_address", length = 50)
    private String address; // 주소

    @Column(name = "member_join_M", length = 1, nullable = false)
    @ColumnDefault("'Y'")
    private String join_M; //--가입상태

}