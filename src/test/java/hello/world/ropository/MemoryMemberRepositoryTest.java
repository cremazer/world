package hello.world.ropository;

import static org.assertj.core.api.Assertions.assertThat;

import hello.world.domain.Member;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @DisplayName("Save member.")
    @Test
    void save() {
        Member member = new Member();
        member.setName("Lucky");
        repository.save(member);

        Member result = repository.findById(member.getId()).orElseThrow();
        log.info("result = " + (result == member));

        assertThat(result).isEqualTo(member);
    }

    @DisplayName("Find a member by name.")
    @Test
    void findByName() {
        Member member1 = new Member();
        member1.setName("Lucky");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Supriya");
        repository.save(member2);

        Member result = repository.findByName("Lucky").orElseThrow();

        assertThat(result).isEqualTo(member1);
    }

    @DisplayName("Find all members.")
    @Test
    void findAll() {
        Member member1 = new Member();
        member1.setName("Lucky");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Supriya");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result).hasSize(2);
    }
}