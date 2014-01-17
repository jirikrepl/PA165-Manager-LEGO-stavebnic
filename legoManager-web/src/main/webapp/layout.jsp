<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<s:layout-definition>

    <!DOCTYPE html>
    <html lang="${pageContext.request.locale}">
    <head>
        <title><f:message key="${titlekey}"/></title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css"/>
        <link rel="stylesheet" type="text/css"
              href="${pageContext.request.contextPath}/bootstrap/css/ourCustomStyle.css"/>
        <s:layout-component name="html-head"/>
    </head>

    <body>
    <div class="navbar navbar-inverse" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <a href="${pageContext.request.contextPath}" class="navbar-brand">Lego Manager</a>
            </div>
            <nav class="collapse navbar-collapse bs-navbar-collapse" role="navigation">
                <sec:authorize access="isAuthenticated()">
                    <ul class="nav navbar-nav">
                        <li>
                            <s:link beanclass="cz.muni.fi.PA165.action.CategoryActionBean"><f:message
                                    key="navigation.category"/></s:link>
                        </li>
                        <li>
                            <s:link beanclass="cz.muni.fi.PA165.action.ThemeSetActionBean"><f:message
                                    key="navigation.themeset"/></s:link>
                        </li>
                        <li>
                            <s:link beanclass="cz.muni.fi.PA165.action.BuildingKitActionBean"><f:message
                                    key="navigation.buildingkit"/></s:link>
                        </li>
                        <li>
                            <s:link beanclass="cz.muni.fi.PA165.action.BrickActionBean"><f:message
                                    key="navigation.brick"/></s:link>
                        </li>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <li>
                                <s:link beanclass="cz.muni.fi.PA165.action.AccountActionBean"><f:message
                                        key="navigation.account"/></s:link>
                            </li>
                        </sec:authorize>
                    </ul>

                <ul class="nav navbar-nav navbar-right">
                    <li class="user-info"><sec:authentication property="principal.username" /></li>
                        <li>
                            <button style="margin-top: 8px" type="submit" class="btn"
                                    onclick="location.href='${pageContext.request.contextPath}' + '/j_spring_security_logout';">
                                <f:message key="navigation.logOut"/>
                            </button>
                        </li>
                    </ul>
                </sec:authorize>

            </nav>
        </div>
        <s:layout-component name="header"/>
    </div>

    <div class="container">
        <s:messages/>
        <s:layout-component name="body"/>
    </div>
    </body>
    </html>
</s:layout-definition>