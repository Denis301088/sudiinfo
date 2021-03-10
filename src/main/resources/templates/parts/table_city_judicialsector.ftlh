<#if streetssearch??>
    <table class="table table-sm table-dark">
        <div class="table-responsive">
            <table class="table align-middle">
                <thead>
                <tr class="table-secondary">
                    <th  scope="col"><p class="text-center">Территориальная подсудность</p></th>
                    <th scope="col"><p class="text-center">Округ, № участка</p></th>
                    <th scope="col"><p class="text-center">Контактная информация</p></th>
                    <th scope="col"><p class="text-center">График работы</p></th>
                </thead>
                <tbody>
                <#list streetssearch as st>
                    <tr class="table-warning">
                        <td class="align-align-top">
                            <p>
                            <div class="text-center">
                                ${st.judicialSector.districtCity.city.district.regionName.toString()} р-н,
                            </div>
                            <div class="text-center">
                                г. ${st.judicialSector.districtCity.city.name},
                            </div>
                            <div class="text-nowrap">
                                ${st.name} ${house?ifExists} ${st.judicialSector.infoDiapasone?ifExists}
                            </div>
                            </p>
                        </td>
                        <td class="align-top">
                            <div>
                                <p>
                                <div class="text-center">
                                    <strong>
                                        Судебный участок №${st.judicialSector.number_sector}
                                    </strong>
                                </div>
                                <div class="text-nowrap">
                                    мирового судьи ${st.judicialSector.districtCity.name}
                                </div>
                                <div class="text-center">
                                    г. ${st.judicialSector.districtCity.city.name}
                                </div>
                                </p>
                            </div>
                        </td>
                        <td class="align-top">
                            <div>
                                <p class="text-center">
                                <div>
                                    <strong>Адрес:</strong>
                                    <div>
                                        <div class="text-nowrap">
                                            ${st.judicialSector.address}
                                        </div>
                                    </div>
                                    <strong>Веб-страница:</strong>
                                    <div>
                                        <div>
                                            <a href="http://${st.judicialSector.webAddress}">${st.judicialSector.webAddress}</a>
                                        </div>
                                    </div>
                                </div>
                                </p>
                            </div>
                        </td>
                        <td class="align-top">
                            <div>
                                <p class="text-center">
                                <div>
                                    <strong>Время работы:</strong>
                                    <div>
                                        <div class="text-nowrap">
                                            Понедельник-Четверг: ${st.judicialSector.workSheduleInfo[0]}
                                        </div>
                                        <div class="text-nowrap">
                                            Пятница: ${st.judicialSector.workSheduleInfo[1]}
                                        </div>
                                    </div>
                                </div>
                                <div>
                                    <strong>График прима граждан:</strong>
                                    <div class="text-nowrap">
                                        <div>
                                            Понедельник-Четверг: ${st.judicialSector.workSheduleInfo[0]}
                                        </div>
                                        <div class="text-nowrap">
                                            Пятница: ${st.judicialSector.workSheduleInfo[1]}
                                        </div>
                                    </div>
                                </div>
                                <div>
                                    <strong>Обед:</strong>
                                    <div>${st.judicialSector.workSheduleInfo[2]}</div>
                                </div>
                                <div>
                                    <strong>Выходные:</strong>
                                    <div>${st.judicialSector.workSheduleInfo[3]}</div>
                                </div>
                                </p>
                            </div>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </table>
    </div>
<#elseIf messageEmpty??>
    <div class="row justify-content-center pb-5">
        <div>
            <strong>
                Для вашего адреса не было найдено судебного участка.
            </strong>
        </div>
    </div>
</#if>