package hello.world.config;


import hello.world.ropository.JdbcMemberRepository;
import hello.world.ropository.MemberRepository;
import hello.world.service.MemberService;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberRepository memberRepository() {
        return new JdbcMemberRepository(dataSource);
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
}
