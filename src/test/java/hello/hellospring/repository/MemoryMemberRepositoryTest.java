package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    //테스트는 의존관계없이 각각 실행돼야됨
    @AfterEach  //테스트가 한번 끝날떄 마다 리포지토리 지워줌
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();   //저장이 잘 되는지 테스트
        member.setName("spring");   //이름 세팅
        //when
        repository.save(member);//repository에 저장하고
        Member result = repository.findById(member.getId()).get();  //optional에서 꺼내서 검증할거임
        //Assertions.assertThat(result).isEqualTo(member);  //member가 result랑 똑같으면 가져온 값이랑 result랑 값이 같으면
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        //given-(prign1,2회원이 가입됨)
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);   //member1저장장

        Member member2 = new Member();      //shift+f6:동시에 이름변경가능
        member2.setName("spring2");
        repository.save(member2);

        //when(findByName이 잘동작하는지:spring1찾기)
        Member result = repository.findByName("spring1").get();

        //then
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        List<Member> result = repository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
    }
}
