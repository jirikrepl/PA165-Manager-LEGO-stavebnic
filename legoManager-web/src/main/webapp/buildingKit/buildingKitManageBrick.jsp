<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="brick.create.title">
    <s:layout-component name="body">

        <s:useActionBean beanclass="cz.muni.fi.PA165.action.BuildingKitActionBean" var="actionBean"/>
        <h1 class="text-center"><f:message key="buildingkit.addbrick.headline"/></h1>


        <s:form class="createForm form-horizontal" beanclass="cz.muni.fi.PA165.action.BuildingKitActionBean">
            <legend><f:message key="buildingkit.addbrick.legend"/></legend>
            <s:hidden name="buildingKit.id" value="${buildingKit.id}"/>
            <div class="form-group">
                <s:label for="b1" class="col-sm-2 control-label"><f:message key="form.create.title"/></s:label>
                <div class="col-sm-4">
                    <s:select class="form-control" id="b1" name="brick.id">
                        <s:options-collection collection="${actionBean.bricks}" value="id" label="name"/>
                    </s:select>
                </div>
            </div>

            <div class="form-group">
                

                <s:label for="b1" class="col-sm-2 control-label"><f:message key="bricks.form.create.count"/></s:label>

                <div class="col-sm-4">
                    <s:text class="form-control" id="b2" name="brickCount" value="1"/>
                </div>
            </div>

            
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-1">
                    <s:submit class="btn" name="addBrick"><f:message key="buildingkit.addbrick.add"/></s:submit>
                </div>
            
                <div class="col-sm-offset-1 col-sm-3">
                    <s:submit class="btn" name=""><f:message key="buildingkit.addbrick.cancel"/></s:submit>
                </div>
            </div>
            
        </s:form>




        <table class="table">
            <tr>
                <th>id</th>
                <th><f:message key="table.brick.title"/></th>
                <th><f:message key="table.brick.count"/></th>
                <th><f:message key="table.options"/></th>
            </tr>

            <!--creates lines of table in loop-->
            <!--uses bricks from getter from action bean-->
            <c:forEach items="${actionBean.bricksSaved}" var="brickItem">
                <tr>
                    <td>${brickItem.key.id}</td>
                    <td>${brickItem.key.name}</td>
                    <td>${brickItem.value}</td>

                    <td>
                        <span>

                            <s:form class="table-buttons" beanclass="cz.muni.fi.PA165.action.BuildingKitActionBean">
                                <s:hidden name="buildingKit.id" value="${buildingKit.id}"/>
                                <input name="brickIdDelete" value="${brickItem.key.id}" type="hidden"/>
                                <s:submit class="btn" name="deleteBrick"><f:message key="buildingkit.addbrick.delete"/></s:submit>
                            </s:form>
                        </span>

                    </td>

                </tr>
            </c:forEach>
        </table>


    </s:layout-component>
</s:layout-render>
