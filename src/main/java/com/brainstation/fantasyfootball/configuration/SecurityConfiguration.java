package com.brainstation.fantasyfootball.configuration;

import com.brainstation.fantasyfootball.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@EnableWebSecurity
@Configuration
public class SecurityConfiguration{
    public static final String[] PUBLIC_URLS =
            {
                    "/",
                    "/user/**",
                    "/css/**",
                    "/js/**",
                    "/image/**",
                    "/common/**",
                    "/contact/**",
                    "/news/**",
                    "/players",
                    "/players/**",
                    "/common/round_match",
                    "/common/match",
            };
    public static final String[] ADMIN_URLS =
            {
                    "/admin/**",
                    "/country/**",
                    "/match/**",
                    "/getallplayer",
                    "/getallplayer/**",
                    "/add_player",
                    "/points/**",
                    "/round/**",
                    "/tournament/**"
            };
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(PUBLIC_URLS).permitAll()
                .antMatchers(ADMIN_URLS).hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin(
                        form -> form
                                .loginPage("/user/loginForm")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/user/homepage",true)
                                .failureUrl("/user/error_login")
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .logoutSuccessUrl("/")
                                .deleteCookies("JSESSIONID")
                                .permitAll()

                );
        return http.build();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
        return authConfiguration.getAuthenticationManager();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
