<#if errorMsg??>
<div ng-controller="CloseableAlertController">
    <alert type="danger" close="close()">
        ${errorMsg}
    </alert>
</div>
</#if>

<#if success??>
<div ng-controller="CloseableAlertController">
    <alert type="success" close="close()">
        ${success}
    </alert>
</div>
</#if>

<#if notification??>
<div ng-controller="CloseableAlertController">
    <alert type="warning" close="close()">
        ${notification}
    </alert>
</div>
</#if>
