<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="users.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.PA165.action.AccountActionBean" var="actionBean"/>
        <h1 class="text-center"><f:message key="acounts.headline"/></h1>

        <!--form for brick creation-->
        <!--there is included file with rows in this form-->
        <s:form class="createForm form-horizontal" beanclass="cz.muni.fi.PA165.action.AccountActionBean">
            <legend><f:message key="acounts.create.title"/></legend>
            <%@include file="usersForm.jsp"%>

            <!--submit button, in bootstrap div class, see documentation for bootstrap forms-->
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-4">
                    <s:submit class="btn" name="createAccount"><f:message key="acounts.create"/></s:submit>
                </div>
            </div>
        </s:form>

        <!--table of accounts-->
        <table class="table">
            <tr>
                <th>id</th>
                <th><f:message key="table.acounts.name"/></th>
                <th><f:message key="admintablerole"/></th>
                <th><f:message key="table.options"/></th>
            </tr>

            <!--creates lines of table in loop-->
            <!--uses bricks from getter from action bean-->
            <c:forEach items="${actionBean.accounts}" var="accounts">
                <tr>
                    <td>${accounts.id}</td>
                    <td>${accounts.name}</td>
                    <td><f:message key="account.isAdmin.${accounts.isAdmin}"/></td>

                    <td>
                        <span>
                            <!--table buttons-->
                            <s:form class="table-buttons" beanclass="cz.muni.fi.PA165.action.AccountActionBean">
                                <s:hidden name="account.id" value="${accounts.id}"/>
                                <s:hidden name="account.name" value="${accounts.name}"/>
                                <s:hidden name="account.isAdmin" value="${accounts.isAdmin}"/>
                                <s:submit class="btn" name="openEditPage"><f:message key="brick.edit.button"/></s:submit>
                            </s:form>

                            <s:form class="table-buttons" beanclass="cz.muni.fi.PA165.action.AccountActionBean">
                                <s:hidden name="account.id" value="${accounts.id}"/>
                                <s:submit class="btn" name="delete"><f:message key="table.buttons.delete"/></s:submit>
                            </s:form>
                        </span>

                    </td>

                </tr>
            </c:forEach>
        </table>

    </s:layout-component>
</s:layout-render>