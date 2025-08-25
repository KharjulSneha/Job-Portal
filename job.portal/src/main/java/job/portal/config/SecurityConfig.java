package job.portal.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.query.parser.Part;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        HttpSecurity logout =
                http
                        .csrf(csrf -> csrf.disable())
                        .authorizeHttpRequests(authRequests -> authRequests
                                .requestMatchers("/api/users/register", "/api/users/login").permitAll()
                                .requestMatchers("/api/users/**").permitAll()
                                        .requestMatchers("/api/role/**").permitAll()
                                .requestMatchers("/admin/**").hasRole("Admin")
//                                .requestMatchers("/employer/**").hasRole("Employer")
                                .requestMatchers("api/employers/**").permitAll()
//                                .requestMatchers("/jobseeker/**").hasRole("Job Seeker")
                                        .requestMatchers("/jobseeker/**").permitAll()
                                .anyRequest().authenticated()
                        )
                        .formLogin(withDefaults())
                        .logout(withDefaults());


        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var userDetailsService = new InMemoryUserDetailsManager();

        var admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("adminPass")
                .roles("Admin")
                .build();

        userDetailsService.createUser(admin);

        return userDetailsService;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}