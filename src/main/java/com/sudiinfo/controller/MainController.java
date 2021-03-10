package com.sudiinfo.controller;

import com.sudiinfo.domain.databaseclasses.city.House;
import com.sudiinfo.domain.databaseclasses.city.Street;
import com.sudiinfo.domain.databaseclasses.district.Settlement;
import com.sudiinfo.repo.*;
import com.sudiinfo.service.RequestHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/*
* Контроллер для поиска судебного участка
* */
@Controller
public class MainController {

    private DistrictJudicialSectorRepo districtJudicialSectorRepo;
    private JudicialSectorRepo judicialSectorRepo;
    private StreetRepo streetRepo;
    private SettlementRepo settlementRepo;
    private RequestHandler requestHandler;

    public MainController(DistrictJudicialSectorRepo districtJudicialSectorRepo
            , JudicialSectorRepo judicialSectorRepo
            , StreetRepo streetRepo
            , SettlementRepo settlementRepo
            , RequestHandler requestHandler) {
        this.districtJudicialSectorRepo = districtJudicialSectorRepo;
        this.judicialSectorRepo = judicialSectorRepo;
        this.streetRepo = streetRepo;
        this.settlementRepo = settlementRepo;
        this.requestHandler = requestHandler;
    }

    @GetMapping("/")
    public String  main(Model model){
        model.addAttribute("searchActive",true);
        return "main";
    }

    @GetMapping("all_sectors")
    public String getAllSector(Model model){
        model.addAttribute("allSectorActive",true);
        model.addAttribute("judicial_sectors",judicialSectorRepo.findAll());
        model.addAttribute("district_judicial_sectors",districtJudicialSectorRepo.findAll());

        return "all_sectors";
    }

    @GetMapping("/search_city")
    public String searchCity(@Valid Street street, BindingResult bindingResult,
                         String number, @RequestParam(required = false) String housing, Model model){

        model.addAttribute("searchActive",true);
        return requestHandler.handleRequestForCity(street,bindingResult,number,housing,model);
    }

    @GetMapping("search_district")
    public String searchDistrict(@Valid Settlement settlement, BindingResult bindingResult, Model model){

        model.addAttribute("searchActive",true);
        return requestHandler.handleRequestForDistrict(settlement,bindingResult,model);
    }

    @ModelAttribute("streetsname")
    public Set<Street> getStreets() {
        return new TreeSet(streetRepo.findAll().stream().map(Street::getName).collect(Collectors.toList()));
    }

    @ModelAttribute("settlementsname")
    public Set<Street> getSettlements() {
        return new TreeSet(settlementRepo.findAll().stream().map(Settlement::getName).collect(Collectors.toList()));
    }

}

//class HouseContainer {
//    private List <Street> streets = new ArrayList<>();
//
//    public List<House> getHouses() {
//        return houses;
//    }
//
//    public void setHouses(List<House> houses) {
//        this.houses = houses;
//    }
//}
//
//class ModelContainer{
//
//    private Str
//}


