<#if streets??>
    <div class="row justify-content-center">
        <div class="pb-2">
            <strong>
                <h6>Отчет для улицы: ${street.name}</h6>
            </strong>
        </div>

    </div>
    <div class="row justify-content-center pb-4">
        <h6>В таблице представлены все судебные участки г. Белгорода, принадлежащие заданной улице</h6>
    </div>

    <table class="table table-bordered">
        <thead>
        <tr>
            <th class="table-warning" scope="col"></th>
            <th class="table-warning" scope="col"><p class="text-lg-center">Диапазон домов</p></th>
            <th class="table-warning" scope="col"><p class="text-lg-center">Номера домов №</p></th>
            <th class="table-warning" scope="col"><p class="text-lg-center">Кроме домов №</p></th>
            <th class="table-warning" scope="col"><p class="text-lg-center">Сторона улицы</p></th>
            <th class="table-primary" scope="col"><p class="text-lg-center">№ Участка, округ</p></th>
            <th class="table-primary" scope="col"><p class="text-lg-center">Отчет о пересечении участка</p></th>
        </tr>
        </thead>
        <tbody>
        <#list streets! as st>
            <#if st.diapasonHouses?has_content>
                <#list st.diapasonHouses?if_exists as diapason>
                    <tr <#if st_index%2==0>class="table-secondary"<#else>class="table-secondary"</#if>>
                        <td scope="row"><strong><p class="text-nowrap">начиная с №:</p></strong></td>
                        <td>
                            <#if (diapason.getHouseDiapason("FIRST"))??>
                                <p class="text-lg-center">
                                д. ${(diapason.getHouseDiapason("FIRST").number)?if_exists} ${(diapason.getHouseDiapason("FIRST").housing)?if_exists}<#else>с начала улицы
                                </p>
                            </#if>
                        </td>
                        <#if diapason_index==0>
                            <td rowspan="${st.diapasonHouses?size*2}">
                                <#if st.houses?size!=0>
                                    <p class="text-center">
                                    <#list st.houses as house>
                                        <div>
                                            д. ${house.number}${house.housing},
                                        </div>
                                    </#list>
                                    </p>
                                <#else>-</#if>
                            </td>
                        </#if>
                        <td rowspan="2">
                            <p class="text-center">
                                <#if diapason.housesException?size!=0>
                                    <#list diapason.housesException as houseEx>д. ${houseEx.number}${houseEx.housing},</#list>
                                <#else>
                                    -
                                </#if>
                            </p>
                        </td>
                        <td rowspan="2">
                            <p class="text-center">
                                <#if diapason.STREETSIDE.name()=="RIGHT">Правая сторона<#elseif diapason.STREETSIDE.name()=="LEFT">Левая сторона<#else>Левая и правая сторона</#if>
                            </p>
                        </td>
                        <#if diapason_index==0>
                            <td rowspan="${st.diapasonHouses?size*2}">
                                <p class="text-center">
                                    Судебный участок № ${st.judicialSector.number_sector} ${st.judicialSector.districtCity.name}
                                </p>
                            </td>
                            <td rowspan="${st.diapasonHouses?size*2}">
                                <p>
                                <div class="form-group">
                                    <a class="btn btn-primary" href="/report1?judicial_sector=${st.judicialSector.id}">Получить отчет</a>
                                </div>
                                </p>
                            </td>
                        </#if>
                    </tr>
                    <tr <#if st_index%2==0>class="table-secondary"<#else>class="table-secondary"</#if>>
                        <td scope="row"><p class="text-nowrap"><strong>заканчивая №:</strong></p></td>
                        <td>
                            <p class="text-center">
                                <#if (diapason.getHouseDiapason("LAST"))??>
                                    д. ${(diapason.getHouseDiapason("LAST").number)?if_exists}${(diapason.getHouseDiapason("LAST").housing)?if_exists}<#else>до конца
                                </#if>
                            </p>
                        </td>
                    </tr>
                </#list>
            <#else>
                <tr <#if st_index%2==0>class="table-success"<#else>class="table-danger"</#if>>
                    <td scope="row">
                        <strong>
                            <p class="text-center">
                                <#if !st.houses?has_content>Вся улица</#if>
                            </p>
                        </strong>
                    </td>
                    <td>
                        <p class="text-center">-</p>
                    </td>
                    <td>
                        <#if st.houses?size!=0>
                            <p class="text-center">
                            <#list st.houses as house>
                                <div class="text-nowrap">
                                    д. ${house.number}${house.housing},
                                </div>
                            </#list>
                            </p>
                        <#else><p class="text-center">-</p></#if>
                    </td>
                    <td>
                        <p class="text-center">-</p>
                    </td>
                    <td>
                        <p class="text-center">-</p>
                    </td>
                    <td>
                        <p class="text-center">
                            Судебный участок № ${st.judicialSector.number_sector} ${st.judicialSector.districtCity.name}
                        </p>
                    </td>
                    <td>
                        <p>
                        <div class="form-group">
                            <a class="btn btn-primary" href="/report?judicial_sector=${st.judicialSector.id}">Получить отчет</a>
                        </div>
                        </p>
                    </td>
                </tr>
            </#if>
        </#list>
        </tbody>
    </table>
    <#elseif streetEmpty??>
        <div class="row justify-content-center pb-5">
            <div>
                <strong>
                    Улица не найдена.
                </strong>
            </div>
        </div>
</#if>