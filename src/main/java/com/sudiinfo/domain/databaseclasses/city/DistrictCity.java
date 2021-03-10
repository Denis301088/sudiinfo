package com.sudiinfo.domain.databaseclasses.city;

import javax.persistence.*;
import java.util.Set;

@Entity
public class DistrictCity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER/*,optional = false*/)
    private City city;

    @OneToMany(mappedBy = "districtCity",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<JudicialSector>judicialSectors;

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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Set<JudicialSector> getJudicialSectors() {
        return judicialSectors;
    }

    public void setJudicialSectors(Set<JudicialSector> judicialSectors) {
        this.judicialSectors = judicialSectors;
    }
}
