<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="category.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.PA165.action.CategoryActionBean" var="actionBean"/>
        <h1 class="text-center"><f:message key="category.headline"/></h1>


        <s:form class="form-horizontal" beanclass="cz.muni.fi.PA165.action.CategoryActionBean">
            <fieldset><legend><f:message key="category.create"/></legend>
                <%@include file="form.jsp"%>
                <div class="form-group">
                <div class="col-sm-offset-2 col-sm-4">
                    <s:submit class="btn" name="createCategory"><f:message key="category.create"/></s:submit>
                    </div>
                </div>
            </fieldset>
        </s:form>

        <table class="table">
            <tr>
                <th>id</th>
                <th><f:message key="table.category.name"/></th>
                <th><f:message key="table.category.description"/></th>
                <th><f:message key="table.options"/></th>
            </tr>

            <!--creates lines of table in loop-->
            <!--uses bricks from getter from action bean-->
            <c:forEach items="${actionBean.categories}" var="category">
                <tr>
                    <td>${category.id}</td>
                    <td>${category.name}</td>
                    <td>${category.description}</td>

                    <td><c:out value=""/></td>

                    <td>
                        <s:link beanclass="cz.muni.fi.PA165.action.CategoryActionBean" event="edit"><s:param name="categoryDto.id" value="${category.id}"/>edit</s:link>
                        <s:form beanclass="cz.muni.fi.PA165.action.CategoryActionBean">
                            <s:errors/>
                            <s:hidden name="categoryDto.id" value="${category.id}"/>
                            <s:submit name="delete"><f:message key="table.buttons.delete"/></s:submit>
                        </s:form>
                    </td>

                </tr>
            </c:forEach>
        </table>

    </s:layout-component>
</s:layout-render>
