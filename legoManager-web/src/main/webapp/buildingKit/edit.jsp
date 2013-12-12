<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="buildingKit.edit.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.PA165.action.BuildingKitActionBean" var="actionBean"/>
        <h1 class="text-center"><f:message key="buildingKit.headline"/></h1>

        <!--form for brick creation-->
        <!--there is included file with rows in this form-->

        <s:form class="createForm form-horizontal" beanclass="cz.muni.fi.PA165.action.BuildingKitActionBean">
            <s:param name="buildingKit.id" value="${actionBean.buildingKit.id}"/>
            <legend><f:message key="buildingKit.create"/></legend>
            <%@include file="buildingKitForm.jsp" %>


            <!--submit button, in bootstrap div class, see documentation for bootstrap forms-->
            <div class="form-group">
                <span class="col-sm-offset-2 col-sm-1">
                    <s:submit class="btn" name="updateBuildingKit"><f:message key="buildingKit.edit.button"/></s:submit>
                    </span>
                    
                    <span class="col-sm-offset-1 col-sm-3">
                        <s:link beanclass="cz.muni.fi.PA165.action.BuildingKitActionBean"
                                event="list">
                            <f:message key="buildingKit.cancel.button"/>
                        </s:link>
                    </span>
            </div>
        </s:form>

    </s:layout-component>
</s:layout-render>