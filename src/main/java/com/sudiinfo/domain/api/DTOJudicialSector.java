package com.sudiinfo.domain.api;

import com.sudiinfo.domain.databaseclasses.city.JudicialSector;

/*
* DTO класс с информацией о судебном участке для формировавния отчета
* */
public class DTOJudicialSector {

    private int number_sector;
    private String district;
    private String address;
    private String webAddress;

    public DTOJudicialSector(JudicialSector judicialSector) {
        this.number_sector = judicialSector.getNumber_sector();
        this.district=judicialSector.getDistrictCity().getName();
        this.address = judicialSector.getAddress();
        this.webAddress = judicialSector.getWebAddress();
    }

    public int getNumber_sector() {
        return number_sector;
    }

    public String getDistrict() {
        return district;
    }

    public String getAddress() {
        return address;
    }

    public String getWebAddress() {
        return webAddress;
    }
}
