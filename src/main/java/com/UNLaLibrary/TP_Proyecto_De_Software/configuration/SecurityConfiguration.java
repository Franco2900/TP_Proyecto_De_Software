package com.UNLaLibrary.TP_Proyecto_De_Software.configuration;

import com.UNLaLibrary.TP_Proyecto_De_Software.services.implementation.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    @Autowired
    private UserService userService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/img/**").permitAll()
                .antMatchers("/", "/listadoDocumentos/**",
                    "/listadoDepartamentos/**", "/listadoMaterias/**", "/registro").permitAll()
                .antMatchers("/agregarDocumento/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            .and()
                .formLogin().loginPage("/login").loginProcessingUrl("/loginprocess")
                .usernameParameter("username").passwordParameter("password")
                .defaultSuccessUrl("/loginsuccess").permitAll()
            .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/logout").permitAll()
            .and()
                .headers().frameOptions().sameOrigin()
            .and().csrf().disable();
    }
}
