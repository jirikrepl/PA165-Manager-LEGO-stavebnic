<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>


<div class="form-group">
    <s:label for="b1" name="category.name" class="col-sm-2 control-label"/>
    <div class="col-sm-4">
        <s:text class="form-control" id="b1" name="categoryDto.name"/> <!--this parameter categoryDto.name is connected to actionBean private variable Brick-->
    </div>
</div>

<!--    second row -- option box-->
<div class="form-group">
    <s:label for="b1" name="category.description" class="col-sm-2 control-label"/>
    <div class="col-sm-4">
        <s:text class="form-control" id="b1" name="categoryDto.description"/>
    </div>
</div>  
