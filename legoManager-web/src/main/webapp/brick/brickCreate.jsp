<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="brick.create.title">
    <s:layout-component name="body">
        
        <s:useActionBean beanclass="cz.muni.fi.PA165.action.BrickActionBean" var="actionBean"/>
        <h1 class="text-center"><f:message key="bricks.create.headline"/></h1>
        
        <s:form beanclass="cz.muni.fi.PA165.action.BrickActionBean">
                
                <%@include file="brickForm.jsp"%>
                
                <s:submit class="btn" name="save"><f:message key="brick.create"/></s:submit>
                
        </s:form>



        

    </s:layout-component>
</s:layout-render>