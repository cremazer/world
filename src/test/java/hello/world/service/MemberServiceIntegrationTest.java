package hello.world.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import hello.world.domain.Member;
import hello.world.ropository.MemberRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @DisplayName("join for membership")
    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("Luke");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).orElseThrow();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @DisplayName("An exception occurs when registering a member with a duplicate name.")
    @Test
    void exceptionOccursWhenRegisteringMemberWithDuplicateName() {
        // given
        Member member1 = new Member();
        member1.setName("Luke");

        Member member2 = new Member();
        member2.setName("Luke");

        // when
        memberService.join(member1);
        IllegalStateException illegalStateException = assertThrows(IllegalStateException.class,
            () -> memberService.join(member2));

        // then
        assertThat(illegalStateException.getMessage()).isEqualTo("Already a registered member.");
    }

    @DisplayName("Find all members.")
    @Test
    void findMembers() {
        // given
        Member member1 = new Member();
        member1.setName("Luke");

        Member member2 = new Member();
        member2.setName("Nell");

        // when
        memberService.join(member1);
        memberService.join(member2);

        // then
        List<Member> findMembers = memberService.findMembers();
        assertThat(findMembers).isNotEmpty();
    }

    @DisplayName("Find one member.")
    @Test
    void findOne() {
        // given
        Member member = new Member();
        member.setName("Luke");

        // when
        Long memberId = memberService.join(member);

        // then
        Member foundMember = memberService.findOne(memberId).orElseThrow();
        assertThat(foundMember.getId()).isEqualTo(memberId);
    }
}