package com.example.team_pro_ex.com.Entity.Base;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
@MappedSuperclass
public class member_BaseEntity {

    @Temporal(TemporalType.DATE)
    @CreatedDate
    private Date join_D; //--가입날짜

    //08.24 생각해야할 부분 : 회원 탈퇴를 어떻게 할 지 생각을 해보자
    @CreatedDate
    private Date join_O; //--탈퇴날짜
}
