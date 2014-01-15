<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<s:layout-render name="/layout.jsp" titlekey="brick.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.PA165.action.BrickActionBean" var="actionBean"/>
        <h1 class="text-center"><f:message key="bricks.headline"/></h1>

        <!--form for brick creation-->
        <!--there is included file with rows in this form-->
        <sec:authorize access="hasRole('ROLE_ADMIN')">
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
        </sec:authorize>

        <!--table of bricks-->
        <table class="table">
            <tr>
                <th>id</th>
                <th><f:message key="table.brick.title"/></th>
                <th><f:message key="table.brick.color"/></th>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                <th><f:message key="table.options"/></th>
                </sec:authorize>
            </tr>

            <!--creates lines of table in loop-->
            <!--uses bricks from getter from action bean-->
            <c:forEach items="${actionBean.bricks}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td><c:out value="${user.name}"/></td>
                    <td><f:message key="Color.${user.color}"/></td>
                    <%--<td><c:out value=""/></td>--%>

                    <td>
                        <span>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <!--table buttons-->
                            <s:form class="table-buttons" beanclass="cz.muni.fi.PA165.action.BrickActionBean">
                                <s:hidden name="brick.id" value="${user.id}"/>
                                <s:hidden name="brick.name" value="${user.name}"/>
                                <s:hidden name="brick.color" value="${user.color}"/>
                                <s:submit class="btn" name="openEditPage"><f:message key="brick.edit.button"/></s:submit>
                            </s:form>

                            <s:form class="table-buttons" beanclass="cz.muni.fi.PA165.action.BrickActionBean">
                                <s:hidden name="brick.id" value="${user.id}"/>
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