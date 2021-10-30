<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ attribute name="title" required="true" type="java.lang.String" %>

<t:template>
    <jsp:attribute name="css">
        <meta charset="utf-8" />
        <title>${title}</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
              rel="stylesheet"
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
              crossorigin="anonymous">
    </jsp:attribute>

    <jsp:attribute name="header">
        <jsp:include page="/WEB-INF/jsp/shared/header.jsp"/>
    </jsp:attribute>

    <jsp:body>
        <jsp:doBody/>
    </jsp:body>

    <jsp:attribute name="footer">
        <jsp:include page="/WEB-INF/jsp/shared/footer.jsp"/>
    </jsp:attribute>
</t:template>