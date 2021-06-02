<#import "parts/common.ftlh" as c>
<#import "parts/editor.ftlh" as e>
<@c.page>
    <div class="row justify-content-center">
        <div class="col-sm-6">
            <div class="card border-secondary my-5">
                <div class="card-header">User Creation Form</div>
                <div class="card-body">
                    <form method="post" action="/user/new">
                        <@e.editor/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@c.page>