package com.example.lecture_spring_2_crudproject.controller.board;

import com.example.lecture_spring_2_crudproject.entity.account.Member;
import com.example.lecture_spring_2_crudproject.entity.board.Board;
import com.example.lecture_spring_2_crudproject.entity.board.Comments;
import com.example.lecture_spring_2_crudproject.entity.customDto.CustomDtoSortPages;
import com.example.lecture_spring_2_crudproject.entity.data.FileUploadEntity;
import com.example.lecture_spring_2_crudproject.service.board.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.google.common.io.ByteStreams.toByteArray;

/**
 * @description : 게시판 컨트롤러
 **/

@Controller
@Slf4j
@RequestMapping(path = "/board")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    protected BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/insertComments")
    public String insertComments(Board board, Model model) {
        System.out.println(board.getTitle());
        model.addAttribute("board", board);
        return "/board/insertComments";
    }

    @PostMapping("/insertComments")
    public String insertComments(@RequestParam("board_title")String boardTitle, Comments comments, Model model) {

        System.out.println("------inertComments---------");
        System.out.println(comments.getBoard_title());
        System.out.println(comments.getComments_content());
        boardService.insertComment(comments);
        return "redirect:/board/getBoardList";
    }

    //board Seq전달하면 전체 comments를 불러오는 controller method
    @GetMapping("/getCommentsList")
    public String getCommentsList(Comments comments, Model model) {
        System.out.println("-------getCommentsList-------");
        System.out.println(comments.getBoard_title());
        List<Comments> checkCommentsList = boardService.getAllComments(comments);

        model.addAttribute("commentsList", checkCommentsList);
        return "/board/getCommentsList";
    }

    @GetMapping("/getBoardList")
    public String getBoardList(Model model, Board board) {
        System.out.println("-------------------");
        List<Board> boardList = boardService.getBoardList(board);
        model.addAttribute("boardList", boardList);
        return "/board/getBoardList";
    }

    @GetMapping("/insertBoard")
    public String insertBoard() {
        System.out.println("------insertBoard_get-------------");
        return "/board/insertBoard";
    }

    @PostMapping("/insertBoard")
    public String insertBoard(Board board) {
        System.out.println("--------insertBoard_post-----------");
        System.out.println(board.getCreateDate());
        System.out.println(board.getUpdateDate());
        board.setCreateDate(new Date());
        board.setUpdateDate(new Date());
        System.out.println(board.getCreateDate());
        System.out.println(board.getUpdateDate());
        boardService.insertBoard(board);
        return "redirect:/board/getBoardList";
    }

    @GetMapping("/getBoard")
    public String getBoard(Board board, Model model) {
        System.out.println("-------------------");
        System.out.println(board.getSeq());
        model.addAttribute("board", boardService.getBoard(board));
        model.addAttribute("boardPrv", boardService.getPagesSortIndex(board));
        return "/board/getBoard";
    }

    @PostMapping ("/updateBoard")
    public String updateBoard(Board board) {
        System.out.println("----------updateBoard---------");
        System.out.println(board.getContent());
        System.out.println(board.getSeq());
        boardService.updateBoard(board);
        return "redirect:/board/getBoard?seq="+board.getSeq();
    }

    @GetMapping("/updateBoard")
    public String updateBoardView(Board board, Model model) {
        System.out.println("-------------------");
        model.addAttribute("board", boardService.getBoard(board));
        return "/board/insertBoard";
    }

    @GetMapping("/deleteBoard")
    public String deleteBoard(Board board) {
        System.out.println("--------boarde delete-----------");
        System.out.println(board.getSeq());
        boardService.deleteBoard(board);
        return "redirect:/board/getBoardList";
    }


    @GetMapping("/viewUserWriteBoard")
    public String viewUserWriteBoard(Member member, Model model) {
        System.out.println("-------view-------");
        System.out.println(member.getId());
        model.addAttribute("boardList",
                boardService.getBoardListAllBoardListByMemberId(member));
        return "/board/getBoardList";
    }

    @GetMapping("/getAllUserBoardList")
    public String AllUsersBoard(Model model) {

//        List<Board> board = null;
//        List<Member> member = null;

//        for(List<Object> result : boardService.getBoardAndMemberUsersBoard()) {
//            board = (List<Board>) result.get(0);
//            member = (List<Member>) result.get(1);
//        }

        System.out.println(boardService.getBoardAndMemberUsersBoard().size());

//            board = (List<Board>) boardService.getBoardAndMemberUsersBoard().get(0);
//            member = (List<Member>) boardService.getBoardAndMemberUsersBoard().get(1);

        return "index";
    }

    @GetMapping("sortTest")
    public String sortTest(Board board, Model model) {

        CustomDtoSortPages customDtoSortPages = boardService.getPagesSortIndex(board);
        System.out.println(customDtoSortPages.getNEXT_SUBJECT());
        System.out.println(customDtoSortPages.getNEXTID());
        System.out.println(customDtoSortPages.getPREV_SUBJECT());
        System.out.println(customDtoSortPages.getPREVID());

//        model.addAttribute("sortBoard", customDtoSortPages);
        return "/board/getBoardList";
    }
    //*client에서 server로 이미지파일 전송(데이터 전송)
    //html form태그에 upload버튼으로 이미지 데이터 전송(MultipartFile) > Entity기준으로 데이터 정보를 전달

    //--------------------------------------------------------------

    //-server는 이미지파일을 특정 폴더에 저장
    //장점 : 서버에 원본 이미지 파일을 저장하므로 필요할 때 서버에서 바로 전달 받을 수 있음 = db에 부담감이 줄어듬
    //단점 : 다수의 서버에 이미지 파일을 저장할 경우, 동일한 이미지 데이터 처리에 대한 이슈 발생 = UUID를 통해 이미지 이름을 구분

    //--------------------------------------------------------------

    //-server는 이미지 파일을 byte코드로 db에 저장
    //장점 : 이미지 데이터를 한 곳에 저장하고 관리
    //단점 : DB에 많은 부하가 걸림, 데이터 저장 포맷의 한계.(oracle 기준으로 Blob 단위로 저장할 때 4gb한계에 이슈)

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("upladfile")MultipartFile[] upladfile) throws IOException {
        //multipartfile을 클라이언트에서 서버로 RequestParam데이터 받아옴 name=uploadfile
        System.out.println("test");
        //@Slf4j Lombook라이브러리로 log데이터 찍음
        //info /r
        //info / error / debug 단위가 있고 단위마다 필터링하여 정보를 수집하고 관리 가능
        log.info("img load session");
        //multipartfile데이터를 수집하여 entity FileuploadEntity에 데이터 저장
        List<FileUploadEntity> list = new ArrayList<>();
        for(MultipartFile file : upladfile){
            //MultipartFile file이 있을 때까지 작업 진행
            if(!file.isEmpty()){
                //MultipartFiled의 정보를 dto에 저장
                FileUploadEntity dto = new FileUploadEntity(null,
                        UUID.randomUUID().toString(),
                        file.getContentType(),
                        file.getName(),
                        file.getOriginalFilename()
                        );
                list.add(dto);
                File newFileName = new File(dto.getUuid()+"_"+dto.getName()+".PNG");
                //file을 서버에 저장하는 스트림행위는 서버가 성공할지 여부를 체크하므로 exception처리 필요
                //메서드에 throws IOException 처리 = try catch처리 필요
                file.transferTo(newFileName);
            }
        }
        return "/board/getBoardList";
    }

    //-----------------------------------------------------------------

    //server에서 client로 이미지 전송
    //supringboot에서 URL주소를 통해 이미지를 받음. InputStream을 통해 파일을 http프로토콜에 전달하여 클라이언트에게 전송
    @GetMapping("/vewImage/{imgname}")
    public ResponseEntity<byte[]> viewImage(@PathVariable("imgname")String input_imgName) throws IOException {

        //ResponseEntity : http프로토콜을 통해서 byte데이터를 전달하는 객체, byte(소문자=기본타입) []배열
        //@PathVariable : URL주소의 값을 받아옴
        String path ="/Users/jeongseojin/studyspring/lecture_spring_2_crudProject/src/main/resources/upload"+input_imgName;
        //데이터(이미지)를 전손하기 위한 객체로써 java에서는 항상 데이터를 스트림타입으로 전달
        InputStream inputStream = new FileInputStream(path);
        //byte배열로 변환
        byte[] imgByteArr = toByteArray(inputStream);
        inputStream.close();
        //ResponseEntity를 통해 http프로토콜로 클라이언트에게 데이터 전송
    return new ResponseEntity<byte[]>(imgByteArr, HttpStatus.OK);
    }
}


