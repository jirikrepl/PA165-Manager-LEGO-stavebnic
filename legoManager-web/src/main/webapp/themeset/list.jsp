<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<s:layout-render name="/layout.jsp" titlekey="themeset.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.PA165.action.ThemeSetActionBean" var="actionBean"/>
        <h1 class="text-center"><f:message key="themeset.headline"/></h1>

        <sec:authorize access="hasRole('ROLE_ADMIN')">
        <s:form class="createForm form-horizontal" id="themesetCreateForm" beanclass="cz.muni.fi.PA165.action.ThemeSetActionBean">
            <legend><f:message key="themeset.create.title"/></legend>
            <%@include file="form.jsp"%>

            <!--submit button, in bootstrap div class, see documentation for bootstrap forms-->
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-4">
                    <s:submit class="btn" name="createThemeSet"><f:message key="themeset.create.button"/></s:submit>
                </div>
            </div>
        </s:form>
        </sec:authorize>
            
        <table class="table">
            <tr>
                <th>id</th>
                <th><f:message key="table.themeset.name"/></th>
                <th><f:message key="table.themeset.price"/></th>
                <th><f:message key="table.themeset.category"/></th>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                <th><f:message key="table.options"/></th>
                </sec:authorize>
            </tr>

            <c:forEach items="${actionBean.themeSets}" var="themeSet">
                <tr>
                    <td>${themeSet.id}</td>
                    <td><c:out value="${themeSet.name}"/></td>
                    <td><c:out value="${themeSet.price}"/> Kč</td>
                    <td><c:out value="${themeSet.categoryDto.name}"/></td>
                    <td>
                        <span>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <!--table buttons-->
                            <!--edit-->
                            <s:form class="table-buttons" beanclass="cz.muni.fi.PA165.action.ThemeSetActionBean">
                                <s:hidden name="themeSetDto.id" value="${themeSet.id}"/>
                                <s:hidden name="themeSetDto.name" value="${themeSet.name}"/>
                                <s:hidden name="themeSetDto.price" value="${themeSet.price}"/>
                                <s:submit class="btn" name="openEditPage"><f:message key="themeset.edit.button"/></s:submit>
                            </s:form>

                            <!--delete-->
                            <s:form class="table-buttons" beanclass="cz.muni.fi.PA165.action.ThemeSetActionBean">
                                <s:hidden name="themeSetDto.id" value="${themeSet.id}"/>
                                <s:submit class="btn" name="deleteThemeSet"><f:message key="themeset.delete.button"/></s:submit>
                            </s:form>
                            </sec:authorize>
                        </span>
                    </td>
                    
                </tr>
            </c:forEach>
        </table>
            
            
        </s:layout-component>
</s:layout-render>