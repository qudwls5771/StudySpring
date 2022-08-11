package com.example.demo.persistence.account_info;

import com.example.demo.domain.account_info.Member;
import org.springframework.data.repository.CrudRepository;


//CrudRepository 제네릭 타입을 지정
//CrudRepository를 상속받음
//CrudRepository : Entity 매개체를 create, read, update, delete 기능을 하는 인터 페이스
//extends CrudRepository<Board, Long>의 매개변수 Board(Entity)와 Long(PK기본키의 타입)을 선언
//JPA가 어떤 객체로 어떤 타입으로 찾아야되는지 알아들음


//MemberRepository는 CrudRepository를 상속받아 기능을 온전히 씀
//CrudRepository : DB에 기본적인 SQL문을 통해 소통
// (C= Create / insert into, R = Read(Select),U = Update, D = Delete를 쓴다.)
public interface MemberRepository extends CrudRepository<Member, Long> {

}
