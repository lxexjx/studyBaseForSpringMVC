package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
public class JpaMemberRepository implements MemberRepository {

    //jap를 사용하려면 EntityManager가 필요.
    private final EntityManager em; //springboot가 자동으로 EntityManager 생성
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    public Member save(Member member) {
        em.persist(member);
        return member;  //이렇게 하면 jap가 insert쿼리 만들어서 db에 집어넣고 setid까지 해줘
   }
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class).setParameter("name", name).getResultList();
        return result.stream().findAny();   //하나만 찾으니까
    }
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();  //객체지향 쿼리:객체를 대상으로 쿼리르 날리면 sql로 변역됨
    }
}