<#import "login.ftlh" as l>
<nav class="navbar navbar-expand-lg bg-secondary navbar-light bg-light">
    <a class="navbar-brand" href="/">
        <h1>
            BelgorodСуд31.info
        </h1>
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="container-fluid">
        <span class="navbar-text row justify-content-end">
        <div>
        <div>
            <strong>
                <h5>
                    Белгородский портал для удобного поиска судебного участка мирового суда
                </h5>
            </strong>
        </div>
        </div>
    </span>
    </div>
    <div class="form-group">
        <@l.logout/>
    </div>
</nav>



