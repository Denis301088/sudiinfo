package com.sudiinfo.domain.databaseclasses;

import com.sudiinfo.domain.databaseclasses.city.JudicialSector;
import com.sudiinfo.domain.databaseclasses.district.DistrictJudicialSector;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class WorkSheduleJudicialSector {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="work_shedule_info",joinColumns = {@JoinColumn(name="work_shedule_id",referencedColumnName ="id")})
    private List<String> work_schedule;

    @OneToMany(mappedBy = "workSheduleJudicialSector",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    Set<JudicialSector>judicialSectors;

    @OneToMany(mappedBy = "workSheduleJudicialSector",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    Set<DistrictJudicialSector>districtJudicialSectors;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getWork_schedule() {
        return work_schedule;
    }

    public void setWork_schedule(List<String> work_schedule) {
        this.work_schedule = work_schedule;
    }

    public Set<JudicialSector> getJudicialSectors() {
        return judicialSectors;
    }

    public void setJudicialSectors(Set<JudicialSector> judicialSectors) {
        this.judicialSectors = judicialSectors;
    }
}
