<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>


<div class="form-group">
    <s:label for="b1" class="col-sm-2 control-label"><f:message key="themeset.create.name"/></s:label>
    <div class="col-sm-4">
        <s:text class="form-control" id="b1" name="dto.name"/> <!--this parameter categoryDto.name is connected to actionBean private variable Brick-->
    </div>
</div>

<!--    second row -- option box-->
<div class="form-group">
    <s:label for="b1" class="col-sm-2 control-label"><f:message key="themeset.create.price"/></s:label>
    <div class="col-sm-4">
        <s:text class="form-control" id="b1" name="dto.description"/>
    </div>
</div>  
<div class="form-group">
    <s:label for="b1" class="col-sm-2 control-label"><f:message key="themeset.create.description"/></s:label>
    <div class="col-sm-4">
        <s:text class="form-control" id="b1" name="dto.description"/>
    </div>
</div>
<div class="form-group">
    <s:label for="b1" class="col-sm-2 control-label"><f:message key="themeset.create.category"/></s:label>
    <div class="col-sm-4">
        <s:text class="form-control" id="b1" name="dto.description"/>
    </div>
</div>
