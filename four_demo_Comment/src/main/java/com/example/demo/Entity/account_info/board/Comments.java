package com.example.demo.Entity.account_info.board;


import com.example.demo.Entity.account_info.Member;
import com.example.demo.Entity.account_info.base.BaseTimeEntity;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Comments extends BaseTimeEntity{

    @Id
    private Long seq;

    private  String Comments;

    @Transient
    private Long board_seq;


    //Fetch조인을 사용!
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "board_seq", referencedColumnName = "seq")
    private Board board;


}
