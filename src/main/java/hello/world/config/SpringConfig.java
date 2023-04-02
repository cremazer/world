package hello.world.config;


import hello.world.ropository.JpaMemberRepository;
import hello.world.ropository.MemberRepository;
import hello.world.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final EntityManager entityManager;

    @Autowired
    public SpringConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Bean
    public MemberRepository memberRepository() {
        return new JpaMemberRepository(entityManager);
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
}
