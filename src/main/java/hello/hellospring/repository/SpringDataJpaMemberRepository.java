package main.java.hello.hellospring.repository;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>,MemberRepository {
    Optional<Member> findByName(String name);
}