<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<@c.page>
    <div class="row justify-content-center">
        <div class="col-sm-6">
            <div class="card border-secondary my-5">
                <div class="card-header">User Profile Editor</div>
                <div class="card-body">
                    <form method="post" action="/user/new">
                        <div class="form-group">
                            <label for="username">Username</label>
                            <input type="text" class="form-control ${(usernameError??)?string('is-invalid','')}"
                                   name="username" placeholder="Username" id="username"
                                   value="<#if userAccount??>${userAccount.username}</#if>"/>
                            <#if usernameError??>
                                <div class="invalid-feedback">
                                    ${usernameError}
                                </div>
                            </#if>
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" class="form-control ${(passwordError??)?string('is-invalid','')}"
                                   name="password" placeholder="Password" id="password">
                            </input>
                            <#if passwordError??>
                                <div class="invalid-feedback">
                                    ${passwordError}
                                </div>
                            </#if>
                        </div>
                        <div class="form-group">
                            <label for="firstName">First Name</label>
                            <input type="text" class="form-control ${(firstNameError??)?string('is-invalid','')}"
                                   name="firstName" placeholder="First Name" id="firstName"
                                   value="<#if userAccount??>${userAccount.firstName}</#if>"/>
                            <#if firstNameError??>
                                <div class="invalid-feedback">
                                    ${firstNameError}
                                </div>
                            </#if>
                        </div>
                        <div class="form-group">
                            <label for="lastName">Last Name</label>
                            <input type="text" class="form-control ${(lastNameError??)?string('is-invalid','')}"
                                   name="lastName" placeholder="Last Name" id="lastName"
                                   value="<#if userAccount??>${userAccount.lastName}</#if>"/>
                            <#if lastNameError??>
                                <div class="invalid-feedback">
                                    ${lastNameError}
                                </div>
                            </#if>
                        </div>
                        <div class="form-group">
                            <label for="role">Role</label>
                            <select class="form-control" id="role" name="role">
                                <option>USER</option>
                                <option>ADMIN</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="status">Status</label>
                            <select class="form-control" id="status" name="status">
                                <option>ACTIVE</option>
                                <option>INACTIVE</option>
                            </select>
                        </div>
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <div class="form-group">
                            <button type="submit" class="btn btn-outline-secondary">Save</button>
                            <a class="btn btn btn-outline-secondary" href="/">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@c.page>