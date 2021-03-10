package com.sudiinfo.domain.databaseclasses.city;

import com.sudiinfo.domain.databaseclasses.city.DiapasonHouses;
import com.sudiinfo.domain.databaseclasses.city.House;
import com.sudiinfo.domain.databaseclasses.city.JudicialSector;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Street {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message="Введите название улицы")
    private String name;

    @OneToMany(mappedBy = "street",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<DiapasonHouses>diapasonHouses;

//    @OneToMany(mappedBy = "street")
//    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @ElementCollection(targetClass = House.class,fetch = FetchType.LAZY)
    @CollectionTable(name="street_house",joinColumns = @JoinColumn(name="street_id"))
    private List<House> houses;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="judicialSector_id")
    private JudicialSector judicialSector;

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

    public List<DiapasonHouses> getDiapasonHouses() {
        return diapasonHouses;
    }

    public void setDiapasonHouses(List<DiapasonHouses> diapasonHouses) {
        this.diapasonHouses = diapasonHouses;
    }

    public List<House> getHouses() {
        return houses;
    }

    public void setHouses(List<House> houses) {
        this.houses = houses;
    }

    public JudicialSector getJudicialSector() {
        return judicialSector;
    }

    public void setJudicialSector(JudicialSector judicialSector) {
        this.judicialSector = judicialSector;
    }
}
