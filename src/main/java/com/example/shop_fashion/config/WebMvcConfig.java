package com.example.shop_fashion.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class WebMvcConfig{
    @Autowired
    public UserDetailsService userDetailService;
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((request) -> request
                        .requestMatchers("/**","/index","/register","/static/**","/about","/blog","/blog_details","/checkout","/contact"
                                ,"/shop","/shop_details","/shopping_cart","/addtocart","/getsession","/changequantitycart","/deletecart","/remove_cart"
                                ,"/get-place","/logout","/*.jpg","/*.png","/save","/admin/**","/manager").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/logout").hasAnyRole("ADMIN","USER")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form.loginPage("/login")
//                        .defaultSuccessUrl("/index",true).permitAll()
                                .defaultSuccessUrl("/",true)
                                .successHandler(((request, response, authentication) -> {
                                    for (GrantedAuthority authority: authentication.getAuthorities()){
                                        if (authority.getAuthority().equals("ADMIN")){
                                            response.sendRedirect("/admin/manager");
                                        }else if (authority.getAuthority().equals("USER")){
                                            response.sendRedirect("/index");
                                        }
                                    }
                                }))
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                );
        return http.build();
    }
    @Autowired
    public void configGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }
}