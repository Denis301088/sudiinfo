package com.sudiinfo.domain.databaseclasses.city;

import com.sudiinfo.domain.DIAPASON_HOUSES;
import com.sudiinfo.domain.STREET_SIDE;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Entity
public class DiapasonHouses {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    private Street street;

    @Enumerated(EnumType.STRING)
    private STREET_SIDE STREETSIDE;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="diapason_houses_street",joinColumns = {@JoinColumn(name="diapason_houses_id",referencedColumnName ="id")})
    @MapKeyEnumerated(EnumType.STRING)
    private Map<DIAPASON_HOUSES, House>diapason;

    @ElementCollection(targetClass = House.class,fetch = FetchType.LAZY)
    @CollectionTable(name="diapason_houses_exception",joinColumns = @JoinColumn(name="diapason_houses_id"))
    private List<House> housesException;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public STREET_SIDE getSTREETSIDE() {
        return STREETSIDE;
    }

    public void setSTREETSIDE(STREET_SIDE STREETSIDE) {
        this.STREETSIDE = STREETSIDE;
    }

    public Map<DIAPASON_HOUSES, House> getDiapason() {
        return diapason;
    }

    public void setDiapason(Map<DIAPASON_HOUSES, House> diapason) {
        this.diapason = diapason;
    }

    public List<House> getHousesException() {
        return housesException;
    }

    public void setHousesException(List<House> housesException) {
        this.housesException = housesException;
    }

    public House getHouseDiapason(String diapasonHouses){

        House house=diapason.get(DIAPASON_HOUSES.valueOf(diapasonHouses));
        return diapason.get(DIAPASON_HOUSES.valueOf(diapasonHouses));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiapasonHouses that = (DiapasonHouses) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
