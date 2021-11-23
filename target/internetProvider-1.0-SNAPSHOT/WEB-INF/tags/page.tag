<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ attribute name="title" required="true" type="java.lang.String" %>
<%@ attribute name="cssLink" required="true" type="java.lang.String" %>

<t:template>
    <jsp:attribute name="css">
        <meta charset="utf-8" />
        <title>${title}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

    <script src="https://kit.fontawesome.com/83c9b01a57.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="../../assets/css/headerAndFooter.css">
        <link rel="stylesheet" href="${cssLink}">
    </jsp:attribute>

    <jsp:attribute name="header">
        <jsp:include page="/WEB-INF/jsp/shared/header.jsp"/>
    </jsp:attribute>


    <jsp:attribute name="footer">
        <jsp:include page="/WEB-INF/jsp/shared/footer.jsp"/>
    </jsp:attribute>

    <jsp:body>
        <jsp:doBody/>
    </jsp:body>

</t:template>