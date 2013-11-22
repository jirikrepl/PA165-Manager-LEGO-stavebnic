<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="themeset.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.PA165.action.ThemeSetActionBean" var="actionBean"/>
        <h1 class="text-center"><f:message key="themeset.headline"/></h1>

        <s:form class="form-horizontal" id="themesetCreateForm" beanclass="cz.muni.fi.PA165.action.ThemeSetActionBean">
            <legend><f:message key="themeset.create.title"/></legend>
            <%@include file="form.jsp"%>

            <s:hidden name="themeSetDto.id"/>

            <!--submit button, in bootstrap div class, see documentation for bootstrap forms-->
            <div class="form-group">
                <span class="col-sm-offset-2 col-sm-2">
                    <s:submit class="btn" name="updateThemeSet"><f:message key="themeset.button.save"/></s:submit>
                    </span>

                    <span class="col-sm-4">
                    <s:submit class="btn" name=""><f:message key="themeset.button.cancel"/></s:submit>
                    </span>
                </div>
        </s:form>

    </s:layout-component>
</s:layout-render>