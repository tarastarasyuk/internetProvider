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
                    <button class="btn btn-success top-up-account">Top up</button>
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
                            <span>Town: </span>
                        </div>

                    </div>
                    <button class="btn edit-user-btn">
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
</main>
</t:page>