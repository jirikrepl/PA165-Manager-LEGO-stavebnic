<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="brick.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.PA165.action.BrickActionBean" var="actionBean"/>
        <h1 class="text-center"><f:message key="bricks.headline"/></h1>

        <table class="table">
            <tr>
                <th><f:message key="table.brick.title"/></th>
                <th><f:message key="table.brick.color"/></th>
                <th><f:message key="table.brick.count"/></th>
                <th><f:message key="table.options"/></th>
            </tr>
        </table>

    </s:layout-component>
</s:layout-render>