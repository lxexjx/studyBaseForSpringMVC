package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;


    @BeforeEach
    /*MemberService에서 만든 new MemoryMemberRepository 테스테에서 만든 new MemoryMemberRepository가 서로 다른 인스턴스인데
     *같은 인스턴스를 쓰도록 외부에서 동작하기 전에 넣어줘->다른 객체가 아닌 같은 객체로 쓰도록
     * */
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();    //같은 MemoryMemberRepository가 사용됨
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test   //join테스트
    public void 회원가입() throws Exception {
        //Given 주어졌을때 이걸 기반으로 해
        Member member = new Member();
        member.setName("hello");
        //When 이걸 실행했을때 이걸 검증해
        Long saveId = memberService.join(member);   //join을 검증해야됨
        //Then 이게 나와야됨
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //Given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");
        //When
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,() -> memberService.join(member2));//IllegalStateException예외가 발생해야 한다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers(){

    }
    @Test
    void findOne(){

    }
}
