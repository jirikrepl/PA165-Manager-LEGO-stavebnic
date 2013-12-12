<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="brick.create.title">
    <s:layout-component name="body">

        <s:useActionBean beanclass="cz.muni.fi.PA165.action.BuildingKitActionBean" var="actionBean"/>
        <h1 class="text-center"><f:message key="buildingkit.addbrick.headline"/></h1>


        <s:form class="createForm form-horizontal" beanclass="cz.muni.fi.PA165.action.BuildingKitActionBean">
            <legend><f:message key="buildingkit.addbrick.legend"/></legend>

            <s:errors/>
            <s:param name="buildingKit.id" value="${actionBean.buildingKit.id}"/>
            <%--select box for brick--%>
            <div class="form-group">
                <s:label for="b1" class="col-sm-2 control-label"><f:message key="form.create.title"/></s:label>
                <div class="col-sm-4">
                    <s:select class="form-control" id="b1" name="brick.id">
                        <s:options-collection collection="${actionBean.bricks}" value="id" label="name"/>
                    </s:select>
                </div>
            </div>

            <%--input box for brick count--%>
            <div class="form-group">
                <s:label for="b1" class="col-sm-2 control-label"><f:message key="bricks.form.create.count"/></s:label>
                <div class="col-sm-4">
                    <s:text class="form-control" id="b2" name="brickCount"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-1">
                    <s:submit class="btn" name="saveBrickCount"><f:message key="buildingkit.addbrick.add"/></s:submit>
                </div>

                <div class="col-sm-offset-1 col-sm-3">
                    <s:link beanclass="cz.muni.fi.PA165.action.BuildingKitActionBean" event="list">
                        <f:message key="buildingkit.addbrick.cancel"/>
                    </s:link>
                </div>
            </div>
        </s:form>

        <table class="table">
            <tr>
                <th>id</th>
                <th><f:message key="table.brick.title"/></th>
                <th><f:message key="table.brick.count"/></th>
                <th><f:message key="table.options"/></th>
            </tr>

            <!--creates lines of table in loop-->
            <!--uses bricks from getter from action bean-->
            <c:forEach items="${actionBean.bricksSaved}" var="brickItem">
                <tr>
                    <td>${brickItem.key.id}</td>
                    <td>${brickItem.key.name}</td>
                    <td>${brickItem.value}</td>

                    <td>
                        <span>
                            <%--edit link--%>
                            <s:link class="btn anchor-to-button table-buttons" beanclass="cz.muni.fi.PA165.action.BuildingKitActionBean" event="openBrickCountEdit">
                                <s:param name="brick.id" value="${brickItem.key.id}"/>
                                <s:param name="buildingKit.id" value="${actionBean.buildingKit.id}"/>
                                <f:message key="brick.edit.button"/>
                            </s:link>

                            <%--delete button--%>
                            <s:form class="table-buttons" beanclass="cz.muni.fi.PA165.action.BuildingKitActionBean">
                                <s:param name="buildingKit.id" value="${action.buildingKit.id}"/>
                                <s:hidden name="brick.id" value="${brickItem.key.id}"/>
                                <s:submit class="btn" name="deleteBrick"><f:message key="buildingkit.addbrick.delete"/></s:submit>
                            </s:form>
                        </span>
                    </td>
                </tr>
            </c:forEach>
        </table>

    </s:layout-component>
</s:layout-render>
