<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html ng-app="article">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<#if title??>
    <title>${title}</title>
</#if>

    <!--CSS-->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/carousel-theme.css" rel="stylesheet" />
    <link href="/css/main.css" rel="stylesheet" />
    <link href="/css/normalize.css" rel="stylesheet" />

    <!--JS-->
    <script src="/js/jquery-2.1.4.min.js"></script>
    <script src="/js/angular.js"></script>
    <script src="/js/ui-bootstrap-tpls-0.12.1.js"></script>
    <script src="/js/smart-table.min.js"></script>

    <script src="/js/common/main.js"></script>
    <script src="/js/common/closeable-alert-controller.js"></script>

    <!-- REST ANGULARJS SCRIPTS BELOW -->

    <script src="/js/article/article-controller.js"></script>
</head>
<body>
    <#include "alerts.ftl">

    <div id="webshop-body" class="container marketing">
        <#include "${bodyView}">
    </div>

</body>
</html>