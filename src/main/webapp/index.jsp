<%@ page import="java.util.ResourceBundle" %>
<%@ page import="java.util.Locale" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${cookie.locale.value}"/>
<fmt:setBundle basename="lang"/>
<t:page title="Home" cssLink="">
    <main>
        <div class="container text-center d-flex flex-column justify-content-center align-items-center "
             style="min-height: 80vh;">

            <h1><fmt:message key="title"/></h1>
            <h1>Hello!</h1>
        <fmt:message key="text"/>
        </div>
    </main>
</t:page>
