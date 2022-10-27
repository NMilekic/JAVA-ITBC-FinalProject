package com.example.zavrsni_projekat.configuration;

import com.example.zavrsni_projekat.filters.JwtRequestFilter;
import com.example.zavrsni_projekat.service.MyClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyClientDetailsService myUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//
//        http.csrf().disable()
//                .authorizeRequests()
//                .mvcMatchers("/api/clients/register").permitAll()
//                .mvcMatchers("/api/clients/search").authenticated()
//                .and().formLogin()
//                .and().httpBasic();
//
//        http.headers().frameOptions().disable();
//    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/h2-console/**");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .mvcMatchers("/api/clients/login").permitAll()
                .mvcMatchers("/api/clients/register").permitAll()
                .mvcMatchers("/api/clients/create").hasRole("CLIENT")
                .mvcMatchers("/api/clients/search").hasRole("CLIENT")
                .mvcMatchers("/api/clients").hasAnyRole("ADMIN")
                .mvcMatchers("/api/clients/{clientId}/reset-password").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user").password("12345").roles("USER")
//                .and()
//                .withUser("admin").password("54321").roles("USER", "ADMIN")
//                .and().passwordEncoder(NoOpPasswordEncoder.getInstance());
//    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
