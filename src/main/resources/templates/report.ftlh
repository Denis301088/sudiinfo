<#import "parts/common.ftlh" as c>
<@c.page>
    <div>
        <div class="row justify-content-start pb-4">
            <strong>
                Отчет по ошибкам в пересечениях территориальных подсудностей для участка:
            </strong>
        </div>
    </div>

    <form method="get" action="/report">
        <div class="container">
            <div class="pb-4">
                <div class="row justify-content-start pb-3">
                    <div class="col-sm-3 col-md-4">
                        <select class="form-control" id="exampleFormControlSelect1" name="judicial_sector">
                            <#list judicial_sectors! as sector>
                                <option value="${sector.id}">Участок №${sector.number_sector} ${sector.districtCity.name} </option>
                            </#list>
                        </select>
                    </div>
                    <div>
                        <button type="submit" class="btn btn-primary ml-2">Получить отчет</button>
                    </div>
                </div>
            </div>
        </div>
    </form>


    <div class="pb-4">
        <div>
            <div class="row justify-content-start">
                <strong>
                    Отчет по охвату судебными участками для улицы:
                </strong>
            </div>
        </div>
    </div>

    <form method="get" action="/report">
        <div class="container pb-4">
            <div class="row justify-content-start pb-4">
                <div class="col-sm-3 col-md-4">
                    <input class="form-control ${(street?? && nameError??)?string('is-invalid', '')}" list="datalistOptions" id="exampleDataList" name="name"
                           value="<#if street??>${street.name}</#if>" placeholder="Введите название улицы">
                    <#if nameError??>
                        <div class="invalid-feedback">
                            ${nameError}
                        </div>
                    </#if>
                    <datalist id="datalistOptions">
                        <#list streetsname?if_exists as name>
                        <option value="${name}">
                            </#list>
                    </datalist>
                </div>
                <div>
                    <button type="submit" class="btn btn-primary ml-2">Получить отчет</button>
                </div>
            </div>
        </div>
    </form>
    <#include "parts/table_report_for_judicialsector.ftlh">
    <#include "parts/table_report_for_street.ftlh">
</@c.page>