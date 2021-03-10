package com.sudiinfo.domain.databaseclasses.district;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
public class Settlement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Введите название села/посёлка")
    private String name;

    @ManyToOne
    private InhabitedLocality inhabitedLocality;

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

    public InhabitedLocality getInhabitedLocality() {
        return inhabitedLocality;
    }

    public void setInhabitedLocality(InhabitedLocality inhabitedLocality) {
        this.inhabitedLocality = inhabitedLocality;
    }
}
