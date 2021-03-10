package com.sudiinfo.service;

import com.sudiinfo.controller.ControllerUtils;
import com.sudiinfo.domain.DIAPASON_HOUSES;
import com.sudiinfo.domain.STREET_SIDE;
import com.sudiinfo.domain.databaseclasses.city.DiapasonHouses;
import com.sudiinfo.domain.databaseclasses.city.House;
import com.sudiinfo.domain.databaseclasses.city.Street;
import com.sudiinfo.domain.databaseclasses.district.Settlement;
import com.sudiinfo.repo.SettlementRepo;
import com.sudiinfo.repo.StreetRepo;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/*
* Обработчик запроса для поиска судебного участка
* */
@Service
public class RequestHandler {

    private StreetRepo streetRepo;
    private SettlementRepo settlementRepo;

    public RequestHandler(StreetRepo streetRepo, SettlementRepo settlementRepo) {
        this.streetRepo = streetRepo;
        this.settlementRepo = settlementRepo;
    }

    public String handleRequestForCity(@Valid Street street, BindingResult bindingResult,
                                       String number, @RequestParam(required = false) String housing, Model model){

        List<Street> streets=streetRepo.findByName(street.getName());
        List<Street>streetsForView=new ArrayList<>();

        if(!bindingResult.hasErrors()){
            if(!StringUtils.hasText(number)){
                model.addAttribute("numberError","Введите номер дома");
                return "main";
            }

            int numberHouse=Integer.parseInt(number);
            House currentHouse=new House();
            currentHouse.setNumber(numberHouse);
            currentHouse.setHousing(housing.toLowerCase());

            for (Street st:streets){

                if(!st.getHouses().isEmpty() && st.getHouses().contains(currentHouse)){
                    streetsForView.add(st);
                    continue;
                }

                if(!st.getDiapasonHouses().isEmpty()){

                    if(!st.getDiapasonHouses().stream().map(DiapasonHouses::getHousesException).filter(x->x.contains(currentHouse)).findFirst().isEmpty())
                        continue;

                    for(DiapasonHouses diapason:st.getDiapasonHouses()){

//                        diapason.getDiapason().keySet().forEach(key->{
////                            switch (key){
////                                case FIRST:
////
////                                case LAST:
////                            }
////                        });

                        if(diapason.getDiapason().containsKey(DIAPASON_HOUSES.FIRST)){
                            House houseFirst=diapason.getDiapason().get(DIAPASON_HOUSES.FIRST);
                            if (houseFirst.compareTo(currentHouse)>0)
                                continue;
                        }
                        if(diapason.getDiapason().containsKey(DIAPASON_HOUSES.LAST)){
                            House houseLast=diapason.getDiapason().get(DIAPASON_HOUSES.LAST);
                            if (houseLast.compareTo(currentHouse)<0)
                                continue;
                        }
                        if(diapason.getSTREETSIDE()==STREET_SIDE.RIGHT){
                            if(numberHouse%2==0){
                                streetsForView.add(st);
                                break;
                            }
                        } else if(diapason.getSTREETSIDE()==STREET_SIDE.LEFT){
                            if(numberHouse%2!=0){
                                streetsForView.add(st);
                                break;
                            }
                        }else {
                            streetsForView.add(st);
                            break;
                        }
                    }

                }else if (st.getHouses().isEmpty()){//Заглушка,если не нашлось по домам чтобы не сработало как по всей улице,если список диапазонов пуст
                    streetsForView.add(st);
                }

            }

            if(!streetsForView.isEmpty()){
                for (Street st:streetsForView){
                    st.getJudicialSector().setWorkSheduleInfo(st.getJudicialSector().getWorkSheduleJudicialSector().getWork_schedule());
                }
                model.addAttribute("streetssearch",streetsForView);
                if(StringUtils.hasText(number) && Integer.parseInt(number)>0){
                    if(StringUtils.hasText(number) && StringUtils.hasText(housing))
                        model.addAttribute("house","д. " + number +"-"+ housing);
                    else
                        model.addAttribute("house","д. " + number);
                }
            }else {
                model.addAttribute("messageEmpty","Для вавшего запроса не был найден судебный участок");
            }

        }else{

            model.addAllAttributes(ControllerUtils.getErrors(bindingResult));
            model.addAttribute("street",street);
        }

        return "main";

    }

    public String handleRequestForDistrict(@Valid Settlement settlement, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAllAttributes(ControllerUtils.getErrors(bindingResult));

        }else {
            List<Settlement>settlements=settlementRepo.findByName(settlement.getName());
            model.addAttribute("settlementsearch",settlements);
        }

        return "main";
    }
}
