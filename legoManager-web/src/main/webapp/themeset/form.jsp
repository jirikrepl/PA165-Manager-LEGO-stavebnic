<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>

<s:useActionBean beanclass="cz.muni.fi.PA165.action.ThemeSetActionBean" var="actionBean"/>

<div class="form-group">
    <s:label for="b1" class="col-sm-2 control-label"><f:message key="themeset.create.name"/></s:label>
        <div class="col-sm-4">
        <s:text class="form-control" id="b1" name="themeSetDto.name"/>
    </div>
</div>

<div class="form-group">
    <s:label for="b1" class="col-sm-2 control-label"><f:message key="themeset.create.price"/></s:label>
        <div class="col-sm-4">
        <s:text class="form-control" id="b1" name="themeSetDto.price"/>
    </div>
</div>

<div class="form-group">
    <s:label for="b1" class="col-sm-2 control-label"><f:message key="themeset.categories"/></s:label>
        <div class="col-sm-4">
        <s:select class="form-control" id="b1" name="themeSetDto.categoryDto.id">
            <s:options-collection collection="${actionBean.categories}" value="id" label="name"/>
        </s:select>
    </div>
</div>
