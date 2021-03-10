<#assign
known = Session.SPRING_SECURITY_CONTEXT??
>
<#if known>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    isMainAdmin = user.isMainAdmin()
    userId=user.getId()
    >
<#else>
    <#assign
    isMainAdmin = false
    userId=-1
    >
</#if>