<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/layout.jsp" titlekey="index.title">
    <s:layout-component name="header">
        <s:layout-component name="body">
            <p>Congratulations, you've set up a Stripes project!</p>
            </br>
            <p>
                You are running Java version ${actionBean.javaVersion}
                on the ${actionBean.osName} operating system.
            </p>
        </s:layout-component>
    </s:layout-component>
</s:layout-render>
