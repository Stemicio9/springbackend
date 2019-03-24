package w1n.backend.springbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import w1n.backend.springbackend.components.CustomizeAuthenticationSuccessHandler;
import w1n.backend.springbackend.services.UserDetailService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;

    @Bean
    public UserDetailsService mongoUserDetails() {
        return new UserDetailService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserDetailsService userDetailsService = mongoUserDetails();
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/menutoggle").permitAll()
                .antMatchers("/menulaptop").permitAll()
                .antMatchers("/layoutdevice").permitAll()
                .antMatchers("/signupdatore").permitAll()
                .antMatchers("/homedatore").permitAll()
                .antMatchers("/homelavoratore").permitAll()
                .antMatchers("/dashboarduser").permitAll()
                .antMatchers("/dashboarddatore").permitAll()
                .antMatchers("/paginaprovapeppe").permitAll()
                .antMatchers("/paginaprovapeppefiltered/**").permitAll()
                .antMatchers("/paginaprovapeppefilteredavanti/**").permitAll()
                .antMatchers("/signupamministratore").permitAll()
                .antMatchers("/annunci").permitAll()
                .antMatchers("/zone").permitAll()
                .antMatchers("/tuttigliutenti").permitAll()
                .antMatchers("/incentivifile").permitAll()
                .antMatchers("/incentivitermici").permitAll()

                .antMatchers("/dashboard/**").hasAuthority("ADMIN").anyRequest()
                .authenticated().and().csrf().disable().formLogin().successHandler(customizeAuthenticationSuccessHandler)

                .loginPage("/login").failureUrl("/login?error=true")
                .usernameParameter("email")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").and().exceptionHandling();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }



}