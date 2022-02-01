package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import jdk.jfr.TransitionTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Optional;
//Service: 리포지터리와 도메인 활용해서 비지니스 로직을 작성
//@Service spring이 올라올때 spring이 MemberService를 등록해줘
@Transitional   //jpa사용시 @Transitional필수
public class MemberService {
    private final MemberRepository memberRepository;

    //@Autowired  //controller와 service를 연결
    /*의존관계 주입: MemberService가 생성될 때 생성자를 호출하는데
    Autowired가 있으면 memberRepository가 필요하니까 spring container에 있는 Memory memberRepository를넣어줘
    MemberService객체를 가져다가 등록시켜줘
    * */
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);  //save호출
        return member.getId();  //id반환
        //AOP가 필요한 상황: time을 한곳에 모아두고 원하는 곳에 적용
//        long start = System.currentTimeMillis();
//        try {
//            validateDuplicateMember(member); //중복 회원 검증
//            memberRepository.save(member);
//            return member.getId();
//        } finally {
//            long finish = System.currentTimeMillis();
//            long timeMs = finish - start;
//            System.out.println("join " + timeMs + "ms");
//        }
    }
    //중복 회원 검증
    private void validateDuplicateMember(Member member) {   //ctrl+t:extract메서드
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {   //값이 있으면
                    throw new IllegalStateException("이미 존재하는 회원입니다.");  //이 로직 동작
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
