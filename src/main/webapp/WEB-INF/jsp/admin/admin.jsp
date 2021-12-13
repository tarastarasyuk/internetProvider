<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.locale.value}"/>
<fmt:setBundle basename="titles"/>
<t:page title="AdminPanel" cssLink="../../assets/css/admin.css">
    <main style="min-height: 80vh;" class="d-flex justify-content-center align-items-center">
    <div class="container">
        <section class="manage-section">
            <div class="manage-section-title">
                <h1>Admin Panel</h1>
            </div>


            <div class="manage-cards-box">

                <div class="manage-cards-line">

                    <div class="manage-card" style="width: 18rem;">
                        <div class="manage-card-body">
                            <i class="far fa-file-alt fa-5x" style="color: #001737;"></i>
                        </div>
                        <div class="manage-card-footer">
                            <a href="${pageContext.request.contextPath}/adminPanel/manageTariffs"><button class="btn btn-primary manage-btn">Manage
                                tariffs</button></a>
                        </div>
                    </div>

                    <div class="manage-card" style="width: 18rem;">
                        <div class="manage-card-body">
                            <i class="far fa-user fa-5x" style="color: #001737;"></i>
                        </div>
                        <div class="manage-card-footer">
                            <a href="${pageContext.request.contextPath}/adminPanel/manageClients"><button class="btn btn-primary manage-btn">Manage
                                users</button></a>
                        </div>
                    </div>
                </div>

            </div>

        </section>
    </div>
    </main>

</t:page>

