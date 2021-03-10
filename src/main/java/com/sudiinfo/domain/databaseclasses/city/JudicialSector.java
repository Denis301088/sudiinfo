package com.sudiinfo.domain.databaseclasses.city;

import com.sudiinfo.domain.databaseclasses.WorkSheduleJudicialSector;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="judicial_sector")
public class JudicialSector {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private int number_sector;

    @OneToMany(mappedBy = "judicialSector",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Street>streets;

    @ManyToOne(fetch = FetchType.EAGER/*,optional = false*/)
    private DistrictCity districtCity;

    private String address;

    private String webAddress;

    @ManyToOne(fetch = FetchType.EAGER)
    private WorkSheduleJudicialSector workSheduleJudicialSector;

    @Transient
    private String infoDiapasone;

    @Transient
    private List<String> workSheduleInfo;

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

    public Set<Street> getStreets() {
        return streets;
    }

    public void setStreets(Set<Street> streets) {
        this.streets = streets;
    }

    public DistrictCity getDistrictCity() {
        return districtCity;
    }

    public void setDistrictCity(DistrictCity districtCity) {
        this.districtCity = districtCity;
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

    public WorkSheduleJudicialSector getWorkSheduleJudicialSector() {
        return workSheduleJudicialSector;
    }

    public void setWorkSheduleJudicialSector(WorkSheduleJudicialSector workSheduleJudicialSector) {
        this.workSheduleJudicialSector = workSheduleJudicialSector;
    }

    public String getInfoDiapasone() {
        return infoDiapasone;
    }

    public void setInfoDiapasone(String infoDiapasone) {
        this.infoDiapasone = infoDiapasone;
    }

    public List<String> getWorkSheduleInfo() {
        return workSheduleInfo;
    }

    public void setWorkSheduleInfo(List<String> workSheduleInfo) {
        this.workSheduleInfo = workSheduleInfo;
    }
}
