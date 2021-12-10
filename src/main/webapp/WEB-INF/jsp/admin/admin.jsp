<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

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

<%--                <div class="manage-cards-line">--%>


<%--                    <div class="manage-card" style="width: 18rem;">--%>
<%--                        <div class="manage-card-body">--%>
<%--                            <i class="fas fa-wifi fa-5x" style="color: #001737;"></i>--%>
<%--                        </div>--%>
<%--                        <div class="manage-card-footer">--%>
<%--                            <a href="${pageContext.request.contextPath}/adminPanel/manageServices"><button class="btn btn-primary manage-btn">Manage--%>
<%--                                services</button></a>--%>
<%--                        </div>--%>
<%--                    </div>--%>

<%--                    <div class="manage-card" style="width: 18rem;">--%>
<%--                        <div class="manage-card-body">--%>
<%--                            <i class="far fa-building fa-5x" style="color: #001737;"></i>--%>
<%--                        </div>--%>
<%--                        <div class="manage-card-footer">--%>
<%--                            <a href="${pageContext.request.contextPath}/adminPanel/manageCities"><button class="btn btn-primary manage-btn">Manage--%>
<%--                                cities</button></a>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>

            </div>

        </section>
    </div>
    </main>
<%--    <h1>AdminPanel</h1>--%>
<%--    <!-- Button trigger modal -->--%>
<%--    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">--%>
<%--        Add a new Client--%>
<%--    </button>--%>

<%--    <!-- Modal -->--%>
<%--    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">--%>
<%--        <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">--%>
<%--            <div class="modal-content">--%>
<%--                <div class="modal-header">--%>
<%--                    <h5 class="modal-title" id="exampleModalLabel">Register a new client:</h5>--%>
<%--                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>--%>
<%--                </div>--%>
<%--                <div class="modal-body">--%>
<%--                    <form class="row g-3" method="post" action="${pageContext.request.contextPath}/adminPanel/addNewClient">--%>

<%--                        <div class="col-md-4">--%>
<%--                            <label for="validationDefault03" class="form-label">Username</label>--%>
<%--                            <input type="text" class="form-control" id="validationDefault03" value="Otto" name="username" required>--%>
<%--                        </div>--%>
<%--                        <div class="col-md-4">--%>
<%--                            <label for="validationDefault04" class="form-label">Password</label>--%>
<%--                            <input type="text" class="form-control" id="validationDefault04" value="Otto" name="password" required>--%>
<%--                        </div>--%>
<%--                        <div class="col-md-4"></div>--%>

<%--                        <div class="col-md-8">--%>
<%--                            <label for="validationDefault05" class="form-label">Email</label>--%>
<%--                            <input type="text" class="form-control" id="validationDefault05" value="Otto" name="email" required>--%>
<%--                        </div>--%>
<%--                        <div class="col-md-4"></div>--%>

<%--                        <div class="col-md-3">--%>
<%--                            <label for="cityId" class="form-label">City</label>--%>
<%--                            <select class="form-select" id="cityId" name="cityId" required>--%>
<%--                                <option selected disabled value="">Choose your city</option>--%>
<%--                                <c:forEach var="city" items="${cityList}">--%>
<%--                                    <option value="${city.getId()}">${city.getCityName()}--%>
<%--                                    </option>--%>
<%--                                </c:forEach>--%>
<%--                            </select>--%>

<%--                            <div class="invalid-feedback">--%>
<%--                                Please select a valid state.--%>
<%--                            </div>--%>
<%--                        </div>--%>

<%--                        <div class="col-12">--%>
<%--                            <button class="btn btn-primary" type="submit">Submit form</button>--%>
<%--                        </div>--%>
<%--                    </form>--%>
<%--                </div>--%>
<%--                <div class="modal-footer">--%>
<%--                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>--%>
<%--                    <button type="button" class="btn btn-primary">Register</button>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <br>--%>
<%--    <br>--%>
<%--    <br>--%>
<%--    <br>--%>
<%--    <br>--%>
<%--   <div style="display: flex; justify-content: center;"><a href="/logout">Logout</a></div>--%>

</t:page>

