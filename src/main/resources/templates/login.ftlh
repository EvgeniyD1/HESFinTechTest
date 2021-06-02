<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<@c.page>
    <#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
        <div class="alert alert-danger">
            ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
        </div>
    </#if>
    <form method="post" action="/login">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label" for="username">User Name:</label>
            <div class="col-sm-5 my-1">
                <input type="text" name="username" class="form-control" placeholder="username" id="username"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label" for="password">Password:</label>
            <div class="col-sm-5 my-1">
                <input type="password" name="password" class="form-control" placeholder="password" id="password"/>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-outline-info col-sm-2 my-1">
            Sign in
        </button>
    </form>
</@c.page>