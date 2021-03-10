package com.sudiinfo.config;

import com.sudiinfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

//    public WebSecurityConfig(UserService userService, PasswordEncoder passwordEncoder) {
//        this.userService = userService;
//        this.passwordEncoder = passwordEncoder;
//    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder(8);//в параметре надежность ключа шифрования
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","/search_city","/search_district","/all_sectors","/report","/report*","/api/report*").permitAll()//permitAll()дает полный доступ к странице на котррую приходит пользователь по указанному пути.Для остальных запростов будет требоваться авторизация
                .anyRequest().authenticated()
                .and()
                .formLogin()//Подключаем форму для заполнения логина
                .loginPage("/login")//которая будет лежать по этому пути
                .defaultSuccessUrl("/admin")
                .permitAll()//Даем разрешение пользоваться этой формой всем
//                .and()
//                .rememberMe()//включаем сохранение параметров запроса при сбросе сессии.Если есть несколько серверов или сброс сесии происходит при перезапуске приложения,то идентификационные данные лучше хранить на внешнем кэше,например redis.В данном случае проскольку кейс простой,будем хранить данные в базе
                .and()
                .logout()//Подключаем форму выхода
                .permitAll();//даем разрешеие пользоваться формой выхода всем
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }


}
