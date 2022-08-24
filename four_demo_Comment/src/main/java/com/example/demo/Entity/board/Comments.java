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

    @Transient  //쓰는 이유->seq과 board_seq가 충돌
    private Long board_seq;
    
    //Fetch조인을 사용!
    //코멘트를 검색할 때 = join을 해서 board에 있는 것을 가지고 올 수 있도록 관계성을 명시!
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "board_seq", referencedColumnName = "seq")
    private Board board;


}
