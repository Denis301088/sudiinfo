package com.sudiinfo.domain.databaseclasses.district;

import com.sudiinfo.domain.databaseclasses.District;
import com.sudiinfo.domain.databaseclasses.WorkSheduleJudicialSector;

import javax.persistence.*;
import java.util.List;

@Entity
public class DistrictJudicialSector {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private int number_sector;

    private String address;

    private String webAddress;

    @ManyToOne
    private District district;

    @ManyToOne(fetch = FetchType.EAGER)
    private WorkSheduleJudicialSector workSheduleJudicialSector;

    @OneToMany(mappedBy = "districtJudicialSector",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<InhabitedLocality> inhabitedLocalities;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumber_sector() {
        return number_sector;
    }

    public void setNumber_sector(int number_sector) {
        this.number_sector = number_sector;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebAddress() {
        return webAddress;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public WorkSheduleJudicialSector getWorkSheduleJudicialSector() {
        return workSheduleJudicialSector;
    }

    public void setWorkSheduleJudicialSector(WorkSheduleJudicialSector workSheduleJudicialSector) {
        this.workSheduleJudicialSector = workSheduleJudicialSector;
    }

    public List<InhabitedLocality> getInhabitedLocalities() {
        return inhabitedLocalities;
    }

    public void setInhabitedLocalities(List<InhabitedLocality> inhabitedLocalities) {
        this.inhabitedLocalities = inhabitedLocalities;
    }
}
