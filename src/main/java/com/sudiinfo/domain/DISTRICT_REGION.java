package com.sudiinfo.domain;

/*
* Район области
* */
public enum DISTRICT_REGION {

    Alekseyevskiy("Алексеевский"),
    Belgorodskij("Белгородский"),
    Borisovskij("Борисовский"),
    Valujskij("Валуйский"),
    Vejdelevskij("Вейделевский"),
    Volokonovskij("Волоконовский"),
    Grajvoronskij("Грайворонский"),
    Gubkinskij("Губкинский"),
    Ivnyanskij("Ивнянский"),
    Korochanskij("Корочанский"),
    Krasnenskij("Красненский"),
    Krasnogvardejskij("Красногвардейский"),
    Krasnoyaruzhskij("Краснояружский"),
    Novooskolskij("Новооскольский"),
    Prohorovskij("Прохоровский"),
    Rakityanskij("Ракитянский"),
    Rovenskij("Ровеньский"),
    Starooskolskij("Старооскольский"),
    Chernyanskij("Чернянский"),
    Shebekinskij("Шебекинский"),
    Yakovlevskij("Яковлевский");


    private String name;

    DISTRICT_REGION(String name){
        this.name=name;
    }

    @Override
    public String toString() {
        return name;
    }
}
