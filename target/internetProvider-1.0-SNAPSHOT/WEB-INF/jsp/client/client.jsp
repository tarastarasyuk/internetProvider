<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:page title="Client" cssLink="../../assets/css/client.css">
    <main style="min-height: 80vh;" class="d-flex justify-content-center align-items-center">
    <div class="container" style="width: 80%;">
        <!-- TODO: REMOVE THIS STYLE -->
        <div class="row" style="margin-top: 40px;">
            <div class="col-5">
                <div class="info-field">
                    <span>${doPost}Account: ${user.getAccount()}$&nbsp;&nbsp;&nbsp;</span>
                    <a href="/payment"><button type="button" class="btn btn-success top-up-account" >
                        Top up
                    </button>
                    </a>
                </div>
            </div>
            <div class="col-3">
                <div class="info-field">
                    <span>Status:</span>
                    <span class="status">&nbsp;${user.getStatus()}</span>
                </div>
            </div>
            <div class="col-4">
                <div class=" info-field">
                    <span>
                        <c:choose>

                                <c:when test="${user.getTariffId() != 0}">
                                    <c:choose>
                                        <c:when test="${user.getTariffExpiration() == 0}">
                                            <span>Expiration:</span>
                                            Expired
                                        </c:when>
                                        <c:when test="${user.getTariffExpiration() != 0}">
                                            <span>Expiration:</span>
                                            ${user.getTariffExpiration()} days left
                                        </c:when>
                                    </c:choose>


<%--                                    <span>Expiration:</span>--%>
<%--                                    ${user.getTariffExpiration()} days left--%>

                                </c:when>

                                <c:otherwise>
                                    <span>No tariff</span>
                                </c:otherwise>

                        </c:choose>
                    </span>
                </div>
            </div>
        </div>
        <div class="client-data-and-tariff">

            <div class="client-data">


                    <div class="user-info">

                        <div class="user-logo">
                            <i class="far fa-user fa-7x"></i>
                        </div>

                        <div class="info">
                            <span class="info-title">Your data:</span>
                            <span>Username: ${user.getUsername()}</span>
                            <span>Email: ${user.getEmail()}</span>
                            <span>Town: ${user.getCityName()}</span>
                        </div>

                    </div>

                    <button type="button" class="btn edit-user-btn" data-bs-toggle="modal" data-bs-target="#editModal">
                        Edit profile
                    </button>



            </div>

            <div class="client-tariff-card">
                <span class="clientPanel-header">Your tariff:</span>
                <c:choose>
                    <c:when test="${sessionScope.user.tariffId != 0}">
                        <div class="card tariff-card" style="width: 18rem;">
                            <div class="card-body tariff-content">
                                <h5 class="card-title tariff-title">"${requestScope.tariff.name}"</h5>
                                <p class="card-text tariff-subtitle">${requestScope.tariff.description}</p>

                                <div class="tariff-price">
                                    <div class="tariff-price-box">
                                        <span>Price:</span>
                                        <span>${requestScope.tariff.price}$/${requestScope.tariff.dayDuration}days</span>
                                    </div>
                                </div>
                            </div>

                            <div class="tariff-service-list">
                                Services:
                                <c:forEach var="service"
                                           items="${requestScope.tariff.listOfServiceName}"><span><strong>*${service} </strong></span></c:forEach>
                            </div>

                            <ul class="list-group list-group-flush list-of-features">
                                <!-- TODO: MOVE INNER CSS IN  tariffs.css -->

                                <c:forEach var="feature" items="${requestScope.tariff.featuresList}">
                                    <li class="list-group-item">
                                    <span class="d-inline-block bg-primary rounded-circle"
                                          style="width: .5em; height: .5em;"></span>
                                            ${feature}
                                    </li>
                                </c:forEach>

                            </ul>

                            <div class="card-body tariffs-connection">
                                <a href="${pageContext.request.contextPath}/tariffs"><button type="button" class="btn btn-primary">Change</button></a>
                                <div>
                                    <form action="${pageContext.request.contextPath}/clientPanel/deleteTariff" method="POST">
                                        <button type="submit" class="btn btn-danger">Delete</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:when>
                    <c:when test="${sessionScope.user.tariffId == 0 }">
                        <div class="current-tarrif">

                            <div class="card tariff-card" style="width: 18rem; height: 100%; background-color:#fff;">

                                <div class="card-body tariff-content" >

                                    <div class="card empty-tariff-card" style=" background-color:#fff;">
                                            You don't have tariff
                                    </div>


                                </div>



                            </div>
                            <a href="${pageContext.request.contextPath}/tariffs"><button type="button" class="btn btn-primary choose-tariff-btn">Let's Choose</button></a>



                        </div>
                    </c:when>
                </c:choose>



            </div>


        </div>
    </div>


        <div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Edit your profile:</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form class="row g-3" method="post" action="${pageContext.request.contextPath}/clientPanel/editProfile">

                            <div class="col-md-4">
                                <label for="validationDefault03" class="form-label">User</label>
                                <input type="text" class="form-control" id="validationDefault03" value="${user.getUsername()}" name="username" required>
                            </div>
                            <div class="col-md-4">
                                <label for="validationDefault04" class="form-label">Password</label>
                                <input type="text" class="form-control" id="validationDefault04" name="password" placeholder="********">
                            </div>
                            <div class="col-md-4"></div>

                            <div class="col-md-8">
                                <label for="validationDefault05" class="form-label">Email</label>
                                <input type="text" class="form-control" id="validationDefault05" value="${user.getEmail()}" name="email" required>
                            </div>
                            <div class="col-md-4"></div>

                            <div class="col-md-3">
                                <label for="cityId" class="form-label">City</label>
                                <select class="form-select" id="cityId" name="cityId" required>
                                    <option disabled hidden value="">Choose your city</option>
                                    <option value="d">d</option>
                                    <c:forEach var="city" items="${cityList}">
                                        <option value="${city.getId()}" ${user.getCityId() == city.getId() ? 'selected' : ''}>${city.getCityName()}
                                        </option>
                                    </c:forEach>
                                </select>

                                <div class="invalid-feedback">
                                    Please select a valid state.
                                </div>
                            </div>


                            <div class="modal-footer">
                                    <button class="btn btn-primary" type="submit">Submit form</button>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>




</main>
</t:page>