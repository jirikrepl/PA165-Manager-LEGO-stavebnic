<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<s:layout-definition>
    <!DOCTYPE html>
    <html lang="${pageContext.request.locale}">
        <head>
            <title><f:message key="${titlekey}"/></title>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" />
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap/css/style.css" />
            <s:layout-component name="html-head"/>
        </head>
        <body>
            
            <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
                <div class="container">
                    <div class="navbar-header">
                        <a href="../" class="navbar-brand">Lego Manager</a>
                    </div>
                    <nav class="collapse navbar-collapse bs-navbar-collapse" role="navigation">
                        <ul class="nav navbar-nav">
                            <li class="active">
                                <s:link href="/category.jsp"><f:message key="navigation.category"/></s:link>
                            </li>
                            <li>
                                <s:link href="/themeset.jsp"><f:message key="navigation.themeset"/></s:link>
                            </li>
                            <li>
                                <s:link href="/buildingkit.jsp"><f:message key="navigation.buildingkit"/></s:link>
                            </li>
                            <li>
                                <s:link beanclass="cz.muni.fi.PA165.action.BrickActionBean"><f:message key="navigation.brick"/></s:link>
                            </li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li>
                                <div class="navbar-collapse collapse">
                                    <form class="navbar-form navbar-right">
                                        <div class="form-group">
                                            <input type="text" placeholder="Email" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <input type="password" placeholder="Password" class="form-control">
                                        </div>
                                        <button type="submit" class="btn btn-success">Sign in</button>
                                        <button type="submit" class="btn btn-success">Sign up</button>
                                    </form>
                                </div><!--/.navbar-collapse -->
                            </li>

                        </ul>
                                </nav>
                    </div>
                <s:layout-component name="header"/>
                <!-- do ostatnych jsp vlozit layout-render header -->
            </div>
              
            <div id="content">
                  <!--sem sa vyrenderuje obsah stranok. Pre kazdu stranku specificky obsah.-->
                  <!-- do ostatnych jsp vlozit layout-render body -->
                <s:layout-component name="body"/>
            </div>
        </body>
    </html>
</s:layout-definition>