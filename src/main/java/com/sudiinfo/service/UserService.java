package com.sudiinfo.service;

import com.sudiinfo.controller.ControllerUtils;
import com.sudiinfo.domain.Role;
import com.sudiinfo.domain.databaseclasses.User;
import com.sudiinfo.repo.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

/*
* Сервисный класс для добавления администратора в БД
* */
@Service
public class UserService implements UserDetailsService {

    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {//Класс Security менеджер работает с базой юзеров через этот метод

        User user=userRepo.findByUsername(username);
        if(user==null) throw new UsernameNotFoundException("Неверный логин или пароль");//Это исключение будет выбрасываться Security
        // менеджером на строницу /login в поле ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
        return user;
    }

    public void addUser(@Valid User user, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            model.addAllAttributes(ControllerUtils.getErrors(bindingResult));
        }else {
            User userFromDb=userRepo.findByUsername(user.getUsername());
            if(userFromDb!=null){
                model.addAttribute("usernameError","Администратор уже был добавлен");
            }else {
                user.setRoles(Collections.singleton(Role.ADMIN));
                user.setPassword(passwordEncoder.encode(user.getPassword()));

                ((List<User>)model.getAttribute("users")).add(userRepo.save(user));
            }
        }

    }

}
