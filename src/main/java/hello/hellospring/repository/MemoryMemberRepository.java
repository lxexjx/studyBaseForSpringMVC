package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;
//MemberRepository (repository)의 구현체!! data 저장
//@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long,Member> store=new HashMap<>();  //저장하기 위한 Map
    private static long sequence=0L;    //0,1,2키 값 생성해줌

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);  //store(map)에 저장
        return member;  //저장 결과 반환
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));  //store에서 꺼내,  결과과 null이면 Optional.ofNullable로 감싸거 반환
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()              //store에서 돌려
                .filter(member -> member.getName().equals(name))    //name이 파라미터로 들어온 name이랑 같은 지 확인해서 같으면 필터링
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
