package com.sudiinfo.domain;

import com.sudiinfo.domain.databaseclasses.city.DiapasonHouses;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
 * Обработка коллекции диапазонов улиц всех судебных участков для формирования отчета о пресечениях
 * территориальных подсудностей запрашиваемого участка с территориальными подсудностями других участков
 * */
public class FunctionReportSector implements Function<DiapasonHouses, List<DiapasonHouses>> {

    private List<DiapasonHouses> diapasonHousesAll;

    public FunctionReportSector(List<DiapasonHouses> diapasonHousesAll) {
        this.diapasonHousesAll = diapasonHousesAll;
    }

    @Override
    public List<DiapasonHouses> apply(DiapasonHouses diapasonHousesJudicialSector) {

        List<DiapasonHouses> resultRange = new ArrayList<>();
        List<DiapasonHouses> diapasonHousesAllFilter = diapasonHousesAll.stream().filter(x -> x.getStreet().getName().equals(diapasonHousesJudicialSector.getStreet().getName())).collect(Collectors.toList());
        for (DiapasonHouses diapasonHouses : diapasonHousesAllFilter) {
            if (diapasonHousesJudicialSector.getSTREETSIDE() == diapasonHouses.getSTREETSIDE()
                    || diapasonHousesJudicialSector.getSTREETSIDE() == STREET_SIDE.ALL
                    || diapasonHouses.getSTREETSIDE() == STREET_SIDE.ALL) {

                if (processRange(diapasonHousesJudicialSector, diapasonHouses) || processRange(diapasonHouses, diapasonHousesJudicialSector)) {
                    resultRange.add(diapasonHouses);

                }
            }
        }
        return resultRange;
    }

    private boolean processRange(DiapasonHouses diapasonHouses1, DiapasonHouses diapasonHouses2) {

        if (diapasonHouses1.getDiapason().containsKey(DIAPASON_HOUSES.FIRST)
                && diapasonHouses2.getDiapason().containsKey(DIAPASON_HOUSES.LAST)
                && diapasonHouses2.getDiapason().containsKey(DIAPASON_HOUSES.FIRST)) {

            return diapasonHouses2.getDiapason().get(DIAPASON_HOUSES.FIRST)
                    .compareTo(diapasonHouses1.getDiapason().get(DIAPASON_HOUSES.FIRST)) <= 0
                    && diapasonHouses1.getDiapason().get(DIAPASON_HOUSES.FIRST)
                    .compareTo(diapasonHouses2.getDiapason().get(DIAPASON_HOUSES.LAST)) <= 0;

        } else if (!diapasonHouses2.getDiapason().containsKey(DIAPASON_HOUSES.FIRST) && diapasonHouses2.getDiapason().containsKey(DIAPASON_HOUSES.LAST)
                && diapasonHouses1.getDiapason().containsKey(DIAPASON_HOUSES.FIRST)) {

            return diapasonHouses1.getDiapason().get(DIAPASON_HOUSES.FIRST)
                    .compareTo(diapasonHouses2.getDiapason().get(DIAPASON_HOUSES.LAST)) <= 0;

        } else if (!diapasonHouses2.getDiapason().containsKey(DIAPASON_HOUSES.LAST) && diapasonHouses2.getDiapason().containsKey(DIAPASON_HOUSES.FIRST)
                && diapasonHouses1.getDiapason().containsKey(DIAPASON_HOUSES.LAST)) {
            return diapasonHouses1.getDiapason().get(DIAPASON_HOUSES.LAST)
                    .compareTo(diapasonHouses2.getDiapason().get(DIAPASON_HOUSES.FIRST)) >= 0;
        } else if (!diapasonHouses1.getDiapason().containsKey(DIAPASON_HOUSES.FIRST) && !diapasonHouses2.getDiapason().containsKey(DIAPASON_HOUSES.FIRST)
                || !diapasonHouses1.getDiapason().containsKey(DIAPASON_HOUSES.LAST) && !diapasonHouses2.getDiapason().containsKey(DIAPASON_HOUSES.LAST)) {
            return true;
        }

        return false;
    }
}
