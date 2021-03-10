package com.sudiinfo.domain.databaseclasses;

import com.sudiinfo.domain.DISTRICT_REGION;
import com.sudiinfo.domain.databaseclasses.city.City;
import com.sudiinfo.domain.databaseclasses.district.DistrictJudicialSector;

import javax.persistence.*;
import java.util.Set;

@Entity
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private DISTRICT_REGION RegionName;

    @OneToMany(mappedBy = "district",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<City>cities;

    @OneToMany(mappedBy = "district",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<DistrictJudicialSector>districtJudicialSectors;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DISTRICT_REGION getRegionName() {
        return RegionName;
    }

    public void setRegionName(DISTRICT_REGION regionName) {
        RegionName = regionName;
    }

    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }

    public Set<DistrictJudicialSector> getDistrictJudicialSectors() {
        return districtJudicialSectors;
    }

    public void setDistrictJudicialSectors(Set<DistrictJudicialSector> districtJudicialSectors) {
        this.districtJudicialSectors = districtJudicialSectors;
    }
}
