
<div class="container">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title" id="article-header">Add article<a class="anchorjs-link" href="#article-header"><span class="anchorjs-icon"></span></a></h3>
        </div>
        <div class="panel-body">
            <form action="/article/add" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <@spring.formHiddenInput "articleView.articleId", ""/>

                <!-- HEADER -->
                <div class="form-group">
                    <label for="articleView.header" >Header</label>
                    <@spring.formInput "articleView.header", "placeholder=\"Header\" class=\"form-control\"", "text"/>
                    <#list spring.status.errorMessages as error> <b>${error}</b> <br> </#list>
                </div>

                 <!-- SHORT DESCRIPTION -->
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title" id="article-desc">Short description<a class="anchorjs-link"  href="#article-desc"><span class="anchorjs-icon"></span></a></h3>
                    </div>
                    <div class="panel-body my-custom-textarea" >
                        <div class="form-group">
                            <@spring.formTextarea "articleView.description", "placeholder=\"Short description\" class=\"form-control text-area\""/>
                            <#list spring.status.errorMessages as error> <b>${error}</b> <br> </#list>
                        </div>
                    </div>
                </div>

                 <!-- TEXT -->
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title" id="article-text">Text<a class="anchorjs-link"  href="#article-text"><span class="anchorjs-icon"></span></a></h3>
                    </div>
                    <div class="panel-body my-custom-textarea" >
                        <div class="form-group">
                            <@spring.formTextarea "articleView.text", "placeholder=\"Text\" class=\"form-control text-area\""/>
                            <#list spring.status.errorMessages as error> <b>${error}</b> <br> </#list>
                        </div>
                    </div>
                </div>

                 <!-- AUTHORS -->
                <div class="form-group">
                    <@spring.formSingleSelect "articleView.authors", authors />
                </div>

                 <!-- KEYWORDS -->
                <div class="form-group">
                    <@spring.formSingleSelect "articleView.keywords", keywords />
                </div>

                <div class="form-group">
                    <input type="submit" value="Proceed" class="btn btn-primary pull-right" />
                </div>

            </form>
        </div>
    </div>
</div>