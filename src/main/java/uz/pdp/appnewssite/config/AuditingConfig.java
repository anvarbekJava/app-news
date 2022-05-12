package uz.pdp.appnewssite.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import uz.pdp.appnewssite.entity.Users;


@Configuration
@EnableJpaAuditing
public class AuditingConfig {
    @Bean
    AuditorAware<Users> auditorAware(){
        return new SpringSecurityAuditAwareImpl();
    }
}
