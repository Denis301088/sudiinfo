package com.sudiinfo.domain.databaseclasses.city;

import javax.persistence.*;
import java.util.Comparator;
import java.util.Objects;


@Embeddable
public class House implements Comparable<House>{

//    @NotBlank(message = "Введи номер дома")
    private int number;

    private String housing;

    public House() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getHousing() {
        return housing;
    }

    public void setHousing(String housing) {
        this.housing = housing;
    }

    @Override
    public int compareTo(House house) {

        return Comparator.comparing(House::getNumber).thenComparing(House::getHousing).compare(this,house);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return number == house.number &&
                Objects.equals(housing, house.housing);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, housing);
    }
}
