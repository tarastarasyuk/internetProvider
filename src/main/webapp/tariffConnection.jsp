<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<t:page title="Login" cssLink="../../assets/css/tariffs.css">
    <main>
        <section class="tariff-connection">
            <div class="container">
                <a href="../tariffs"><button class="back-to-tariffs btn btn-primary">
                    <i class="fas fa-arrow-left"></i>
                    Back to tariffs
                </button></a>
                <div class="tariff-connection-title">
                    <h1>Tariff Connetion</h1>
                </div>

                <div class="tariff-payment">
                    <div class="tariff-payment-box">
                        <form action="${pageContext.request.contextPath}/tariff/tariffConnection" method="POST">
                            <h4>Connection fee: ${newTariff.getPrice()}$</h4>
                            <input type="submit" class="btn btn-success" value="Pay and Connect">
                        </form>
                    </div>
                </div>

                <div class="tariff-window">
                    <div class="tariff-cards-info">
                        <c:choose>
                            <c:when test="${noTariffMessage != null}">
                            <div class="current-tarrif">

                                <h3>Your current tariff:</h3>

                                <div class="card tariff-card" style="width: 18rem; height: 100%;">

                                    <div class="card-body tariff-content">

                                        <div class="card empty-tariff-card">
                                                ${noTariffMessage}
                                        </div>
                                    </div>



                                </div>



                            </div>
                            </c:when>
                            <c:when test="${noTariffMessage == null}">
                                <div class="current-tarrif">

                                    <h3>Your current tarrif:</h3>

                                    <div class="card tariff-card" style="width: 18rem;">

                                        <div class="card-body tariff-content">
                                            <h5 class="card-title tariff-title">"${currentTariff.getName()}"</h5>
                                            <p class="card-text tariff-subtitle">${currentTariff.getDescription()}</p>

                                            <div class="tariff-price">
                                                <div class="tariff-price-box">
                                                    <span>Price:</span>
                                                    <span>${currentTariff.getPrice()}$/${currentTariff.getDayDuration()}days</span>
                                                </div>
                                            </div>

                                            <div class="tariff-service-list">
                                                Services:
                                                <c:forEach var="service" items="${currentTariff.getListOfServiceName()}"><span><strong>*${service} </strong></span></c:forEach>
                                            </div>
                                        </div>


                                        <ul class="list-group list-group-flush list-of-features">
                                            <!-- TODO: MOVE INNER CSS IN  tariffs.css -->

                                            <c:forEach var="feature" items="${currentTariff.getFeaturesList()}">
                                                <li class="list-group-item">
                                    <span class="d-inline-block bg-primary rounded-circle"
                                          style="width: .5em; height: .5em;"></span>
                                                        ${feature}
                                                </li>
                                            </c:forEach>

                                        </ul>


                                    </div>


                                </div>
                            </c:when>
                        </c:choose>

                        <div class="new-tarrif">

                            <h3>Tarrif you want:</h3>

                            <div class="card tariff-card" style="width: 18rem;">

                                <div class="card-body tariff-content">
                                    <h5 class="card-title tariff-title">"${newTariff.getName()}"</h5>
                                    <p class="card-text tariff-subtitle">${newTariff.getDescription()}</p>

                                    <div class="tariff-price">
                                        <div class="tariff-price-box">
                                            <span>Price:</span>
                                            <span>${newTariff.getPrice()}$/${newTariff.getDayDuration()}days</span>
                                        </div>
                                    </div>

                                    <div class="tariff-service-list">
                                        Services:
                                        <c:forEach var="service" items="${newTariff.getListOfServiceName()}"><span><strong>*${service} </strong></span></c:forEach>
                                    </div>
                                </div>


                                <ul class="list-group list-group-flush list-of-features">
                                    <!-- TODO: MOVE INNER CSS IN  tariffs.css -->

                                    <c:forEach var="feature" items="${newTariff.getFeaturesList()}">
                                        <li class="list-group-item">
                                    <span class="d-inline-block bg-primary rounded-circle"
                                          style="width: .5em; height: .5em;"></span>
                                                ${feature}
                                        </li>
                                    </c:forEach>

                                </ul>


                            </div>


                        </div>
                    </div>
                </div>

            </div>


        </section>
    </main>
</t:page>
