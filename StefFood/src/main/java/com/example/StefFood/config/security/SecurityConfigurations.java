package com.example.StefFood.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    @Autowired
    private AutenticacaoService autenticacaoService;

    //configuração de autenticação
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
    }

    //configuração de autorização
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET,"/lojas").permitAll()
                .antMatchers(HttpMethod.GET, "/lojas/*").permitAll()
                .antMatchers(HttpMethod.GET, "/produtos").permitAll()
                .antMatchers(HttpMethod.GET, "/produtos/*").permitAll()
                .antMatchers(HttpMethod.GET, "/usuarios").permitAll()
                .antMatchers(HttpMethod.GET, "/usuarios/*").permitAll()
                .antMatchers(HttpMethod.GET, "/lojas/lojaprodutos/*").permitAll()
                .anyRequest().authenticated()
                .and().formLogin();
    }

        //configuração de recursos estaticos(js css imagens etc)
        @Override
        public void configure(WebSecurity web) throws Exception {
        }
}
