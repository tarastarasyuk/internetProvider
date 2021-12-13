<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="${cookie.locale.value}"/>
<fmt:setBundle basename="titles"/>
<footer class="footer d-flex align-items-center">
    <div class="container expand-lg d-flex flex-row justify-content-around align-items-center"
         style="flex-wrap: wrap;">
        <li><a href="${pageContext.request.contextPath}/" class="nav-link"><fmt:message key="nav.about"/></a></li>
        <li><a href="${pageContext.request.contextPath}/services" class="nav-link"><fmt:message key="nav.services"/></a></li>
        <li><a href="${pageContext.request.contextPath}/tariffs" class="nav-link"><fmt:message key="nav.tariffs"/></a></li>
        <li><a href="#" class="nav-link"><fmt:message key="nav.contacts"/></a></li>
        <li><a href="#" class="nav-link"><fmt:message key="nav.privacy_terms"/></a></li>
        <li><a href="${pageContext.request.contextPath}/login" class="nav-link"><fmt:message key="nav.login"/></a></li>

    </div>

</footer>