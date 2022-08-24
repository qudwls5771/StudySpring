package com.example.demo.persistence.board;

import com.example.demo.Entity.board.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//Entity, 기본키(PK) 타입
public interface CommentsRepository extends JpaRepository<Comments, Long> {

    @Query(value = "SELECT c FROM Comments c JOIN fetch c.board WHERE c.board.seq = : input_board_seq")
    List<Comments> findCommentsByBoard_seq(Long input_board_seq);


}
