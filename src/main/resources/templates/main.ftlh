<#import "parts/common.ftlh" as c>
<#import "parts/searchcity.ftlh" as s>
<@c.page>
    <div class="pb-4">
        <div>
            <div class="row justify-content-center">
                <h6>Для поиска мирового судебного участка воспользуйтесь соответствующей формой поиска:</h6>
            </div>
        </div>
    </div>
<div class="accordion pb-5" id="accordionExample">
    <div class="card">
        <div class="card-header" id="headingOne">
            <h5 class="mb-0">
                <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseOne1" aria-expanded="true" aria-controls="collapseOne">
                   <strong>Нажмите для поиска по г. Белгороду:</strong>
                </button>
            </h5>
        </div>
        <div id="collapseOne1" class="collapse<#if street?? && nameError?? || numberError?? >show</#if>" aria-labelledby="headingOne" data-parent="#accordionExample">
            <div class="card-body">
                <div class="container">
                    <div class="row justify-content-start pb-3">
                        <h6>
                            Введите свой адрес:
                        </h6>
                    </div>
                        <#include "parts/searchcity.ftlh">
                </div>
            </div>
        </div>
    </div>
    <div class="card">
        <div class="card-header" id="headingOne">
            <h5 class="mb-0">
                <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseOne2" aria-expanded="true" aria-controls="collapseOne">
                    <strong>Нажмите для поиска по Белгородскому району:</strong>
                </button>
            </h5>
        </div>
        <div id="collapseOne2" class="collapse<#if settlement?? && nameError??>show</#if>" aria-labelledby="headingOne" data-parent="#accordionExample">
            <div class="card-body">
                <div class="container">
                    <div class="row justify-content-start pb-3">
                        <h6>
                            Введите название своего поселка/села (только для поиска по Белгородскому району):
                        </h6>
                    </div>
                    <#include "parts/searchdistrict.ftlh">
                </div>
            </div>
        </div>
    </div>
</div>
    <#include "parts/table_city_judicialsector.ftlh">
    <#include "parts/table_district_judicialsector.ftlh">
</@c.page>
