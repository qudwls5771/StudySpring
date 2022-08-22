package com.example.demo.controller.board;

/**
* @package : com.example.demo.controller
* @name : BoardController.java
* @date : 2022-08-08 오후 6:24
* @author : Rubisco
* @version : 1.0.0
* @modifyed : 
* @description : 게시판 컨트롤러
**/

import com.example.demo.Entity.account_info.Member;
import com.example.demo.Entity.account_info.board.Board;
import com.example.demo.service.board.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    //BoardService의 getBoardList메서드 실행 > BoardRepository(CrudRepository).findAll()를 통해서 (JPA번역)
    //DB의 데이터 불러오기(테이블전체) (SQL)
    @GetMapping("/getBoardList")
    public String getBoardList(Model model, Board board) {
        System.out.println("-------------------");
        List<Board> boardList = boardService.getBoardList(board);
        model.addAttribute("boardList", boardList);
        return "/board/getBoardList";
    }

    @GetMapping("/insertBoard")
    public String insertBoardView() {
        return "insertBoard";
    }

    @PostMapping("/insertBoard")
    public String insertBoard(Board board) {
        board.setCreateDate(new Date());

        //클라이언트에서 board객체를 받아서 매개변수로 사용
        //[1]BoardService의 inserBoard메서드 실행 >
        //[2]BoardRepository(CrudRepository).save(board)를 통해서 (JPA번역)
        //DB의 저장 (SQL)
        //insertBoard라는 메서드에 board객체 인자값으로 넣기
        boardService.insertBoard(board);
        return "redirect:getBoardList";
    }

    @GetMapping("/getBoard")
    public String getBoard(Board board, Model model) {
        model.addAttribute("board", boardService.getBoard(board));
        return "getBoard";
    }

    @PostMapping ("/updateBoard")
    public String updateBoard(Board board) {
        boardService.updateBoard(board);
        return "redirect:getBoard?seq="+board.getSeq();
    }

    @GetMapping("/updateBoard")
    public String updateBoardView(Board board, Model model) {
        model.addAttribute("board", boardService.getBoard(board));
        return "insertBoard";
    }

    @PostMapping("/deleteBoard")
    public String deleteBoard(Board board) {
        boardService.deleteBoard(board);
        return "redirect:getBoardList";
    }

    @GetMapping("/selectBoard")
    public String selectBoard(Member member, Model model) {
        System.out.println("--------boarde select-----------");
        //board.getId()는 클라이언트에서 가져옴

        //@Service에 board를 인자값으로 넣고 메서드 실행
        model.addAttribute("boardList", boardService.getBoardListByMemberId(member));

        //회원이 작성한 게시글리스트(List<Board>)
        // > HTML에다가 뿌려주면 끝 (Controller에 가면 메서드가 실행되서 다른 결과물을 리턴받기 때문
        // 어느 HTML로 가느냐? = 객체지향은 재활용성이 중요한 요인 중 하나
        // HTML에 중에 재사용 할만한 것을 먼저 찾고, 그 후에 새로 만들기에 대해 고민
        // > getBoardList

        //return 페이지 Or controller mapoing
        return "/board/getBoardList";
    }
}
