package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;
//회원 객체를 저장하는 repository 저장소
public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); //optionaml:null반화될때 optional감싸서 반환
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
