package hello.world.controller;

import hello.world.domain.Member;
import hello.world.model.member.MemberRequestForm;
import hello.world.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/member/new")
    public String createForm() {
        return "member/createMemberForm";
    }

    @PostMapping("/member/new")
    public String createNewMember(MemberRequestForm form) {
        Member member = new Member();
        member.setName(createForm());

        memberService.join(member);
        return "redirect:/";
    }
}
