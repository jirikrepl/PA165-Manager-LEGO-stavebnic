<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/layout.jsp" titlekey="index.title">
    <s:layout-component name="body">

        <div class="container" style="text-align: center">
            <img class="text-center" style="width: 13%" src="bootstrap/brick.png"/>

            <h1 class="text-center"><f:message key="index.headline"/></h1>

            <div class="text-center"><p><f:message key="index.preface"/></p></div>

        </div>
        <div class="container" style="margin-top: 65px; text-align: center;">
            <form action="<c:url value='j_spring_security_check' />" method="post">
                <c:if test="${param.error != null}">
                    <p style="color: red; margin-left: -79px;">
                        <f:message key="loginform.badLogin"/>
                    </p>
                </c:if>
                <c:if test="${param.logout != null}">
                    <p style="color: green">
                        <f:message key="loginform.userLogout"/>
                    </p>
                </c:if>
                    <%--<p>--%>
                    <%--Login--%>
                    <%--<input type="text" name="j_username"/>--%>
                    <%--</p>--%>

                    <%--<p>--%>
                    <%--Heslo--%>
                    <%--<input type="password" name="j_password"/>--%>
                    <%--</p>--%>
                    <%--<input type="hidden"--%>
                    <%--name="${_csrf.parameterName}"--%>
                    <%--value="${_csrf.token}"/>--%>

                <div class="row col-md-3 col-sm-3 col-md-offset-4 col-sm-offset-4 col-xs-3 col-xs-offset-4">
                    <div class="form-group">
                        <label for="exampleInputEmail1"><f:message key="loginform.username"/></label>
                        <input class="form-control" name="j_username" id="exampleInputEmail1" />
                    </div>

                    <div class="form-group">
                        <label for="exampleInputPassword1"><f:message key="loginform.password"/></label>
                        <input type="password" name="j_password" class="form-control" id="exampleInputPassword1"/>

                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </div>

                    <button type="submit" class="btn"><f:message key="loginform.loginButton"/></button>
                </div>
            </form>
        </div>

    </s:layout-component>
</s:layout-render>