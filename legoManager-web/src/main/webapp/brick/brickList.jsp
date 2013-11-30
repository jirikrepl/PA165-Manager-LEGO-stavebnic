<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="brick.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.PA165.action.BrickActionBean" var="actionBean"/>
        <h1 class="text-center"><f:message key="bricks.headline"/></h1>

        <!--form for brick creation-->
        <!--there is included file with rows in this form-->
        <s:form class="createForm form-horizontal" beanclass="cz.muni.fi.PA165.action.BrickActionBean">
            <legend><f:message key="brick.create.title"/></legend>
            <%@include file="brickForm.jsp"%>

            <!--submit button, in bootstrap div class, see documentation for bootstrap forms-->
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-4">
                    <s:submit class="btn" name="createBrick"><f:message key="brick.create"/></s:submit>
                    </div>
                </div>
        </s:form>

        <!--table of bricks-->
        <table class="table">
            <tr>
                <th>id</th>
                <th><f:message key="table.brick.title"/></th>
                <th><f:message key="table.brick.color"/></th>
                <%--<th><f:message key="table.brick.count"/></th>--%>
                <th><f:message key="table.options"/></th>
            </tr>

            <!--creates lines of table in loop-->
            <!--uses bricks from getter from action bean-->
            <c:forEach items="${actionBean.bricks}" var="brick">
                <tr>
                    <td>${brick.id}</td>
                    <td><c:out value="${brick.name}"/></td>
                    <td><f:message key="Color.${brick.color}"/></td>
                    <%--<td><c:out value=""/></td>--%>

                    <td>
                        <span>
                            <!--table buttons-->
                            <s:form class="table-buttons" beanclass="cz.muni.fi.PA165.action.BrickActionBean">
                                <s:hidden name="brick.id" value="${brick.id}"/>
                                <s:hidden name="brick.name" value="${brick.name}"/>
                                <s:hidden name="brick.color" value="${brick.color}"/>
                                <s:submit class="btn" name="openEditPage"><f:message key="brick.edit.button"/></s:submit>
                            </s:form>

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