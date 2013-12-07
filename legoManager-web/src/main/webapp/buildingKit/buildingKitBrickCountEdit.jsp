<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="brick.count.edit">
    <s:layout-component name="body">

        <s:useActionBean beanclass="cz.muni.fi.PA165.action.BuildingKitActionBean" var="actionBean"/>
        <h1 class="text-center"><f:message key="buildingkit.addbrick.editCount"/></h1>


        <s:form class="createForm form-horizontal" beanclass="cz.muni.fi.PA165.action.BuildingKitActionBean">
            <legend><f:message key="buildingkit.addbrick.editCount"/></legend>
            <s:hidden name="buildingKit.id" value="${buildingKit.id}"/>

            <div class="form-group">
                <s:label for="b1" class="col-sm-2 control-label"><f:message key="bricks.form.create.count"/></s:label>
                <div class="col-sm-4">
                    <s:text class="form-control" id="b2" name="brickCount" value="${brickItem.key.value}"/>
                </div>
            </div>

            <%--save and cancel buttons--%>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-1">
                    <s:submit class="btn" name="saveEditedBrickCount"><f:message key="themeset.button.save"/></s:submit>
                </div>

                <div class="col-sm-offset-1 col-sm-3">
                    <s:submit class="btn" name="openManageBrickPage"><f:message key="buildingkit.addbrick.cancel"/></s:submit>
                </div>
            </div>
        </s:form>

    </s:layout-component>
</s:layout-render>