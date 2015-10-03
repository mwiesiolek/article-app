<script src="/js/article/article-controller.js"></script>
<script src="/js/article/article-filter.js"></script>
<script src="/js/article/article-directive.js"></script>

<div class="container" ng-controller="ArticlePipeController">

    <div class="panel panel-primary">
        <div class="panel-body">
            <h3>Time zone: GMT</h3>
        </div>
    </div>

    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title" id="article-table">Articles<a class="anchorjs-link" href="#article-table"><span
                    class="anchorjs-icon"></span></a></h3>
        </div>
        <div class="panel-body">
            <a href="/article/add" title="Add article" class="btn btn-primary pull-right" style="margin:0 0 10px 0;">
                Add article
            </a>

            <table st-table="data" class="table table-striped" st-pipe="callServer">
                <thead>
                    <tr>
                        <th colspan="1">
                            <input st-search="authorFirstName" class="form-control" placeholder="Search by first name" type="text"/>
                        </th>
                        <th colspan="1">
                            <input st-search="authorSurname" class="form-control" placeholder="Search by surname" type="text"/>
                        </th>
                        <th colspan="1">
                            <input st-search="startDate"
                                   class="form-control"
                                   placeholder="Search by start date"
                                   type="text"
                                   datepicker-popup="{{format}}"
                                   ng-model="startDate"
                                   ng-click="sopen()"
                                   is-open="status.sopened"
                                   min-date="minDate"
                                   max-date="maxDate"
                                   ng-required="true"
                                   refresh-table
                                   close-text="Close" />
                        </th>
                        <th colspan="1">
                            <input st-search="endDate"
                                   class="form-control"
                                   placeholder="Search by end date"
                                   type="text"
                                   datepicker-popup="{{format}}"
                                   ng-model="endDate"
                                   ng-click="eopen()"
                                   is-open="status.eopened"
                                   min-date="minDate"
                                   max-date="maxDate"
                                   ng-required="true"
                                   refresh-table
                                   close-text="Close" />
                        </th>
                        <th colspan="2">
                            <input st-search="keyword" class="form-control" placeholder="Search by keyword" type="text"/>
                        </th>
                        <th><!-- ACTIONS --></th>
                    </tr>
                    <tr>
                        <th>#</th>
                        <th>Header</th>
                        <th>Short Description</th>
                        <th>Publish date</th>
                        <th>Authors</th>
                        <th>Keywords</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody ng-show="!isLoading">
                    <tr ng-repeat="article in data">
                        <td>{{article.articleId}}</td>
                        <td>{{article.header}}</td>
                        <td>{{article.description}}</td>
                        <td>{{article.date}}</td>
                        <td>{{article.authors | authors}}</td>
                        <td>{{article.keywords | keywords}}</td>
                        <td>
                            <p>
                                <a href="/article/edit?id={{article.articleId}}" title="Edit">Edit</a>
                            </p>

                            <p ng-controller="RemoveController">
                                <a href="/article/remove?id={{article.articleId}}" title="Remove"
                                   ng-click="confirm('Are you sure?', $event)">Remove</a>
                            </p>
                        </td>
                    </tr>
                </tbody>
                <tbody ng-show="isLoading">
                    <tr>
                        <td colspan="6" class="text-center">Loading ...</td>
                    </tr>
                </tbody>
                <tfoot ng-show="!isLoading">
                    <tr>
                        <td class="text-center" st-pagination="" st-items-by-page="10" colspan="7">
                        </td>
                    </tr>
                </tfoot>
            </table>

            <a href="/article/add" title="Add article" class="btn btn-primary pull-right">
                Add article
            </a>
        </div>
    </div>
</div>