<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:layout-render name="/layout.jsp" titlekey="index.title">
    <s:layout-component name="body">

        <h1 class="text-center"><f:message key="index.headline"/></h1>
        <div class="text-center"><p><f:message key="index.preface"/></p></div>

    </s:layout-component>
</s:layout-render>
