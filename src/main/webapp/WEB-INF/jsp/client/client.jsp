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
                    <button type="button" class="btn btn-success top-up-account" data-bs-toggle="modal" data-bs-target="#editModalBalance">
                        Top up
                    </button>

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
                    <span>Tariff expiration:</span>
                    <span>&nbsp;28</span>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-1"></div>

            <div class="col-6">
                <div class="info-field big user-info-section">

                    <div class="user-info d-flex flex-row flex-wrap justify-content-around align-items-center">

                        <div class="logo">
                            <i class="far fa-user fa-5x"></i>
                        </div>

                        <div class="info d-flex flex-column">
                            <span class="header-info">Your data:</span>
                            <span>Username: ${user.getUsername()}</span>
                            <span>Password: ${user.getPassword()}</span>
                            <span>Email: ${user.getEmail()}</span>
                            <span>Town: ${user.getCityName()}</span>
                        </div>

                    </div>

                    <button type="button" class="btn edit-user-btn" data-bs-toggle="modal" data-bs-target="#editModal">
                        Edit profile
                    </button>

                    <br>
<%--                    TODO: REMOVE LATER--%>
                    <a href="${pageContext.request.contextPath}/logout"><button class="btn btn-success">Logout</button></a>
                </div>

            </div>

            <div class="col-4">
                <div
                        class="tariff-info info-field big d-flex flex-column align-items-center justify-content-around">
                    <div class="tariff-name">"Internet Basic"</div>
                    <div class="price-block d-flex flex-column"><span>Price: </span><span
                            class="tariff-price">50$/month</span></div>
                    <div class="service-list">

                        <li> <i class="fas fa-circle fa-xs"
                                style="position: relative; top: -1px; right: 4px; color: #005FFF;"></i>
                            Internet
                            40Mbps</li>

                        <li> <i class="fas fa-circle fa-xs"
                                style="position: relative; top: -1px; right: 4px; color: #005FFF;"></i>
                            Phone
                            10GB</li>
                    </div>

                    <button class="btn btn-primary btn-change-tariff">Change</button>
                </div>
            </div>

            <div class="col-1"></div>
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
                                <input type="text" class="form-control" id="validationDefault04" value="${user.getPassword()}" name="password" required>
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



        <div class="modal fade" id="editModalBalance" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabelBalance">Top up your account:</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form class="row g-3" method="post" action="${pageContext.request.contextPath}/clientPanel/topUpAccount">

                            <div class="col-md-4">
                                <label for="validationBalance" class="form-label">Balance</label>
                                <input type="text" class="form-control" id="validationBalance" value="0" name="account" required>
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