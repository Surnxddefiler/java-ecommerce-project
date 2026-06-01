package ecommerce.ecommerce_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    private final JwtFilter jwtFilter;

    public SecurityConfiguration(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    //encoder password
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable).
                authorizeHttpRequests(auth-> auth.//authorize config
                requestMatchers("/auth/**", "/products").permitAll(). // product and auth requests are free without jwt
                anyRequest().authenticated())//other endpoints
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))//allowing spring to know that we are using jwt
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); //adding jwt filter before UsernamePasswordAuthenticationFilter
        return http.build();
    }
}
