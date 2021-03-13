<#if resultRange??>
    <div class="row justify-content-center pb-2">
        <strong>
            <h6>В таблице представлены ошибки в пересечении территориальных подсудностей участка с другими участками:</h6>
        </strong>
    </div>
    <div class="row justify-content-center pb-2">
        <strong>
            <div class="row justify-content-center pb-2">
                <strong>
                    Отчет для участка:
                </strong>
            </div>
            <h5>
                № ${judicial_sector.number_sector} ${judicial_sector.districtCity.name} г. Белгорода
            </h5>
        </strong>
    </div>

    <table class="table table-bordered">
        <thead>
        <tr></tr>
        <tr>
            <th class="table-success" scope="col"><p class="text-center">Для улицы</p></th>
            <th class="table-success" scope="col"><p class="text-center">Диапазон участка</p></th>
            <th class="table-danger" scope="col"><p class="text-center">Пересечение с участком №:</p></th>
            <th class="table-danger" scope="col" colspan="3"><p class="text-center">Диапазон участка</p></th>
        </tr>
        </thead>
        <tbody>
        <#if resultRange?has_content>
            <#list resultRange as name,value>
                <#list value as val>
                    <tr <#if value?is_even_item>class="table-secondary"<#else>class="table-secondary"</#if>>
                        <#if val?is_first>
                            <td rowspan="${value?size*2}">
                                <p class="text-lg-center">
                                    ${name.street.name}
                                </p>
                            </td>
                            <td rowspan="${value?size*2}">
                                <div class="text-lg-center">
                                    <#if name.STREETSIDE.name()=="RIGHT">По правой стороне:<#elseif name.STREETSIDE.name()=="LEFT">По левой стороне:<#else>По правой и левой стороне:</#if>
                                </div>
                                <div class="text-lg-center">
                                    <#if name.getHouseDiapason("FIRST")??>
                                        начиная с д. ${(name.getHouseDiapason("FIRST").number)!} ${(name.getHouseDiapason("FIRST").housing)!}
                                    <#else>
                                        с начала улицы
                                    </#if>
                                    <#if name.getHouseDiapason("LAST")??>
                                        заканчивая д. ${(name.getHouseDiapason("LAST").number)!} ${(name.getHouseDiapason("LAST").housing)!}
                                    <#else>
                                        до конца улицы
                                    </#if>
                                </div>
                            </td>
                        </#if>
                        <td rowspan="2">
                            <p class="text-lg-center">
                                № ${val.street.judicialSector.number_sector} ${(val.street.judicialSector.districtCity.name)!} г. Белгорода
                            </p>
                        </td>
                        <td rowspan="2">
                            <p class="text-lg-center">
                                <#if val.STREETSIDE.name()=="RIGHT">По правой стороне:<#elseif val.STREETSIDE.name()=="LEFT">По левой стороне:<#else>По правой и левой стороне:</#if>
                            </p>
                        </td>
                        <td>
                            <p class="text-lg-center">
                                начиная с:
                            </p>
                        </td>
                        <td>
                            <p class="text-lg-center">
                                <#if val.getHouseDiapason("FIRST")??>
                                    д. ${(val.getHouseDiapason("FIRST").number)!} ${(val.getHouseDiapason("FIRST").housing)!}
                                <#else>
                                    начала улицы
                                </#if>
                            </p>
                        </td>
                    </tr>
                    <tr <#if value?is_even_item>class="table-secondary"<#else>class="table-secondary"</#if>>
                        <td><p class="text-lg-center">заканчивая:</p></td>
                        <td>
                            <p class="text-lg-center">
                                <#if val.getHouseDiapason("LAST")??>
                                    д. ${(val.getHouseDiapason("LAST").number)!} ${(val.getHouseDiapason("LAST").housing)!}
                                <#else>
                                    до конца
                                </#if>
                            </p>
                        </td>
                    </tr>
                </#list>
            </#list>
            <#else>
            <tr>
                <th colspan="4" class="table-success" scope="col">
                    <div class="row justify-content-center pb-2">
                        <strong>
                            Не найдено пересечений с территориальной подсудностью других участков
                        </strong>
                    </div>
                </th>
            </tr>
        </#if>
        </tbody>
    </table>
</#if>



