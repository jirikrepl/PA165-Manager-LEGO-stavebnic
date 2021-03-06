<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>


<div class="form-group">
    <s:label for="b1" class="col-sm-2 control-label"><f:message key="category.create.name"/></s:label>
    <div class="col-sm-4">
        <s:text class="form-control" id="b1" name="categoryDto.name"/> <!--this parameter categoryDto.name is connected to actionBean private variable Brick-->
    </div>
</div>

<!--    second row -- option box-->
<div class="form-group">
    <s:label for="b1" class="col-sm-2 control-label"><f:message key="category.create.description"/></s:label>
    <div class="col-sm-4">
        <s:text class="form-control" id="b1" name="categoryDto.description"/>
    </div>
</div>  