package com.sudiinfo.domain.databaseclasses.city;

import com.sudiinfo.domain.databaseclasses.District;

import javax.persistence.*;
import java.util.Set;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER/*,optional = false*/)
    private District district;

    @OneToMany(mappedBy = "city",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<DistrictCity> districtCities;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Set<DistrictCity> getDistrictCities() {
        return districtCities;
    }

    public void setDistrictCities(Set<DistrictCity> districtCities) {
        this.districtCities = districtCities;
    }
}
