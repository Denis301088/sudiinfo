<form method="get" action="/search_district">
    <div class="container">
        <div class="row justify-content-start pb-4">
            <div class="col-md-4">
                <select id="inputState" class="form-control" placeholder="Название ">
                        <option>Белгородский р-н</option>
                </select>
            </div>
            <div class="col-md-4">
                <input class="form-control ${(settlement?? && nameError??)?string('is-invalid', '')}" list="datalistOptions1" id="exampleDataList" name="name"
                       value="<#if settlement??>${settlement.name}</#if>" placeholder="Название поселка/села">
                <#if nameError??>
                    <div class="invalid-feedback">
                        ${nameError}
                    </div>
                </#if>
                <datalist id="datalistOptions1">
                    <#list settlementsname?if_exists as name>
                    <option value="${name}">
                        </#list>
                </datalist>
            </div>
            <div>
                <button type="submit" class="btn btn-primary ml-2">Найти участок</button>
            </div>
        </div>
    </div>
</form>