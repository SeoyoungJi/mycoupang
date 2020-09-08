package com.mycoupang.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.mycoupang.service.MemberService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private MemberService memberService;

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
    }
	 
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.authorizeRequests()
	                // 권한 적용
	           //     .antMatchers("/admin/**").hasRole("ROLE_ADMIN")
	                .antMatchers("/**").permitAll()
	                
	             .and() // 로그인
	                .formLogin()
	                .loginPage("/login")
	                .defaultSuccessUrl("/loginEnd", true)
	                .usernameParameter("userid")
	                .passwordParameter("userpw")
	                .permitAll()
	                
	            .and() // 로그아웃
	                .logout()
	                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	                .logoutSuccessUrl("/main")
	                .invalidateHttpSession(true) //세션 삭제
	                .deleteCookies("JSESSIONID") //쿠키 삭제	
	                .clearAuthentication(true)   //권한 삭제
	                
	            .and()
	                .exceptionHandling().accessDeniedPage("/login");
	        
	    }
 
	
	  // 인증을 위한 AuthenticationManager생성을 위해
	  @Override 
	  public void configure(AuthenticationManagerBuilder auth) throws Exception {
		  auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
	  }
	 
	 
}
