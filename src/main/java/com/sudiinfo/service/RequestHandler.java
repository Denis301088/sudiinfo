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

        List<Street> streets=streetRepo.findAll().stream().filter(x->x.getName().equalsIgnoreCase(street.getName())).collect(Collectors.toList());
//        List<Street> streets=streetRepo.findByName(street.getName());
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

                        if((diapason.getDiapason().containsKey(DIAPASON_HOUSES.FIRST)
                                && diapason.getDiapason().get(DIAPASON_HOUSES.FIRST).compareTo(currentHouse)>0)
                            || (diapason.getDiapason().containsKey(DIAPASON_HOUSES.LAST)
                                && diapason.getDiapason().get(DIAPASON_HOUSES.LAST).compareTo(currentHouse)<0)) continue;

                        if(getCurrentStreetSide(diapason,numberHouse)){
                            streetsForView.add(st);
                            break;
                        }
                    }

                }else if (st.getHouses().isEmpty()){//Заглушка,если не нашлось по домам чтобы не сработало как по всей улице,если список диапазонов пуст
                    streetsForView.add(st);
                }

            }

            streetsForView.forEach(str-> str.getJudicialSector()
                    .setWorkSheduleInfo(str.getJudicialSector()
                            .getWorkSheduleJudicialSector().getWork_schedule()));


            if(!streetsForView.isEmpty()){
                model.addAttribute("streetssearch",streetsForView);
                if(StringUtils.hasText(number) && Integer.parseInt(number)>0){
                    if(StringUtils.hasText(number) && StringUtils.hasText(housing))
                        model.addAttribute("house","д. " + number +"-"+ housing);
                    else
                        model.addAttribute("house","д. " + number);
                }
            }else {
                model.addAttribute("messageEmptyCity",true);
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
//            List<Settlement>settlements=settlementRepo.findByName(settlement.getName());
            List<Settlement>settlements=settlementRepo.findAll().stream().filter(x->x.getName().equalsIgnoreCase(settlement.getName())).collect(Collectors.toList());
            if(settlements.isEmpty())
                model.addAttribute("messageEmptyDistrict",true);//для вашего запроса не было найдена поселка/села
            else
            model.addAttribute("settlementsearch",settlements);
        }

        return "main";
    }

    private boolean getCurrentStreetSide(DiapasonHouses diapason,int numberHouse){
        if(diapason.getSTREETSIDE()==STREET_SIDE.RIGHT)
            return numberHouse%2==0;

        else if(diapason.getSTREETSIDE()==STREET_SIDE.LEFT)
             return numberHouse%2!=0;

        return true;
    }
}
