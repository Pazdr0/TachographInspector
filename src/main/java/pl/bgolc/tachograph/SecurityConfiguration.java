package pl.bgolc.tachograph;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import pl.bgolc.tachograph.repository.UserRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                    .usersByUsernameQuery("select username, passwd, enabled from \"tacho\".users where username = ?")
                    .authoritiesByUsernameQuery("select username, roles from \"tacho\".users where username = ?")
                    .passwordEncoder(passwordEncoder());


/*    	auth
                .inMemoryAuthentication()
                    .withUser("user").password("{noop}user").roles("USER");
*/
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                    .anyRequest()
                    .fullyAuthenticated()
                    .and()
                .formLogin()
                	.loginPage("/login")
                	.and()
                .httpBasic();
    }
}
