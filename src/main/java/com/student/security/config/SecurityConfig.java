package com.student.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import com.student.security.AllUserDetailsService;
import com.student.security.JwtAuthenticationEntryPoint;
import com.student.security.JwtAuthenticationFilter;
import com.student.utils.CookieHelper;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	String tokenKey = "student-app";
	
	@Autowired
	AllUserDetailsService allUserDetailsService;
	
	@Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;
	
	@Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
	return new JwtAuthenticationFilter();
	}
	
	@Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
	authenticationManagerBuilder.userDetailsService(allUserDetailsService).passwordEncoder(passwordEncoder());
    }
	@Bean
    public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
    }
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
			.antMatchers("/", "/favicon.ico", "/**/*.png", "/**/*.gif", "/**/*.svg", "/**/*.jpg", "/**/*.html",
				"/**/*.css", "/**/*.js")
			.permitAll().antMatchers("/auth/**").permitAll()
			.antMatchers("/login").permitAll().anyRequest()
			.authenticated()
			.and()
			.rememberMe()
			.key(tokenKey)
			.rememberMeServices(rememberMeServices());

		// Add our custom JWT security filter
		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	 }
	 @Bean
	    public RememberMeServices rememberMeServices() {
		TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices(tokenKey, allUserDetailsService);
		rememberMeServices.setAlwaysRemember(true);
		rememberMeServices.setTokenValiditySeconds(CookieHelper.MAX_AGE);
		return rememberMeServices;
	    }

}
