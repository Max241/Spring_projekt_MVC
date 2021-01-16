package pl.dmcs.brozga.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN","USER");
        auth.inMemoryAuthentication().withUser("ja").password("{noop}ja").roles("USER");
        auth.inMemoryAuthentication().withUser("student").password("{noop}student").roles("STUDENT");

    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/appUsers*").permitAll()
                // "hasRole('ADMIN')" is the same as "hasRole('ROLE_ADMIN')" and "hasAuthority('ROLE_ADMIN')"
                .antMatchers("/exampleOne*").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/exampleTwo*").access("hasRole('ROLE_STUDENT')")
                .antMatchers("/exampleThree*").permitAll()
                .and().formLogin().permitAll(); // with default login page
                /*.and().formLogin().loginPage("/login").permitAll() // with custom login page
                .usernameParameter("login").passwordParameter("password")
                .failureForwardUrl("/login.html?error")
                .and().logout().logoutSuccessUrl("/login.html?logout")
                .and().exceptionHandling().accessDeniedPage("/accessDenied");*/
    }
}
