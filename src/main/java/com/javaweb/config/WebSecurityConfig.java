package com.javaweb.config;

import com.javaweb.security.CustomSuccessHandler;
import com.javaweb.service.impl.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailService();
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
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
                http.csrf().disable()
                .authorizeRequests()

                        // BuildingAPIs
                        .antMatchers(HttpMethod.GET, "/api/buildings/{id}/staffs").hasRole("MANAGER")
                        .antMatchers(HttpMethod.PUT, "/api/buildings/staffs","/api/buildings").hasRole("MANAGER")
                        .antMatchers(HttpMethod.POST, "/api/buildings").hasRole("MANAGER")
                        .antMatchers(HttpMethod.DELETE, "/api/buildings/{ids}").hasRole("MANAGER")

                        // CustomerAPIs
                        .antMatchers(HttpMethod.GET, "/api/customers/{id}/staffs").hasRole("MANAGER")
                        .antMatchers(HttpMethod.PUT, "/api/customers").permitAll()
                        .antMatchers(HttpMethod.PUT, "/api/customers/staffs").hasRole("MANAGER")
                        .antMatchers("/api/customers/transactions").hasAnyRole("MANAGER", "STAFF")
                        .antMatchers(HttpMethod.POST, "/api/customers/{ids}", "/api/customers").hasRole("MANAGER")

                        // UserAPIs
                        .antMatchers(HttpMethod.PUT, "/api/user/profile/{username}", "/api/user/change-password/{id}", "/api/user/{id}").hasAnyRole("MANAGER", "STAFF")
                        .antMatchers(HttpMethod.PUT, "/api/user/password/{id}/reset").hasRole("MANAGER")
                        .antMatchers(HttpMethod.POST, "/api/user").hasRole("MANAGER")
                        .antMatchers(HttpMethod.DELETE, "/api/user").hasRole("MANAGER")

                        // Building Pages
                        .antMatchers("/admin/building-edit-{id}","/admin/building-list").hasAnyRole("MANAGER","STAFF")
                        .antMatchers( "/admin/building-edit").hasAnyRole("MANAGER")

                        // Customer Pages
                        .antMatchers("/admin/customer-edit-{id}", "/admin/customer-list").hasAnyRole("MANAGER", "STAFF")
                        .antMatchers("/admin/customer-edit").hasAnyRole("MANAGER")

                        // User Pages
                        .antMatchers("/admin/home", "/admin/profile-password", "/admin/profile-{username}", "/admin/user-list").hasAnyRole("MANAGER", "STAFF")
                        .antMatchers("/admin/user-edit", "/admin/user-edit-{id}").hasAnyRole("MANAGER")

                        // Home page
                        .antMatchers("/login","/logout", "/resource/**", "/trang-chu", "/gioi-thieu", "/san-pham", "/tin-tuc", "/lien-he", "/access-denied").permitAll()


                .and()
                .formLogin().loginPage("/login").usernameParameter("j_username").passwordParameter("j_password").permitAll()
                .loginProcessingUrl("/j_spring_security_check")
                .successHandler(myAuthenticationSuccessHandler())
                .failureUrl("/login?incorrectAccount").and()
                .logout().logoutUrl("/logout").deleteCookies("JSESSIONID")
                .and().exceptionHandling().accessDeniedPage("/access-denied").and()
                .sessionManagement().maximumSessions(1).expiredUrl("/login?sessionTimeout");
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new CustomSuccessHandler();
    }
}
