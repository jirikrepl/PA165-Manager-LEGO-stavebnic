<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>

<!--first row == name-->
<!--uses bootstrap classes for setting width col-sm-2 -->
<div class="form-group">
    <s:label for="b1" class="col-sm-2 control-label"><f:message key="account.form.title"/></s:label>
    <div class="col-sm-4">
        <s:text class="form-control" id="b1" name="account.name"/>
        <!--this parameter brick.name is connected to actionBean private variable Brick-->
    </div>
</div>

<div class="form-group">
    <s:label for="b2" class="col-sm-2 control-label"><f:message key="account.form.password"/></s:label>
    <div class="col-sm-4">
        <s:password class="form-control" id="b2" name="account.password"/>
    </div>
</div>

<!-- second row -- option box-->
<div class="form-group">
    <s:label for="b4" class="col-sm-2 control-label"><f:message key="account.form.role"/></s:label>
    <div class="col-sm-4">
        <s:checkbox id="b4" name="account.isAdmin" />
    </div>
</div>

