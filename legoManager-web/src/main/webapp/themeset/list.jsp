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
                <th><f:message key="table.themeset.name"/></th>
                <th><f:message key="table.themeset.price"/></th>
                <th><f:message key="table.themeset.description"/></th>
                <th><f:message key="table.themeset.category"/></th>
            </tr>

            <c:forEach items="${actionBean.themeSets}" var="themeSet">
                <tr>
                    <td>${themeSet.id}</td>
                    <td><c:out value="${themeSet.name}"/></td>
                    <td><c:out value="${themeSet.name}"/></td>
                    <td><c:out value="${themeSet.name}"/></td>
                    <td><c:out value="${themeSet.name}"/></td>
                </tr>
            </c:forEach>
        </table>
            
            
        </s:layout-component>
</s:layout-render>