<#import "parts/common.ftlh" as c>
<@c.page>
    <form method="post" action="admin/settings_street">

        <div class="pb-4">
            <div>
                <div class="row justify-content-start">
                    <h6>Установка настроек для добавления улиц в базу данных:</h6>
                </div>
                <div class="row justify-content-center">
                </div>
            </div>
        </div>

        <div class="row g-3 pb-5">
            <div class="col-sm">
                <input type="number" class="form-control" name="countHouses" placeholder="Количество домов" aria-label="Zip">
            </div>
            <div class="col-sm">
                <input type="number" class="form-control" name="countDiapasones" placeholder="Количество диапазонов" aria-label="Zip">
            </div>
            <div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <button type="submit" class="btn btn-primary ml-2">Установить настройки</button>
            </div>
        </div>
    </form>
    <form method="post" action="/addstreet">
        <div class="row justify-content-start col-sm-5 pb-4">
                <input class="form-control ${(nameError??)?string('is-invalid', '')}" list="datalistOptions" id="exampleDataList" name="name" placeholder="Название улицы">
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
        <#if isCoutHouses??>
            <div class="row pb-2">
                <strong>
                    Перечисленисление домов:
                </strong>
            </div>
            <#list countHouses?if_exists as countHouse>
                <div class="row pb-2">
                    <strong>
                        ${countHouse+1}-й Дом
                    </strong>
                </div>
                <div class="container">
                    <div class="row pb-3 justify-content-start">
                        <div class="col-3">
                            <input type="number" class="form-control" placeholder="Номер дома" name="houses[${countHouse}].number" value aria-label="Zip">
                        </div>
                        <div class="col-2">
                            <input type="text" class="form-control" placeholder="Корпус" name="houses[${countHouse}].housing" aria-label="Zip">
                        </div>
                    </div>
                </div>
            </#list>
        </#if>
        <#if isCoutDiapasones??>
            <div class="row pb-2">
                <strong>
                    Перечисленисление диапазонов домов:
                </strong>
            </div>
            <#list countDiapasones?if_exists as countDiapasone>
                <div class="row pb-2">
                    <strong>
                        ${countDiapasone+1}-й Диапазон
                    </strong>
                </div>
                <div class="row pb-2">
                    <div class="btn-group" data-toggle="buttons">
                        <label class="btn btn-primary active mr-3">
                            <input type="radio" name="diapasonHouses[${countDiapasone}].STREETSIDE" value="RIGHT" autocomplete="off">Правая сторона
                        </label>
                        <label class="btn btn-primary active mr-3">
                            <input type="radio" name="diapasonHouses[${countDiapasone}].STREETSIDE" value="LEFT" autocomplete="off">Левая сторона
                        </label>
                        <label class="btn btn-primary active mr-3">
                            <input type="radio" name="diapasonHouses[${countDiapasone}].STREETSIDE" value="ALL" autocomplete="off">Все
                        </label>
                    </div>
                </div>
                <div class="row pb-3">
                    Первый дом:
                </div>
                <div class="container">
                    <div class="row g-3 pb-3 justify-content-start">
                        <div class="col-3">
                            <input type="number" class="form-control" placeholder="Номер дома" name="diapasonHouses[${countDiapasone}].diapason[FIRST].number" aria-label="Zip">
                        </div>
                        <div class="col-2">
                            <input type="text" class="form-control" placeholder="Корпус" name="diapasonHouses[${countDiapasone}].diapason[FIRST].housing" aria-label="Zip">
                        </div>
                    </div>
                </div>
                <div class="row pb-3">
                    Последний дом:
                </div>
                <div class="container">
                    <div class="row justify-content-start pb-4">
                        <div class="col-3">
                            <input type="number" class="form-control" placeholder="Номер дома" name="diapasonHouses[${countDiapasone}].diapason[LAST].number" value aria-label="Zip">
                        </div>
                        <div class="col-2">
                            <input type="text" class="form-control" placeholder="Корпус" name="diapasonHouses[${countDiapasone}].diapason[LAST].housing" aria-label="Zip">
                        </div>
                    </div>
                </div>
            </#list>
        </#if>
            <button type="submit" class="btn btn-primary ml-2">Добавить улицу</button>
        </div>
    </form>
    </div>
</@c.page>