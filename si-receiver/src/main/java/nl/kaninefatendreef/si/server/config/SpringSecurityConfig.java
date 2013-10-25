package nl.kaninefatendreef.si.server.config;

import nl.kaninefatendreef.si.server.security.SimplerInvoicingAuthenticationProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	SimplerInvoicingAuthenticationProvider simplerInvoicingAuthenticationProvider = null;

	//TODO 
	// Make wow
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeUrls()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .and()
        .httpBasic();
    }
    
    @Override
    protected void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(simplerInvoicingAuthenticationProvider);

    }
    
    @Override
    public void configure(WebSecurity builder) throws Exception {
    	// All is secure except.
        builder
            .ignoring()
                .antMatchers("/images/**")
                .antMatchers("/index.jsp")
                .antMatchers("/accessPointService");
    }
}
