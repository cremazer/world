package hello.world.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import hello.world.domain.Member;
import hello.world.ropository.MemoryMemberRepository;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @DisplayName("join for membership")
    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("Lucky");

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
        member1.setName("Lucky");

        Member member2 = new Member();
        member2.setName("Lucky");

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
        member1.setName("Lucky");

        Member member2 = new Member();
        member2.setName("Supriya");

        // when
        memberService.join(member1);
        memberService.join(member2);

        // then
        List<Member> findMembers = memberService.findMembers();
        assertThat(findMembers).hasSize(2);
    }

    @DisplayName("Find one member.")
    @Test
    void findOne() {
        // given
        Member member1 = new Member();
        member1.setName("Lucky");

        Member member2 = new Member();
        member2.setName("Supriya");

        // when
        Long member1Id = memberService.join(member1);
        Long member2Id = memberService.join(member2);

        // then
        Member member = memberService.findOne(member2Id).orElseThrow();
        assertThat(member.getId()).isEqualTo(member2Id);
    }
}