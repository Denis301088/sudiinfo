package com.sudiinfo.controller;

import com.sudiinfo.domain.databaseclasses.city.DiapasonHouses;
import com.sudiinfo.domain.databaseclasses.city.JudicialSector;
import com.sudiinfo.domain.databaseclasses.city.Street;
import com.sudiinfo.repo.JudicialSectorRepo;
import com.sudiinfo.repo.StreetRepo;
import com.sudiinfo.service.ReportHandlerSector;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

/*
* Контроллер для отчетов об улицах судебных участков и ошибках в пересечениях диапазонов домов улиц с улицами других участков
* */
@Controller
public class ReportController {

    private StreetRepo streetRepo;
    private JudicialSectorRepo judicialSectorRepo;
    private ReportHandlerSector reportHandlerSector;

    public ReportController(StreetRepo streetRepo, JudicialSectorRepo judicialSectorRepo, ReportHandlerSector reportHandlerSector) {
        this.streetRepo = streetRepo;
        this.judicialSectorRepo = judicialSectorRepo;
        this.reportHandlerSector = reportHandlerSector;
    }

    @GetMapping("reports")
    public String getPageReportForStreet(Model model){

        model.addAttribute("reportActive",true);
        model.addAttribute("streetsname",new TreeSet(streetRepo.findAll().stream().map(Street::getName).collect(Collectors.toList())));
        model.addAttribute("judicial_sectors",judicialSectorRepo.findAll());

        return "report";
    }

    @GetMapping("/report")
    public String getReport(Street street, @RequestParam(name = "judicial_sector",required = false) JudicialSector judicialsector, Model model){

        model.addAttribute("reportActive",true);
        model.addAttribute("streetsname",new TreeSet(streetRepo.findAll().stream().map(Street::getName).collect(Collectors.toList())));
        model.addAttribute("judicial_sectors",judicialSectorRepo.findAll());


        if(judicialsector==null){
            if(StringUtils.hasText(street.getName())){
//                List<Street> streets=streetRepo.findByName(street.getName());
                List<Street> streets=streetRepo.findAll().stream().filter(x->x.getName().equalsIgnoreCase(street.getName())).collect(Collectors.toList());
                if(streets.isEmpty())model.addAttribute("streetEmpty",true);
                else {
                    model.addAttribute("streets",streets);
                    model.addAttribute("sizediapasones",streets.stream()
                            .map(Street::getDiapasonHouses)
                            .collect(Collectors.toList()).size());
                }

            }else {
                model.addAttribute("nameError","Введите название улицы");
            }

        }else {
            model.addAttribute("street",null);

            model.addAttribute("resultRange",reportHandlerSector.handleReportSector(judicialsector));
            model.addAttribute("judicial_sector",judicialsector);


//            List<DiapasonHouses>diapasonHousesAll=new ArrayList<>();
//            streetRepo.findAll().stream().filter(x->!x.getJudicialSector().getWebAddress().equals(judicialsector.getWebAddress())).map(Street::getDiapasonHouses).forEach(x->diapasonHousesAll.addAll(x));
//            Map<String,List<DiapasonHouses>> diapasonsAll=diapasonHousesAll.stream().collect(Collectors.groupingBy(x->x.getStreet().getName()));
//
//            List<DiapasonHouses>diapasonHousesJudicialSector=new ArrayList<>();
//            judicialsector.getStreets().forEach(x->diapasonHousesJudicialSector.addAll(x.getDiapasonHouses()));
//            Map<String,List<DiapasonHouses>>diapasonsJudicialSector=diapasonHousesJudicialSector.stream().collect(Collectors.groupingBy(x->x.getStreet().getName()));


//            DiapasonPredicat predicate=new DiapasonPredicat();
//            for(Map.Entry<String,List<DiapasonHouses>>entryDiapasonesStreets:diapasonsAll.entrySet()){
//                if(diapasonsJudicialSector.containsKey(entryDiapasonesStreets.getKey())){
//
//                    predicate.setDiapasonHousesAll(entryDiapasonesStreets.getValue());
//                    diapasonsJudicialSector.get(entryDiapasonesStreets.getKey()).stream().filter(predicate).collect(Collectors.toList());
//
//                }
//            }

//            Map<DiapasonHouses,List<DiapasonHouses>> map=predicate.getMap();
//            for (Map.Entry<DiapasonHouses, List<DiapasonHouses>> result:map.entrySet()){
//                map.put(result.getKey(),result.getValue().stream().distinct().collect(Collectors.toList()));
//            }


        }

        return "report";
    }

}
