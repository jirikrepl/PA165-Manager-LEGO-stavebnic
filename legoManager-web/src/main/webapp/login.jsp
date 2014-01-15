<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Login</title>
</head>
<body>
<h1>Enter your credentials</h1>
<form action="j_spring_security_check" method="POST">
    <table>
        <tr>
            <td>Username:</td>
            <td><input type="text" name="j_username" /></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="j_password" /></td>
        </tr>
        <tr>
            <td colspan="2" align="right"><input type="submit" value="Login"/></td>
        </tr>
    </table>
</form>
<font color="red">
    <span>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</span>
</font>
</body>
</html>
=======
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
>>>>>>> origin/master
