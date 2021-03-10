package com.sudiinfo.service;

import com.sudiinfo.domain.FunctionReportSector;
import com.sudiinfo.domain.databaseclasses.city.DiapasonHouses;
import com.sudiinfo.domain.databaseclasses.city.JudicialSector;
import com.sudiinfo.domain.databaseclasses.city.Street;
import com.sudiinfo.repo.StreetRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/*
 * Обработчик запроса об ошибках в пересечениях диапазонов домов улиц судебных участков
 * */
@Service
public class ReportHandlerSector {

    private StreetRepo streetRepo;

    public ReportHandlerSector(StreetRepo streetRepo) {
        this.streetRepo = streetRepo;
    }

    public Map<DiapasonHouses, List<DiapasonHouses>> handleReportSector(JudicialSector judicialsector) {

        List<DiapasonHouses> diapasonHousesAll = new ArrayList<>();
        streetRepo.findAll().stream()
                .filter(x -> !x.getJudicialSector().getWebAddress().equals(judicialsector.getWebAddress()))
                .map(Street::getDiapasonHouses)
                .forEach(x -> diapasonHousesAll.addAll(x));

        List<DiapasonHouses> diapasonHousesJudicialSector = new ArrayList<>();
        judicialsector.getStreets().forEach(x -> diapasonHousesJudicialSector.addAll(x.getDiapasonHouses()));

        FunctionReportSector function = new FunctionReportSector(diapasonHousesAll);
        Map<DiapasonHouses, List<DiapasonHouses>> mapResultRange = diapasonHousesJudicialSector.stream().collect(Collectors.toMap(x -> x, function))
                .entrySet().stream().filter(x -> !x.getValue().isEmpty()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));


        return mapResultRange;


    }
}
