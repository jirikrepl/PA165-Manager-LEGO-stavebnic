<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="category.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.PA165.action.CategoryActionBean" var="actionBean"/>
        <h1 class="text-center"><f:message key="category.headline"/></h1>

        <!--form for brick creation-->
        <!--there is included file with rows in this form-->
        
        <s:form class="form-horizontal" id="brickCreateForm" beanclass="cz.muni.fi.PA165.action.CategoryActionBean">
            <legend><f:message key="category.create"/></legend>
            <%@include file="form.jsp"%>
            
            <s:hidden name="categoryDto.id"/>

            <!--submit button, in bootstrap div class, see documentation for bootstrap forms-->
            <div class="form-group">
                <span class="col-sm-offset-2 col-sm-1">
                    <s:submit class="btn" name="updateCategory"><f:message key="brick.edit.button"/></s:submit>
                    </span>
                    
                    <span class="col-sm-offset-1 col-sm-3">
                    <s:submit class="btn" name=""><f:message key="brick.cancel.button"/></s:submit>
                    </span>
                </div>
        </s:form>

    </s:layout-component>
</s:layout-render>