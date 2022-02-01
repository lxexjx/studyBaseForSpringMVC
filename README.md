# study
spring이 올라올때 component와 관련된 어노테이션이 있으면 spring이 객체로 생성해서 container에 등록하고 
Autowired는 연관관계! 그래서 memberController가 memberService를, memberService가 memberRepository를 사용할 수 있

DI의 3가지 방법
1.생성자 주입: 생성자를 통해서 MemberService가 MemberController에 주입됨
2.필드 주입: 생성자 빼고 필드에 @Autowired에 
3.setter 주입: @Autowired 넣어줌
