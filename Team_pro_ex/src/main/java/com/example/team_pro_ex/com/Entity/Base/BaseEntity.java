package com.example.team_pro_ex.com.Entity.Base;

import javax.persistence.Column;
import java.util.Date;

public class BaseEntity {
    // columnDefinition(default)설정 어노테이션? = "VARCHAR@(1)/타입 default 값을 'Y'로 설장한다."
    @Column(columnDefinition = "VARCHAR@(1) default 'Y'")
    Date join_D; //--가입날짜

    @Column(nullable = true)
    Date join_O; //--탈퇴날짜
}
