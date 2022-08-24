package com.example.demo.Entity.board;

import com.example.demo.Entity.account_info.Member;
import com.example.demo.Entity.base.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;


@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Comments extends BaseTimeEntity {

    @Id
    private Long seq;

    private  String comments_content;

    @Transient
    private String board_title;
    
    //Fetch조인을 사용!
    //코멘트를 검색할 때 = join을 해서 board에 있는 것을 가지고 올 수 있도록 관계성을 명시!
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_title" ,referencedColumnName = "title")
    private Board board;

    public Comments(String comments_content, Board board) {
        this.comments_content = comments_content;
        this.board = board;
    }


}
