<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/layout.jsp" titlekey="index.title">
    <s:layout-component name="body">

        <form action="<c:url value='j_spring_security_check' />" method="post">
            <c:if test="${param.error != null}">
                <p>
                    Spatne jmeno nebo heslo
                </p>
            </c:if>
            <c:if test="${param.logout != null}">
                <p>
                    Uzivatel byl odhlasen.
                </p>
            </c:if>
            <p>
                Login
                <input type="text" name="j_username"/>
            </p>

            <p>
                Heslo
                <input type="password" name="j_password"/>
            </p>
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <button type="submit" class="btn">Log in</button>
        </form>

    </s:layout-component>
</s:layout-render>