package com.example.Eticaret;
import com.example.Eticaret.Customers.CustomerDetailService;
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

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomerDetailService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
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
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()

                .antMatchers("/list_users").hasAnyAuthority("Admin")
                .antMatchers("/admin").hasAnyAuthority("Admin")

                .antMatchers("/shopping/new").hasAnyAuthority("User","Customer","Admin")
                .antMatchers("/users/edit/").hasAnyAuthority("Admin")
                .antMatchers("/users/save").hasAnyAuthority("Admin","Customer")
                .antMatchers("/categories").hasAnyAuthority("Admin","Customer")
                .antMatchers("/category/new").hasAnyAuthority("Admin","Customer")
                .antMatchers("/category/save").hasAnyAuthority("Admin","Customer")
                .antMatchers("/category/edit/*").hasAnyAuthority("Admin","Customer")
                .antMatchers("/category/delete/*").hasAnyAuthority("Admin","Customer")

                .antMatchers("/products").hasAnyAuthority("Admin","Customer")
                .antMatchers("/products/new").hasAnyAuthority("Admin","Customer")
                .antMatchers("/products/save").hasAnyAuthority("Admin","Customer")
                .antMatchers("/products/edit/*").hasAnyAuthority("Admin","Customer")
                .antMatchers("/products/delete/*").hasAnyAuthority("Admin","Customer")
                .antMatchers("brand/new").hasAnyAuthority("Admin","Customer")
                .antMatchers("brand/save").hasAnyAuthority("Admin")
                .antMatchers("/brand/delete/*").hasAnyAuthority("Admin")


                .antMatchers("/list_users").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .usernameParameter("email")
                .defaultSuccessUrl("/list_users")
                .permitAll()
                .and()
                .logout().logoutSuccessUrl("/").permitAll();

    }


}

