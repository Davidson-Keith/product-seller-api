package au.com.keithdavidson.productsellerapi.security;

import au.com.keithdavidson.productsellerapi.model.Role;
import au.com.keithdavidson.productsellerapi.security.jwt.JwtAthorizationFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


// With deprecated WebSecurityConfigurerAdapter:
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable(); // Cross Site Request Forgery disabled because we are using JWT, which doesn't need it.
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // No session will be created or used by Spring Security.
        http.authorizeRequests()
                .antMatchers("/api/authentication/**").permitAll() // "**" = wild card.
                .anyRequest().authenticated();

        // Add authorization filter before authentication filter??? Shouldn't it be the other way around? Don't we have
        // to know who it is before giving them privileges?
        http.addFilterBefore(jwtAthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    // Why a Bean and not a component? Because it is only related to the security scope, thus it doesn't need to be a
    // component?? I have no idea what he is trying to say here.
    @Bean
    public JwtAthorizationFilter jwtAthorizationFilter(){
        return new JwtAthorizationFilter();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                WebMvcConfigurer.super.addCorsMappings(registry);
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("*"); // allow everything for testing for now.
            }
        };
    }
}


// Using latest version of Spring Security, which deprecates WebSecurityConfigurerAdapter, as per:
// https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//    @Autowired
//    private CustomUserDetailsService customUserDetailsService;
//
//    @Bean
//    public SecurityFilterChain filterChain(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
//        return null; // TODO!!!
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//
//// Solution provided by:
//// https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        http.authorizeHttpRequests((authz) -> authz
////                        .anyRequest()
////                        .authenticated())
////                .httpBasic(Customizer.withDefaults());
////        return http.build();
////    }
//}


