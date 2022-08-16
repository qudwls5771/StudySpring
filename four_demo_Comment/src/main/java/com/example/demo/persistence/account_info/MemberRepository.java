package com.example.demo.persistence.account_info;

import com.example.demo.Entity.account_info.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


//CrudRepository 제네릭 타입을 지정
//CrudRepository를 상속받음
//CrudRepository : Entity 매개체를 create, read, update, delete 기능을 하는 인터 페이스
//extends CrudRepository<Board, Long>의 매개변수 Board(Entity)와 Long(PK기본키의 타입)을 선언
//JPA가 어떤 객체로 어떤 타입으로 찾아야되는지 알아들음


//MemberRepository는 CrudRepository를 상속받아 기능을 온전히 씀
//CrudRepository : DB에 기본적인 SQL문을 통해 소통
// (C= Create / insert into, R = Read(Select),U = Update, D = Delete를 쓴다.)


public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByIdOrEmail(String email);

    //Return 내용선언, Find~변수명에 맞춰서 메소드 생성, 필요한 매개변수
    // m은 별칭이다.
    @Query(value= "select m from Member m where m.email = :email or m.id = :id")
    List<Member> findMemberByEmailorId(String email_1, String id_1);

    //(id는 중복가능한 구조에서)id값을 매개변수로 넣고, 아이디 생성날짜가 가장최신화된 것.
    @Query(value= "select m from Member m where m.id = :id order by m.createDate DESC")
    Member findFirstById(String id);

}
