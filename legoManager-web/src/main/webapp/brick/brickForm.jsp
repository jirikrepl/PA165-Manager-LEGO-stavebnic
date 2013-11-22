<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<table>
    <tr>
        <th><s:label for="b1" name="brick.title"/></th>
        <td><s:text id="b1" name="brick.title"/></td>
    </tr>
    <tr>
        <th><s:label for="b4" name="brick.color"/></th>
        <td><s:select id="b4" name="brick.color"><s:options-enumeration enum="cz.muni.fi.PA165.api.service.Color"/></s:select></td>
    </tr>
</table>