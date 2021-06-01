<#import "parts/common.ftlh" as c>
<#import "parts/pagination.ftlh" as p>
<@c.page>
    <form class="form-inline mb-3" method="get" action="/list">
        <label>
            <input type="search" name="username" class="form-control" placeholder="Username"
                   value="${username!}"/>
        </label>
        <label class="ml-2">
            <input type="search" name="role" class="form-control" placeholder="Role"
                   value="${role!}"/>
        </label>
        <button type="submit" class="btn btn-outline-info ml-2">Find User</button>
    </form>
    <@p.pagination url users/>
    <table class="table" id="user-data">
        <thead class="thead-light">
            <tr>
                <th scope="col">#</th>
                <th scope="col">Username</th>
                <th scope="col">First Name</th>
                <th scope="col">Last Name</th>
                <th scope="col">Role</th>
                <th scope="col">Status</th>
                <th scope="col">Created At</th>
            </tr>
        </thead>
        <tbody>
        <#list users.content as user>
            <tr data-id="${user.id}">
                <th scope="row">${user.id}</th>
                <td>
                    <a href="/user/${user.id}">${user.username}</a>
                </td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.role}</td>
                <td>${user.status}</td>
                <td>${user.createdAt}</td>
            </tr>
        </#list>
        </tbody>
    </table>
    <#if users.content?size!=0>
        <@p.pagination url users/>
    </#if>
</@c.page>