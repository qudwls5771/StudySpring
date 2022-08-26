package com.example.team_pro_ex.com.persistence.member;

import com.example.team_pro_ex.com.Entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//DAO
public interface MemberRepository extends JpaRepository<Member, String> {
    //아이디 찾기?
    @Query(value = "select m from Member m where m.id = :id_1")
    Member findMemberByEmailOrId(String id_1);

    //고객의 데이터 정보는 돈이다!? 라는게 있어서 고객의 ID, 이름, 가입상태(Y=>N으로 변경)을 제외한 모든
    //데이터를 Null값으로 변경
    //날짜 테이블에 있는 가입날짜, 탈퇴날짜 수정은 차후에 하기로 한다.
    @Query(value = "update Member m set m.password = 'null', m.address = 'null'," +
            " m.name = 'null', m.year = 'null', m.phone_number = 'null', m.pet_D = 'null', m.pet_S = 'null', " +
            "m.pet_T = 'null', m.pet_W = 'null', m.join_M = 'N' where m.id = :memberID")
    Member updateDelete(String memberID);

}
