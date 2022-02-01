package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
//컨트롤러가 서비스를 통해서 회원가입을 하고 조회를 할 수 있어야됨(컨트롤러가 서비스를 의존한다!)


@Controller     //spring컨테이너가 MemberController객체를 생성해서 스프링에 넣어놓고 관리해
public class MemberController {
    private final MemberService memberService;

    //spring컨테이너가 뜰때 생성자를 호출하는데
    @Autowired   //sprig의 memberService가져다가 연결시켜줘
     /*의존관계 주입: MemberController가 생성될 때  spring Bean에 등록돼 있는
    MemberService객체를 가져다가 등록시켜줘
    * */
   public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return  "members/createMemberForm";
    }

    @PostMapping(value = "/members/new")
    public String create(MemberForm form) { //memberForm.java에 setname을 통해서 name이 들어가
        Member member = new Member();
        member.setName(form.getName()); //getName으로 꺼내

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members=memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";

    }
}
