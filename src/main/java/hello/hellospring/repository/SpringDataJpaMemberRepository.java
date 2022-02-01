package main.java.hello.hellospring.repository;
import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
//인터페이스가 인터페이스 받을때는 extends
public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>,MemberRepository {


    @Override
    Optional<Member> findByName(String name);
}
/*
/