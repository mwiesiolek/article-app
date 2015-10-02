
<div class="container">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title" id="article-header">Add article<a class="anchorjs-link" href="#article-header"><span class="anchorjs-icon"></span></a></h3>
        </div>
        <div class="panel-body">
            <form action="/article/add" method="post">
                <@spring.formHiddenInput "article.articleId", ""/>

                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title" id="page-content">${content_content}<a class="anchorjs-link"
                                                                                       href="#page-content"><span
                                class="anchorjs-icon"></span></a></h3>
                    </div>
                    <div class="panel-body my-custom-textarea" >
                        <div class="form-group">
                        <@spring.formTextarea "pageView.page.content", "placeholder=\"${content_content}\" class=\"form-control text-area\""/>
                        <#list spring.status.errorMessages as error> <b>${error}</b> <br> </#list>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="page.header" >${content_price}</label>
                <@spring.formInput "productView.product.price", "placeholder=\"${content_price}\" class=\"form-control\"", "text"/>
                    <#list spring.status.errorMessages as error> <b>${error}</b> <br> </#list>
                </div>

                <div class="form-group">
                <@spring.formSingleSelect "productView.product.currency", currencies />
                </div>

                <div class="form-group">
                    <input type="submit" value="Proceed" class="btn btn-primary pull-right" />
                </div>

            </form>
        </div>
    </div>
</div>