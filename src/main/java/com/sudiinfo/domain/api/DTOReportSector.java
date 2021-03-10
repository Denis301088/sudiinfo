package com.sudiinfo.domain.api;

import com.sudiinfo.domain.databaseclasses.city.DiapasonHouses;
import com.sudiinfo.domain.databaseclasses.city.JudicialSector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
* DTO класс для предачи на отображение отчета об ошибках запрашиваемого участка(его улицах и соответствующим каждой из улиц спискам судебных участков,территориальная подсудность которых
* пересекается с территориальной подсудностью по улицам запрашиваемого участка)
* */
public class DTOReportSector {

    private DTOJudicialSector DTOJudicialSector;

    private Map<String, List<DTOJudicialSector>>mapResultRangeError=new HashMap<>();

    public DTOReportSector(JudicialSector judicialSector) {
        this.DTOJudicialSector = new DTOJudicialSector(judicialSector);
    }

    public DTOJudicialSector getDTOJudicialSector() {
        return DTOJudicialSector;
    }

    public void setMapResultRange(Map<DiapasonHouses, List<DiapasonHouses>> mapResultRange) {
        for (Map.Entry<DiapasonHouses,List<DiapasonHouses>>ranges:mapResultRange.entrySet()){
            this.mapResultRangeError.put(ranges.getKey().getStreet().getName(),ranges.getValue().stream().map(x->new DTOJudicialSector(x.getStreet().getJudicialSector())).collect(Collectors.toList()));
        }
    }

    public Map<String, List<DTOJudicialSector>> getMapResultRange() {
        return mapResultRangeError;
    }
}
