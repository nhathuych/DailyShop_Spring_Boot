package com.nhathuy.dailyshopv2.config;

import com.nhathuy.dailyshopv2.entity.Role;
import com.nhathuy.dailyshopv2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Qualifier("userDetailsServiceImpl")
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private SuccessLoginHandler successLoginHandler;

	@Autowired
	private FailureLoginHandler failureLoginHandler;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.headers().frameOptions().sameOrigin();

//		http.authorizeRequests().antMatchers("/**").permitAll();    //.hasRole("MEMBER")		// admin hay user bình thường đều có role này
//		http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");

		http.authorizeRequests()
				.antMatchers("/api/**", "/", "/logout", "/login**", "/register", "/403", "/404", "/product/**", "/product-detail/**", "/cart", "/checkout", "/forgot-password", "/change-password")
				.permitAll();
		http.authorizeRequests()
				.antMatchers("/admin/**").hasAuthority("ADMIN")
				.and().authorizeRequests().anyRequest().authenticated();

		http.authorizeRequests()    // yêu cầu ủy quyền
				.and()
				.formLogin()
				.loginPage("/login")    // đường dẫn tới page chứa form đăng nhập
				.usernameParameter("email")            // lấy từ form
				.passwordParameter("password")        // lấy từ form
				.loginProcessingUrl("/login")
				//.defaultSuccessUrl("/")        // đăng nhập thành công thì trở về trang chủ
				//.failureUrl("/login?error")        // đăng nhập thất bại thì load lại trang kèm theo lỗi
				.successHandler(successLoginHandler)    // bắt sự kiện khi login thành công
				.failureHandler(failureLoginHandler)
				.and()
				.rememberMe()
				.key("remember-me")
				.rememberMeParameter("remember-me")
				.tokenValiditySeconds(86400)    // 86400s = 24 giờ
				.and()
				.logout()
				.logoutUrl("/logout").permitAll()
				.deleteCookies("JSESSIONID", "remember-me")        // delete cookies when logout
				.logoutSuccessUrl("/login?logout").permitAll()
				.and()
				.exceptionHandling()
				.accessDeniedPage("/403")    // ko đủ quyền -> redirect tới page 403
				.and()
				.httpBasic();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/fonts/**", "/img/**", "/js/**", "/adminsrc/**");
	}

	@Component
	private class SuccessLoginHandler implements AuthenticationSuccessHandler {

		@Autowired
		private UserService userService;

		@Override
		public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
			Object principal = authentication.getPrincipal();
			Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

			// lưu user đã đăng nhập vào session
			if (principal instanceof UserDetails) {
				// sao ko chạy vô đây ??????????????????????????????????????????????????????????????????????????????
				request.getSession().setAttribute("currentUser", userService.findByEmail(((UserDetails) principal).getUsername()));
			}

			// nếu user là admin thì redirect tới trang admin, ngược lại redirect về home
			if (roles.contains(Role.ROLE_ADMIN)) {
				response.sendRedirect("/admin");
			} else {
				response.sendRedirect("/");
			}
		}
	}

	@Component
	private class FailureLoginHandler extends SimpleUrlAuthenticationFailureHandler {
		@Override
		public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
			String messageError = loginErrorMessage(exception);
			request.getSession().setAttribute("error", messageError);
			getRedirectStrategy().sendRedirect(request, response, "/login?error");
		}

		private String loginErrorMessage(AuthenticationException e) {
			if (e instanceof BadCredentialsException || e instanceof UsernameNotFoundException) {
				return "email or password is incorrect!!!";
			} else if (e instanceof AccountExpiredException) {
				return "Your account deleted!!!";
			} else if (e instanceof LockedException) {
				return "Your account is locked!!!";
			} else if (e instanceof DisabledException) {
				return "Your account isn't active";
			} else {
				return e.getMessage();
			}
		}
	}
}
