<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<s:layout-render name="/layout.jsp" titlekey="buildingKit.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.PA165.action.BuildingKitActionBean" var="actionBean"/>
        <h1 class="text-center"><f:message key="buildingKit.headline"/></h1>

        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <s:form class="createForm form-horizontal" id="brickCreateForm"
                    beanclass="cz.muni.fi.PA165.action.BuildingKitActionBean">
                <s:param name="buildingKit.id" value="${actionBean.buildingKit.id}"/>
                <legend><f:message key="buildingKit.createForm"/></legend>
                <%@include file="buildingKitForm.jsp" %>

                <!--submit button, in bootstrap div class, see documentation for bootstrap forms-->
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-4">
                        <s:submit class="btn" name="createBuildingKit"><f:message key="buildingKit.create"/></s:submit>
                    </div>
                </div>
            </s:form>
        </sec:authorize>


        <table class="table">
            <tr>
                <th>id</th>
                <th><f:message key="table.buildingKit.name"/></th>
                <th><f:message key="table.buildingKit.yearfrom"/></th>
                <th><f:message key="table.buildingKit.price"/></th>
                <th><f:message key="table.buildingKit.category"/></th>
                <th><f:message key="table.buildingKit.themeSet"/></th>
                <th><f:message key="table.options"/></th>
            </tr>

            <!--creates lines of table in loop-->
            <!--uses bricks from getter from action bean-->
            <c:forEach items="${actionBean.buildingKits}" var="buildingKit">
                <tr>
                    <td>${buildingKit.id}</td>
                    <td>${buildingKit.name}</td>
                    <td>${buildingKit.yearFrom}</td>
                    <td>${buildingKit.price}</td>
                    <td>${buildingKit.category.name}</td>
                    <td>${buildingKit.themeSet.name}</td>

                    <!--table buttons-->
                    <td>
                        <span>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <%--building kit edit button--%>
                            <s:form class="table-buttons" beanclass="cz.muni.fi.PA165.action.BuildingKitActionBean">
                                <s:hidden name="buildingKit.id" value="${buildingKit.id}"/>
                                <s:submit class="btn" name="openEditPage"><f:message
                                        key="buildingKit.edit.button"/></s:submit>
                            </s:form>

                            <%--brick manager button--%>
                            <s:link class="btn anchor-to-button table-buttons"
                                    beanclass="cz.muni.fi.PA165.action.BuildingKitActionBean"
                                    event="openManageBrickPage">
                                <s:param name="buildingKit.id" value="${buildingKit.id}"/>
                                <f:message key="buildingKit.bricks.button"/>
                            </s:link>

                            <%--delete building kit button--%>
                            <s:form class="table-buttons" beanclass="cz.muni.fi.PA165.action.BuildingKitActionBean">
                                <s:hidden name="buildingKit.id" value="${buildingKit.id}"/>
                                <s:submit class="btn" name="delete"><f:message key="table.buttons.delete"/></s:submit>
                            </s:form>
                            </sec:authorize>
                        </span>

                    </td>

                </tr>
            </c:forEach>
        </table>

    </s:layout-component>
</s:layout-render>
