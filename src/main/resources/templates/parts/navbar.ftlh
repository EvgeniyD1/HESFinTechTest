<#include "security.ftlh">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <a class="navbar-brand my-0 h1" href="/">
        <span class="text-white-50">HESFinTechTest</span>
    </a>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item ml-3">
                <a class="nav-link" href="/list">All Users</a>
            </li>
            <#if isAdmin>
                <li class="nav-item ml-3">
                    <a class="nav-link" href="/user/new">Create User</a>
                </li>
            </#if>
        </ul>
        <ul class="navbar-nav ml-auto">
            <li>
                <#if !name??>
                <#else>
                    <a class="nav-link" href="/user/${cerrentUser}">${name}</a>
                </#if>
            </li>
            <li>
                <form action="/logout" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-outline-info">
                        <#if !userFromSession??>Sing In <#else>Sing Out</#if>
                    </button>
                </form>
            </li>
        </ul>
    </div>
</nav>