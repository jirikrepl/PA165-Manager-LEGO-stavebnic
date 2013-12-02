<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>


    <div class="form-group">
        <s:label for="b1" name="buildingKitTitle" class="col-sm-2 control-label"><f:message key="buildingkit.form.create.title"/></s:label>
        <div class="col-sm-4">
            <s:text class="form-control" id="b1" name="buildingKit.name"/>
        </div>
    </div>


    <div class="form-group">
        <s:label for="b2" name="buildingKitYearFrom" class="col-sm-2 control-label"><f:message key="buildingkit.form.create.yearfrom"/></s:label>
        <div class="col-sm-4">
            <s:text class="form-control" id="b2" name="buildingKit.yearFrom"/>
        </div>
    </div>

    <div class="form-group">
        <s:label for="b3" name="buildingKitPrice" class="col-sm-2 control-label"><f:message key="buildingkit.form.create.price"/></s:label>
        <div class="col-sm-4">
            <s:text class="form-control" id="b3" name="buildingKit.price"/>
        </div>
    </div>

<div class="form-group">
    <s:label for="b5" class="col-sm-2 control-label"><f:message key="buildingkit.categories"/></s:label>
    <div class="col-sm-4">
        <s:select class="form-control" id="b5" name="categoryId">
            <s:options-collection collection="${actionBean.categories}" value="id" label="name"/>
        </s:select>
    </div>
</div>

<div class="form-group">
    <s:label for="b6" class="col-sm-2 control-label"><f:message key="buildingkit.themesets"/></s:label>
    <div class="col-sm-4">
        <s:select class="form-control" id="b6" name="themesetId">
            <s:options-collection collection="${actionBean.themeSets}" value="id" label="name"/>
        </s:select>
    </div>
</div>

</ul>

