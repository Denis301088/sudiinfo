<#if settlementsearch??>
<table class="table table-sm table-dark">
    <div class="table-responsive">
        <table class="table align-middle">
            <thead>
            <tr class="table-secondary">
                <th scope="col"><p class="text-center">Территориальная подсудность</p></th>
                <th scope="col"><p class="text-center">№ участка</p></th>
                <th scope="col"><p class="text-center">Контактная информация</p></th>
                <th scope="col"><p class="text-center">График работы</p></th>
            </thead>
            <tbody>
            <#list settlementsearch as settlement>
                <tr class="table-warning">
                    <td class="align-align-top">
                        <p>
                        <div class="text-center">
                            ${settlement.inhabitedLocality.name} ,
                        </div>
                        <div class="text-center">
                             ${settlement.name}
                        </div>
                        </p>
                    </td>
                    <td class="align-top">
                        <div>
                            <p class="text-center">
                            <div class="text-center">
                                <strong>
                                    Судебный участок №${settlement.inhabitedLocality.districtJudicialSector.number_sector}
                                </strong>
                            </div>
                            <div class="text-nowrap">
                                мирового судьи Белгородского района
                            </div>
                            <div class="text-center">Белгородской области</div>
                            </p>
                        </div>
                    </td>
                    <td class="align-top">
                        <div>
                            <p>
                            <div>
                                <strong>Адрес:</strong>
                                <div>
                                    <div>
                                        ${settlement.inhabitedLocality.districtJudicialSector.address}
                                    </div>
                                </div>
                                <strong>Веб-страница:</strong>
                                <div>
                                    <div>
                                        <a href="http://${settlement.inhabitedLocality.districtJudicialSector.webAddress}">${settlement.inhabitedLocality.districtJudicialSector.webAddress}</a>
                                    </div>
                                </div>
                            </div>
                            </p>
                        </div>
                    </td>
                    <td class="align-top">
                        <div>
                            <p>
                            <div>
                                <strong>Время работы:</strong>
                                <div>
                                    <div class="text-nowrap">
                                        Понедельник-Четверг: ${settlement.inhabitedLocality.districtJudicialSector.workSheduleJudicialSector.work_schedule[0]}
                                    </div>
                                    <div class="text-nowrap">
                                        Пятница: ${settlement.inhabitedLocality.districtJudicialSector.workSheduleJudicialSector.work_schedule[1]}
                                    </div>
                                </div>
                            </div>
                            <div>
                                <strong>График прима граждан:</strong>
                                <div>
                                    <div class="text-nowrap">
                                        Понедельник-Четверг: ${settlement.inhabitedLocality.districtJudicialSector.workSheduleJudicialSector.work_schedule[0]}
                                    </div>
                                    <div class="text-nowrap">
                                        Пятница: ${settlement.inhabitedLocality.districtJudicialSector.workSheduleJudicialSector.work_schedule[1]}
                                    </div>
                                </div>
                            </div>
                            <div>
                                <strong>Выдача справок и документов из канцелярии и архива:</strong>
                                <div>${settlement.inhabitedLocality.districtJudicialSector.workSheduleJudicialSector.work_schedule[3]}</div>
                            </div>
                            <div>
                                <strong>Обед:</strong>
                                <div>${settlement.inhabitedLocality.districtJudicialSector.workSheduleJudicialSector.work_schedule[2]}</div>
                            </div>
                            <div>
                                <strong>Выходные:</strong>
                                <div>${settlement.inhabitedLocality.districtJudicialSector.workSheduleJudicialSector.work_schedule[4]}</div>
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
</#if>