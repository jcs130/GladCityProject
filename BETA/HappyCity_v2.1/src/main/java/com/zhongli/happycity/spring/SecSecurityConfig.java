package com.zhongli.happycity.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@ComponentScan(basePackages = { "com.zhongli.happycity.security" })
@EnableGlobalMethodSecurity(prePostEnabled  = true,securedEnabled=true)
@EnableWebSecurity
@EnableWebMvcSecurity
public class SecSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

	public SecSecurityConfig() {
		super();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider());
	}

	 @Override
	  public void configure(WebSecurity web) throws Exception {
	    web
	      .ignoring()
	         .antMatchers("/resources/**"); 
	  }

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.authorizeRequests()
				.antMatchers("/j_spring_security_check*", "/login*", "/logout*", "/signin/**", "/signup/**",
						"/user/registration*", "/regitrationConfirm*", "/expiredAccount*", "/registration*",
						"/badUser*", "/user/resendRegistrationToken*", "/forgetPassword*", "/user/resetPassword*",
						"/user/changePassword*", "/emailError*", "/resources/**", "/successRegister*", "/", "/index*").permitAll()
				.antMatchers("/invalidSession*").anonymous().anyRequest().authenticated()
				.antMatchers("/sa/**").hasRole("SUPERADMIN")
				.antMatchers("/admin/**", "/console*").hasRole("ADMIN")
				.antMatchers("/user/**").hasRole("USER")
				.and().formLogin()
				.loginPage("/login.html").loginProcessingUrl("/j_spring_security_check")
				.defaultSuccessUrl("/homepage.html").failureUrl("/login.html?error=true")
				.successHandler(myAuthenticationSuccessHandler).usernameParameter("j_username")
				.passwordParameter("j_password").permitAll().and().logout().invalidateHttpSession(false)
				.logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/logout.html?logSucc=true")
				.deleteCookies("JSESSIONID").permitAll().and().sessionManagement()
				.invalidSessionUrl("/invalidSession.html").sessionFixation().none().maximumSessions(1);
		// @formatter:on
	}

	// beans

	@Bean
	public DaoAuthenticationProvider authProvider() {
		final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(encoder());
		return authProvider;
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(11);
	}

}
