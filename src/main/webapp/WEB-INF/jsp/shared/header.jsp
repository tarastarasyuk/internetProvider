<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.Locale" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="${cookie.locale.value}"/>
<fmt:setBundle basename="titles"/>
<header class="site-header fixed-top py-0">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">


        <div class="container-fluid justify-content-around">

            <div><a class="navbar-brand" href="#"><img src="../../../assets/img/iProvider_logo.png" alt=""></a></div>

            <div>
                <ul class="nav justify-content-center">
                    <li class="nav-item">
                        <a class="nav-link active" href="${pageContext.request.contextPath}/"><fmt:message
                                key="nav.about"/></a>
                    </li>
                    <li class=" nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            <fmt:message key="nav.service"/>
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item d-flex align-items-center gap-2 py-2"
                                   href="${pageContext.request.contextPath}/services"><span
                                    class="d-inline-block bg-primary rounded-circle"
                                    style="width: .5em; height: .5em;"></span><fmt:message key="nav.services"/></a></li>
                            <li><a class="dropdown-item d-flex align-items-center gap-2 py-2"
                                   href="${pageContext.request.contextPath}/tariffs"><span
                                    class="d-inline-block bg-primary rounded-circle"
                                    style="width: .5em; height: .5em;"></span><fmt:message key="nav.tariffs"/></a></li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#"><fmt:message key="nav.contacts"/></a>
                    </li>
                </ul>
            </div>

            <div>
                <c:choose>
                    <c:when test="${sessionScope.user == null}">
                        <a href="${pageContext.request.contextPath}/login">
                            <button class="btn login-btn" type="submit"><fmt:message key="nav.login"/></button>
                        </a>
                        <button class="btn btn-primary connect-btn" type="submit"><fmt:message
                                key="nav.register"/></button>
                    </c:when>
                    <c:when test="${sessionScope.user != null}">
                        <c:choose>
                            <c:when test="${sessionScope.user.roleId == 2}">
                                <a href="${pageContext.request.contextPath}/login">
                                    <button class="btn login-btn" type="submit"><fmt:message key="nav.profile"/>:
                                        <strong>${sessionScope.user.account}$</strong></button>
                                </a>
                            </c:when>
                            <c:when test="${sessionScope.user.roleId == 1}">
                                <a href="${pageContext.request.contextPath}/login">
                                    <button class="btn login-btn" type="submit"><fmt:message key="nav.admin"/></button>
                                </a>
                            </c:when>
                        </c:choose>
                        <a href="${pageContext.request.contextPath}/logout">
                            <button class="btn btn-primary connect-btn" type="submit"><fmt:message
                                    key="nav.logout"/></button>
                        </a>
                    </c:when>
                </c:choose>


            </div>

        </div>
        <button class="nav-item dropdown language-btn">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"
               aria-expanded="false">
                <%--                TODO: put in properties--%>
                <fmt:message key="country"/> <i class="fas fa-globe"></i>
            </a>
            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                <li><a class="dropdown-item d-flex align-items-center gap-2 py-2" href="#" id="ua"><span
                        class="d-inline-block bg-primary rounded-circle"
                        style="width: .5em; height: .5em;"></span>????????????????????</a></li>
                <li><a class="dropdown-item d-flex align-items-center gap-2 py-2" href="#" id="en"><span
                        class="d-inline-block bg-primary rounded-circle"
                        style="width: .5em; height: .5em;"></span>English</a></li>
            </ul>
        </button>
    </nav>

    <script>
        document.getElementById("ua").addEventListener("click", function (e) {
            console.log("ua")
            document.cookie = "locale=uk_UA; path=/";
            document.cookie = "locale=uk_UA; path=../";
            document.cookie = "locale=uk_UA; path=/adminPanel";
            document.cookie = "locale=uk_UA; path=/manageTariffs";
            document.cookie = "locale=uk_UA; path=/manageClients";
            document.cookie = "locale=uk_UA; path=/adminPanel/manageTariffs";
            document.cookie = "locale=uk_UA; path=/adminPanel/manageClients";
            document.cookie = "locale=uk_UA; path=/adminPanel/manageTariffs/tariffCreationForm";
            document.cookie = "locale=uk_UA; path=/adminPanel/manageClients/clientCreationForm";
            document.cookie = "locale=uk_UA; path=/clientPanel";
            document.cookie = "locale=uk_UA; path=/clientPanel/payment";
            document.cookie = "locale=uk_UA; path=/clientPanel/editClientForm";
            document.cookie = "locale=uk_UA; path=/clientPanel/tariffConnection";
            document.location.reload(true);
        }, false);

        document.getElementById("en").addEventListener("click", function (e) {
            console.log("en")
            document.cookie = "locale=en_US; path=/";
            document.cookie = "locale=en_US; path=../";
            document.cookie = "locale=en_US; path=/adminPanel";
            document.cookie = "locale=en_US; path=/manageTariffs";
            document.cookie = "locale=en_US; path=/manageClients";
            document.cookie = "locale=en_US; path=/adminPanel/manageTariffs";
            document.cookie = "locale=en_US; path=/adminPanel/manageClients";
            document.cookie = "locale=en_US; path=/adminPanel/manageTariffs/tariffCreationForm";
            document.cookie = "locale=en_US; path=/adminPanel/manageClients/clientCreationForm";
            document.cookie = "locale=en_US; path=/clientPanel";
            document.cookie = "locale=en_US; path=/clientPanel/payment";
            document.cookie = "locale=en_US; path=/clientPanel/editClientForm";
            document.cookie = "locale=en_US; path=/clientPanel/tariffConnection";
            document.location.reload(true);

        }, false);
    </script>
</header>