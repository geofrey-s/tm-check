package edu.mum.tmcheck.configs;

import edu.mum.tmcheck.serviceimp.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;

      @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests().antMatchers("/wallPage").hasAnyRole("ADMIN", "USER")
//                .and()
//                .authorizeRequests().antMatchers("/login", "/resource/**").permitAll()
//                .and()
//                .formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password").permitAll()
//                .loginProcessingUrl("/doLogin")
//                .successForwardUrl("/postLogin")
//                .failureUrl("/loginFailed")
//                .and()
//                .logout().logoutUrl("/doLogout").logoutSuccessUrl("/logout").permitAll()
//                .and()
//                .csrf().disable();

        http.authorizeRequests()
                .anyRequest()
                .hasAnyRole( "admin", "faculty", "student")
                .and().formLogin()
                .and().logout()
                .permitAll()
                .logoutSuccessUrl("/login")
                .and()
                .csrf()
                .disable();
    }
}
