
<div class="container">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title" id="article-header">Add article<a class="anchorjs-link" href="#article-header"><span class="anchorjs-icon"></span></a></h3>
        </div>
        <div class="panel-body">
            <form action="/article/${method}" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <@spring.formHiddenInput "articleView.articleId", ""/>

                <!-- HEADER -->
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title" id="article-header">Header<a class="anchorjs-link" href="#article-header"><span class="anchorjs-icon"></span></a></h3>
                    </div>
                    <div class="panel-body">
                        <div class="form-group">
                            <@spring.formInput "articleView.header", "placeholder=\"Header\" class=\"form-control\"", "text"/>
                        </div>
                    </div>
                </div>

                 <!-- SHORT DESCRIPTION -->
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title" id="article-desc">Short description<a class="anchorjs-link"  href="#article-desc"><span class="anchorjs-icon"></span></a></h3>
                    </div>
                    <div class="panel-body" >
                        <div class="form-group">
                            <@spring.formTextarea "articleView.description", "placeholder=\"Short description\" class=\"form-control text-area\""/>
                        </div>
                    </div>
                </div>

                 <!-- TEXT -->
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title" id="article-text">Text<a class="anchorjs-link"  href="#article-text"><span class="anchorjs-icon"></span></a></h3>
                    </div>
                    <div class="panel-body" >
                        <div class="form-group">
                            <@spring.formTextarea "articleView.text", "placeholder=\"Text\" class=\"form-control text-area\""/>
                        </div>
                    </div>
                </div>

                 <!-- AUTHORS -->
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title" id="article-authors">Authors<a class="anchorjs-link" href="#article-authors"><span class="anchorjs-icon"></span></a></h3>
                    </div>
                    <div class="panel-body">
                        <div class="form-group">
                            <#list articleView.authors?keys as key>
                                <@spring.formHiddenInput "articleView.authors[${key}].authorId", ""/>
                                <@spring.formHiddenInput "articleView.authors[${key}].signature", ""/>
                                <label for="authors${key}.selected">
                                    <@spring.formCheckbox "articleView.authors[${key}].selected", "" /> ${articleView.authors[key].signature}
                                </label>
                            </#list>
                        </div>
                    </div>
                </div>

                 <!-- KEYWORDS -->
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title" id="article-keyword">Keywords<a class="anchorjs-link" href="#article-keyword"><span class="anchorjs-icon"></span></a></h3>
                    </div>
                    <div class="panel-body">
                        <div class="form-group">
                            <#list articleView.keywords?keys as key>
                                <@spring.formHiddenInput "articleView.keywords[${key}].keywordId", ""/>
                                <@spring.formHiddenInput "articleView.keywords[${key}].word", ""/>
                                <label for="keywords${key}.selected">
                                    <@spring.formCheckbox "articleView.keywords[${key}].selected", "" /> ${articleView.keywords[key].word}
                                </label>
                            </#list>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <input type="submit" value="Proceed" class="btn btn-primary pull-right" />
                </div>

            </form>
        </div>
    </div>
</div>