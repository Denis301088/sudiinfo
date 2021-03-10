package com.sudiinfo.domain;

import com.sudiinfo.domain.databaseclasses.city.DiapasonHouses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class DiapasonPredicat implements Predicate<DiapasonHouses> {
    private List<DiapasonHouses> diapasonHousesAll;
    private Map<DiapasonHouses,List<DiapasonHouses>> resultRange=new HashMap<>();

    public void setDiapasonHousesAll(List<DiapasonHouses> diapasonHousesAll) {
        this.diapasonHousesAll = diapasonHousesAll;
    }

    public Map<DiapasonHouses,List<DiapasonHouses>> getMap(){
        return resultRange;
    }

    @Override
    public boolean test(DiapasonHouses diapasonHousesJudicialSector) {

        for (DiapasonHouses diapasonHouses:diapasonHousesAll){
            if(diapasonHousesJudicialSector.getSTREETSIDE()==diapasonHouses.getSTREETSIDE()
                    || diapasonHousesJudicialSector.getSTREETSIDE()==STREET_SIDE.ALL
                    || diapasonHouses.getSTREETSIDE()==STREET_SIDE.ALL){

                if(processRange(diapasonHousesJudicialSector,diapasonHouses) || processRange(diapasonHouses,diapasonHousesJudicialSector)){
                    if(!resultRange.containsKey(diapasonHousesJudicialSector)){
                        resultRange.put(diapasonHousesJudicialSector,new ArrayList<>());
                    }
                    resultRange.get(diapasonHousesJudicialSector).add(diapasonHouses);
                }
            }
        }
        return resultRange.containsKey(diapasonHousesJudicialSector);
    }

    private boolean processRange(DiapasonHouses diapasonHouses1,DiapasonHouses diapasonHouses2){

        if(diapasonHouses1.getDiapason().containsKey(DIAPASON_HOUSES.FIRST)
                && diapasonHouses2.getDiapason().containsKey(DIAPASON_HOUSES.LAST)
                && diapasonHouses2.getDiapason().containsKey(DIAPASON_HOUSES.FIRST)){

            return  diapasonHouses2.getDiapason().get(DIAPASON_HOUSES.FIRST)
                    .compareTo(diapasonHouses1.getDiapason().get(DIAPASON_HOUSES.FIRST)) <= 0
                    && diapasonHouses1.getDiapason().get(DIAPASON_HOUSES.FIRST)
                    .compareTo(diapasonHouses2.getDiapason().get(DIAPASON_HOUSES.LAST)) <= 0;

        }else if(!diapasonHouses2.getDiapason().containsKey(DIAPASON_HOUSES.FIRST) && diapasonHouses2.getDiapason().containsKey(DIAPASON_HOUSES.LAST)
                && diapasonHouses1.getDiapason().containsKey(DIAPASON_HOUSES.FIRST) ){

            return  diapasonHouses1.getDiapason().get(DIAPASON_HOUSES.FIRST)
                    .compareTo(diapasonHouses2.getDiapason().get(DIAPASON_HOUSES.LAST)) <= 0;

        }else if(!diapasonHouses2.getDiapason().containsKey(DIAPASON_HOUSES.LAST) && diapasonHouses2.getDiapason().containsKey(DIAPASON_HOUSES.FIRST)
                && diapasonHouses1.getDiapason().containsKey(DIAPASON_HOUSES.LAST)){
            return  diapasonHouses1.getDiapason().get(DIAPASON_HOUSES.LAST)
                    .compareTo(diapasonHouses2.getDiapason().get(DIAPASON_HOUSES.FIRST)) >= 0;
        }
        else if (!diapasonHouses1.getDiapason().containsKey(DIAPASON_HOUSES.FIRST) && !diapasonHouses2.getDiapason().containsKey(DIAPASON_HOUSES.FIRST)
        || !diapasonHouses1.getDiapason().containsKey(DIAPASON_HOUSES.LAST) && !diapasonHouses2.getDiapason().containsKey(DIAPASON_HOUSES.LAST)){
            return true;
        }

        return false;
    }
}
