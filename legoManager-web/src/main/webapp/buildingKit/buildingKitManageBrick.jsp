<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="brick.create.title">
    <s:layout-component name="body">

        <s:useActionBean beanclass="cz.muni.fi.PA165.action.BuildingKitActionBean" var="actionBean"/>
        <h1 class="text-center"><f:message key="buildingKit.create.headline"/></h1>


        <s:form beanclass="cz.muni.fi.PA165.action.BuildingKitActionBean">
            <s:hidden name="buildingKit.id" value="${buildingKit.id}" />

            <div class="col-sm-4">
                <s:select class="form-control" id="b4" name="brick.id">
                    <s:options-collection collection="${actionBean.bricks}" value="id" label="name"/>
                </s:select>
            </div>


            <s:submit class="btn" name="addBrick"><f:message key="buildingKit.add"/></s:submit>
        </s:form>


        <table class="table">
            <tr>
                <th>id</th>
                <th><f:message key="table.brick.title"/></th>
                <th><f:message key="table.brick.color"/></th>
                <th><f:message key="table.brick.count"/></th>
                <th><f:message key="table.options"/></th>
            </tr>

            <!--creates lines of table in loop-->
            <!--uses bricks from getter from action bean-->
            <c:forEach items="${actionBean.bricks}" var="brick">
                <tr>
                    <td>${brick.id}</td>
                    <td><c:out value="${brick.name}"/></td>
                    <td><f:message key="brick.color.${brick.color}"/></td>

                    <td><c:out value=""/></td>

                    <td>
                        <span>

                            <s:form class="table-buttons" beanclass="cz.muni.fi.PA165.action.BrickActionBean">
                                <s:hidden name="brick.id" value="${brick.id}"/>
                                <s:submit class="btn" name="delete"><f:message key="table.buttons.delete"/></s:submit>
                            </s:form>
                        </span>

                    </td>

                </tr>
            </c:forEach>
        </table>


    </s:layout-component>
</s:layout-render>
