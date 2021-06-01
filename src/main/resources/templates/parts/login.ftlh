<#include "security.ftlh">
<#macro login>
    <form method="post" action="/login">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label" for="username">User Name:</label>
            <div class="col-sm-5 my-1">
                <input type="text" name="username" class="form-control ${(usernameError??)?string('is-invalid','')}"
                       placeholder="username" id="username" value="<#if userFromSession??>${userFromSession.username}</#if>"/>
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label" for="password">Password:</label>
            <div class="col-sm-5 my-1">
                <input type="password" name="password" class="form-control ${(passwordError??)?string('is-invalid','')}"
                       placeholder="password" id="password"/>
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-outline-info col-sm-2 my-1">
            Sign in
        </button>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-outline-info"><#if !userFromSession??>Sing In <#else>Sing Out</#if></button>
    </form>
</#macro>