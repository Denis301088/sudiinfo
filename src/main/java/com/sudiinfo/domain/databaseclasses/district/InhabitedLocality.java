package com.sudiinfo.domain.databaseclasses.district;

import javax.persistence.*;
import java.util.List;

@Entity
public class InhabitedLocality {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @ManyToOne
    private DistrictJudicialSector districtJudicialSector;

    @OneToMany(mappedBy = "inhabitedLocality",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Settlement>settlements;

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

    public DistrictJudicialSector getDistrictJudicialSector() {
        return districtJudicialSector;
    }

    public void setDistrictJudicialSector(DistrictJudicialSector districtJudicialSector) {
        this.districtJudicialSector = districtJudicialSector;
    }

    public List<Settlement> getSettlements() {
        return settlements;
    }

    public void setSettlements(List<Settlement> settlements) {
        this.settlements = settlements;
    }
}
