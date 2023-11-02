package com.example.bootgsm04.repository;

import com.example.bootgsm04.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

// save(Member) --> insert
@Repository   //                           CrudRepository
public interface MemberRepository extends JpaRepository<Member, Long> {
    public Optional<Member> findByUsername(String username);
    // select * from member where Username=username
    //@Override
    //@Query("select m from Member m order by m.id desc")
    //public List<Member> findAll();
    // 1. 쿼리메서드 -> 메서드를 만드는 규칙(공부) -> SQL자동생성
    //@Transactional
    public void deleteByUsername(String username);

    // 2. 사용자 정의 JPQL -> SQL자동생성(X) -> SQL을 만드는 방법
    @Modifying
    //@Query("delete from Member m where m.memEmail=:memEmail") // 1.Entity 기준
    // 2. table을 기준
    @Query(value = "delete from member where mem_email=:memEmail",nativeQuery = true)
    public void deleteMemberByEmail(String memEmail);


    // findByMemEmail(String memEmail);-->select * from member where memEmail=?
}
// MyBatis(MemberMapper interface<--->SQL(XML))
//                      |implements
//              SqlSessionFactory
//-------------------------------------------------------------------------------
//                  JpaRepository : CRUD에 관련된 메서드를 제공
//                        |extends
// Spring Data JPA(MemberRepository interface<--자동생성(Hibernate:ORM)--->SQL(X))
//                        |implements
//                 EntityManagerFactory