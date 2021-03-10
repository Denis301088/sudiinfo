package com.sudiinfo.controller;

import com.sudiinfo.domain.DIAPASON_HOUSES;
import com.sudiinfo.domain.DiapasonPredicat;
import com.sudiinfo.domain.Role;
import com.sudiinfo.domain.databaseclasses.User;
import com.sudiinfo.domain.databaseclasses.city.DiapasonHouses;
import com.sudiinfo.domain.databaseclasses.city.JudicialSector;
import com.sudiinfo.domain.databaseclasses.city.Street;
import com.sudiinfo.repo.JudicialSectorRepo;
import com.sudiinfo.repo.StreetRepo;
import com.sudiinfo.repo.UserRepo;
import com.sudiinfo.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/*
* Контроллер для работы с личным кабинетом администраора,главного администратора
* */
@Controller
@RequestMapping("admin")
public class AdminController {

    private StreetRepo streetRepo;
    private UserRepo userRepo;
    private UserService userService;

    public AdminController(StreetRepo streetRepo, UserRepo userRepo, UserService userService, PasswordEncoder passwordEncoder) {
        this.streetRepo = streetRepo;
        this.userRepo = userRepo;
        this.userService = userService;
    }

    @GetMapping
    public String getPageSettingsStreet(Model model){
        model.addAttribute("adminActive",true);
        return "admin";
    }

    @PreAuthorize("hasAuthority('MAIN_ADMIN')")
    @GetMapping("rights")
    public String getPageAdmins(Model model){
        model.addAttribute("mainAdminActive",true);
        return "admins_list";
    }

    @PreAuthorize("hasAuthority('MAIN_ADMIN')")
    @PostMapping("rights")
    public String addAdmin(@Valid User user, BindingResult bindingResult, Model model){
        model.addAttribute("mainAdminActive",true);
        userService.addUser(user,bindingResult,model);
        return "admins_list";
    }

    @PreAuthorize("hasAuthority('MAIN_ADMIN')")
    @PostMapping("update")
    public String updateAdmin(@RequestParam(name = "id")User admin,@RequestParam Map<String,String>form, Model model){
        model.addAttribute("mainAdminActive",true);
        admin.getRoles().clear();
        if(form.containsKey("status"))
            admin.getRoles().add(Role.MAIN_ADMIN);
        else
            admin.getRoles().add(Role.ADMIN);
        
        userRepo.save(admin);
        return "redirect:/admin/rights";
    }

    @PreAuthorize("hasAuthority('MAIN_ADMIN')")
    @PostMapping("delete")
    public String deleteAdmin(@RequestParam(name = "id")User admin,Model model){
        model.addAttribute("mainAdminActive",true);
        userRepo.delete(admin);
        return "redirect:/admin/rights";
    }

    @PostMapping("/settings_street")
    public String settingsStreet(@RequestParam String countHouses, @RequestParam String countDiapasones, Model model){
        model.addAttribute("mainAdminActive",true);
        if(StringUtils.hasText(countHouses)){
            int contH=Integer.parseInt(countHouses);
            List<Integer> houses=new ArrayList<>(contH);
            for (int i = 0; i < contH; i++) {
                houses.add(i);
            }
            model.addAttribute("countHouses",houses);
            model.addAttribute("isCoutHouses",true);

        }
        if(StringUtils.hasText(countDiapasones)){

            int countD=Integer.parseInt(countDiapasones);
            List<Integer>diapasones=new ArrayList<>(countD);
            for (int i = 0; i < countD; i++) {
                diapasones.add(i);
            }
            model.addAttribute("countDiapasones",diapasones);
            model.addAttribute("isCoutDiapasones",true);

        }
        return "admin";
    }

    @PostMapping("/addstreet")
    public String addStreet(Street street, Model model){// @ModelAttribute("container") HouseContainer container

        if(StringUtils.hasText(street.getName())){

            street.setName(street.getName().trim());
            if(street.getDiapasonHouses()!=null){
                for (DiapasonHouses diapasonHouses:street.getDiapasonHouses()){
                    if(diapasonHouses.getDiapason().get(DIAPASON_HOUSES.FIRST).getNumber()==0)
                        diapasonHouses.getDiapason().remove(DIAPASON_HOUSES.FIRST);
                    if(diapasonHouses.getDiapason().get(DIAPASON_HOUSES.LAST).getNumber()==0)
                        diapasonHouses.getDiapason().remove(DIAPASON_HOUSES.LAST);

                    diapasonHouses.setStreet(street);
                }
            }
            streetRepo.save(street);

        }else {

            model.addAttribute("nameError","Введите название улицы");
        }


        return "admin";
    }

    @ModelAttribute("streetsname")
    public Set<Street> getStreets() {
        return new TreeSet(streetRepo.findAll().stream().map(Street::getName).collect(Collectors.toList()));
    }

    @ModelAttribute("users")
    public List<User> getUsers() {
        return userRepo.findAll();
    }
}

