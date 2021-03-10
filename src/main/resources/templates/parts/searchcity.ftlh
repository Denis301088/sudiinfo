<form method="get" action="/search_city">
    <div class="container">
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
            <div class="col-sm-3">
                <input type="number" class="form-control ${(numberError??)?string('is-invalid', '')}" placeholder="Номер дома" name="number" aria-label="Zip">
                <#if numberError??>
                    <div class="invalid-feedback">
                        ${numberError}
                    </div>
                </#if>
            </div>
            <div class="col-sm-3">
                <input type="text" class="form-control" placeholder="Дробъ,корпус(буква, номер)" name="housing" aria-label="Zip">
            </div>
            <div>
                <button type="submit" class="btn btn-primary ml-2">Найти участок</button>
            </div>
        </div>
    </div>
</form>