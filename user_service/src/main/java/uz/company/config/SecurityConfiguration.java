package uz.company.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import uz.company.config.filter.AuthenticationJwtTokenFilter;

import javax.servlet.Filter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

private final AuthenticationJwtTokenFilter authenticationJwtTokenFilter;

//    @Autowired
//    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers().defaultsDisabled().cacheControl();

        http   .cors().and().csrf().disable()
//        http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers("/favicon.ico",
                        "//*.png",
                        "//*.gif",
                        "//*.svg",
                        "//*.jpg",
                        "//*.html",
                        "//*.css",
                        "//*.js",
                         "/swagger-ui/index.html",
                          "/swagger-resources/",
                        "/v2/",
                        "/csrf",
                        "/webjars/"
                )
                .permitAll()
                .antMatchers("/auth/*").permitAll()
                .anyRequest().authenticated();
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    private Filter authenticationJwtTokenFilter() {
        return authenticationJwtTokenFilter;
    }


    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}