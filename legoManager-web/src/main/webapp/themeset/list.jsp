<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="themeset.title">
    <s:layout-component name="header">
        
    </s:layout-component>
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.PA165.action.ThemeSetActionBean" var="actionBean"/>
            <h1 class="text-center"><f:message key="themeset.headline"/></h1>
            
            <table class="table">
            <tr>
                <th>id</th>
                <th><f:message key="table.brick.name"/></th>
                <th><f:message key="table.brick.price"/></th>
                <th><f:message key="table.brick.description"/></th>
                <th><f:message key="table.category"/></th>
            </tr>
            
            <!--creates lines of table in loop-->
            <!--uses bricks from getter from action bean-->
            <c:forEach items="${actionBean.themeSets}" var="dto">
                <tr>
                    <td>${dto.id}</td>
                    <td><c:out value="${dto.name}"/></td>
                    <td><c:out value=""/></td>
                    <td><c:out value=""/></td>
                    <td><c:out value=""/></td>
                </tr>
            </c:forEach>
        </table>
            
            
        </s:layout-component>
</s:layout-render>