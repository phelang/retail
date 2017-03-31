package com.retail.springSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /*@Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }*/

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{

        auth
                .inMemoryAuthentication()
                .withUser("admin")
                .password("DEFAULT^*90programHip->")
                .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                    .anyRequest().access("hasRole('ADMIN')")
                .and()
                .formLogin();

        http.csrf().disable();

        /*******************
         * Note if csrf is not disable then this error will appear
         *********************/
       /* Possibly unhandled rejection: {"data":
            {"timestamp":1490882829330,"status":403,"error":"Forbidden","message":"Could not verify the provided CSRF token because your session was not found.","path":"/category/categories"},
            "status":403,"config":{"method":"POST","transformRequest":[null],"transformResponse":[null],"jsonpCallbackParam":"callback","url":"/category/categories"
                    ,"headers":{"Accept":"application/json, text/plain, **///*"}
          /*  },"statusText":""}*/
    }

}
