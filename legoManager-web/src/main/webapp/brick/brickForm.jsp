<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<!--first row == name-->
<!--uses bootstrap classes for setting width col-sm-2 -->
<div class="form-group">
    <s:label for="b1" name="brickTitle" class="col-sm-2 control-label"/>
    <div class="col-sm-4">
        <s:text class="form-control" id="b1" name="brick.name"/> <!--this parameter brick.name is connected to actionBean private variable Brick-->
    </div>
</div>

<!--    second row -- option box-->
<div class="form-group">
    <s:label for="b4" name="brickColor" class="col-sm-2 control-label"/>
    <div class="col-sm-4">
        <s:select class="form-control" id="b4" name="brick.color"><s:options-enumeration enum="cz.muni.fi.PA165.api.service.Color"/></s:select>
    </div>
</div>    