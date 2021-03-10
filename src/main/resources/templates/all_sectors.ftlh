<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="row justify-content-center pb-4">
            <strong>Мировые участки Белгорода и Белгородского района:</strong>
    </div>
    <table class="table justify-content-center">
        <thead>
        <tr class="table-light">
            <th scope="col"></th>
            <th scope="col"></th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <td class="table-active" colspan="4"><strong><div class="text-center"><h5>Мировые участки г. Белгорода:</h5></div></strong></td>
        <#list judicial_sectors as sector>
            <tr <#if sector_index%2==0>class="table-active"<#else>class="table-warning"</#if>>
                <th scope="row">${sector_index+1}</th>
                <td>
                    <div><strong>СУДЕБНЫЙ УЧАСТОК № ${sector.number_sector}</strong></div>
                    <div>мирового судьи <strong>${sector.districtCity.name}</strong> г. Белгорода</div>
                </td>
                <td>
                    <div><strong>Адрес:</strong></div>
                    <div>${sector.address}</div>
                </td>
                <td>
                    <div><strong>Веб страница:</strong></div>
                    <div>
                        <div>
                            <a href="http://${sector.webAddress}">${sector.webAddress}</a>
                        </div>
                    </div>
                </td>
            </tr>
        </#list>
        <td class="table-active" colspan="4"><strong><div class="text-center"><h5>Мировые участки Белгородского района:</h5></div></strong></td>
        <#list district_judicial_sectors as sector>
            <tr <#if sector_index%2==0>class="table-warning"<#else>class="table-active"</#if>>
                <th scope="row">${sector_index+1}</th>
                <td>
                    <div><strong>СУДЕБНЫЙ УЧАСТОК № ${sector.number_sector}</strong></div>
                    <div>Белгородского района</div>
                </td>
                <td>
                    <div><strong>Адрес:</strong></div>
                    <div>${sector.address}</div>
<#--                    <div><strong>Веб страница:</strong></div>-->
<#--                    <div>${sector.webAddress}</div>-->
                </td>
                <td>
                    <div><strong>Веб страница:</strong></div>
                    <div>
                        <div>
                            <a href="http://${sector.webAddress}">${sector.webAddress}</a>
                        </div>
                    </div>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>

</@c.page>