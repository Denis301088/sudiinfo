<#import "parts/common.ftlh" as c>
<@c.page>
   <div class="accordion pb-5" id="accordionExample">
        <div class="card">
            <div class="card-header" id="headingOne">
                <h5 class="mb-0">
                    <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapse1" aria-expanded="true" aria-controls="collapseOne">
                        <strong>Добавить администратора:</strong>
                    </button>
                </h5>
            </div>
            <form method="post" action="rights">
                <div id="collapse1" class="collapse<#if usernameError?? || passwordError??>show</#if>" aria-labelledby="headingOne" data-parent="#accordionExample">
                    <div class="card-body">
                        <div class="container justify-content-center">
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">Почта:</label>
                                <div class="col-sm-6">
                                    <input type="email" name="username" value="<#if user??>${user.username}</#if>"
                                           class="form-control ${(usernameError??)?string('is-invalid', '')}"
                                           placeholder="Почта" />
                                    <#if usernameError??>
                                        <div class="invalid-feedback">
                                            ${usernameError}
                                        </div>
                                    </#if>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">Пароль:</label>
                                <div class="col-sm-6">
                                    <input type="password" name="password"
                                           class="form-control ${(passwordError??)?string('is-invalid', '')}"
                                           placeholder="Пароль" />
                                    <#if passwordError??>
                                        <div class="invalid-feedback">
                                            ${passwordError}
                                        </div>
                                    </#if>
                                </div>
                            </div>
                            <input type="hidden" name="_csrf" value="${_csrf.token}" />
                            <button class="btn btn-primary" type="submit">Добавить</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div>
        <div class="row justify-content-center pb-4">
            <strong>
                Список администраторов:
            </strong>
        </div>
    </div>

    <table class="table table-sm">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Имя</th>
            <th scope="col">Статус администратора</th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <#list users! as user>
            <td>${user_index+1}</td>
            <td>${user.username}</td>
            <form method="post" action="update">
                <td>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" name="status" value="MAIN_ADMIN" id="flexCheckCheckedDisabled" <#if user.username=="d10.88@mail.ru">checked disabled<#elseif user.roles?seq_contains('MAIN_ADMIN')>checked</#if>>
                        <label class="form-check-label" for="flexCheckCheckedDisabled">
                            Главный администратор
                        </label>
                    </div>
                </td>
                <#if user.username!="d10.88@mail.ru">
                    <td>
                        <div>
                            <input type="hidden" name="_csrf" value="${_csrf.token}" />
                            <input type="hidden" name="id" value="${user.id?string["0"]}"/>
                            <button type="submit" class="btn btn-primary btn-sm">Изменить статус</button>
                        </div>
                    </td>
                </#if>
            </form>
            <#if user.username!="d10.88@mail.ru">
                <form method="post" action="delete">
                    <td>
                        <div>
                            <input type="hidden" name="_csrf" value="${_csrf.token}" />
                            <input type="hidden" name="id" value="${user.id?string["0"]}"/>
                            <button type="submit" class="btn btn-primary btn-sm">Удалить администратора</button>
                        </div>
                    </td>
                </form>
            </#if>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>