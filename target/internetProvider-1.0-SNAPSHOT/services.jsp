<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:page title="Services" cssLink="../../assets/css/services.css">
    <main style="min-height: 80vh;" class="d-flex align-items-center">

        <div class="container">

            <div class="d-flex justify-content-around" style="flex-wrap: wrap;">
                <c:forEach var="service" items="${serviceList}">
                    <div class="card" style="width: 18rem;">
                        <div class="card-body text-center">
                            <i class="${service.getLogoLink()}" style="color: #001737;"></i>
                            <h4 class="card-title">${service.getName()}</h4>
                            <a href="${pageContext.request.contextPath}tariffs?service=${service.getId()}" class="btn goto-tariffs">See avaible tariffs</a>
                        </div>
                    </div>
                </c:forEach>

            </div>


        </div>

    </main>
</t:page>