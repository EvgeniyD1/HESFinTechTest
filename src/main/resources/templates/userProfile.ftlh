<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<@c.page>
    <div class="row m-5">
        <div class="col-sm-6">
            <div class="card border-secondary mb-3 ">
                <div class="card-header">User Profile</div>
                <div class="card-body">
                    <div class="row" id="username">
                        <div class="p-2" style="width: 200px">Username</div>
                        <div class="p-2">${user.username}</div>
                    </div>
                    <div class="row" id="firstName">
                        <div class="p-2" style="width: 200px">First Name</div>
                        <div class="p-2">${user.firstName}</div>
                    </div>
                    <div class="row" id="lastName">
                        <div class="p-2" style="width: 200px">Last Name</div>
                        <div class="p-2">${user.lastName}</div>
                    </div>
                    <div class="row" id="role">
                        <div class="p-2" style="width: 200px">Role</div>
                        <div class="p-2">${user.role}</div>
                    </div>
                    <div class="row" id="status">
                        <div class="p-2" style="width: 200px">Status</div>
                        <div class="p-2">${user.status}</div>
                    </div>
                    <div class="row" id="createdAt">
                        <div class="p-2" style="width: 200px">Created At</div>
                        <div class="p-2">${user.createdAt}</div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-6">
            <#if isAdmin>
                <a class="btn btn btn-outline-secondary" href="/user/${user.id}/edit">Edit</a>
            </#if>
        </div>
    </div>
</@c.page>