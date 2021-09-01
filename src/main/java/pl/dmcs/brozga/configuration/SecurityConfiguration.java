package pl.dmcs.brozga.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Resource(name = "myAppUserDetailsService")
    private UserDetailsService userDetailsService;


    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN", "USER");
        auth.inMemoryAuthentication().withUser("ja").password("{noop}ja").roles("USER");
        auth.inMemoryAuthentication().withUser("student").password("{noop}student").roles("STUDENT");
        auth.inMemoryAuthentication().withUser("patient").password("{noop}patient").roles("PATIENT");
        auth.inMemoryAuthentication().withUser("doctor").password("{noop}doctor").roles("DOCTOR");


        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       

        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);

        http.authorizeRequests()
                .antMatchers("/appUsers*").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/appUserRole*").access("hasRole('ROLE_ADMIN')")
                // "hasRole('ADMIN')" is the same as "hasRole('ROLE_ADMIN')" and "hasAuthority('ROLE_ADMIN')"
                .antMatchers("/exampleOne*").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/exampleTwo*").access("hasRole('ROLE_STUDENT')")
                .antMatchers("/address*").access("hasRole('ADMIN') or hasRole('USER')")
                .antMatchers("/exampleThree*").permitAll()
                .antMatchers("/visitHours*").access("hasRole('ROLE_ADMIN')or hasRole('ROLE_DOCTOR')")
                /*.and().formLogin().permitAll(); // with default login page*/
                .and().formLogin().loginPage("/login").permitAll() // with custom login page
                .usernameParameter("login").passwordParameter("password")
                .failureForwardUrl("/login.html?error")
                .and().logout().logoutSuccessUrl("/login.html?logout")
                .and().exceptionHandling().accessDeniedPage("/accessDenied");
    }
}
