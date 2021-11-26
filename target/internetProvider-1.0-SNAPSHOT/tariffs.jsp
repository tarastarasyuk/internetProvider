<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:page title="Login" cssLink="../../assets/css/tariffs.css">
    <main>
        <div class="container">

            <section class="filter">

                <div class="tariffs-title">
                    <h1>Tariffs</h1>
                </div>

                <div class="filters-line">

                    <div class="filter-choosing">
                        <form  >

                            <div class="filters-logo"><i class="fas fa-sliders-h fa-lg"></i></div>
                            <div class="filters">
                                <select class="form-select" name="service" id="by-service">
                                    <option value="" selected disabled hidden>Services</option>
                                    <c:forEach var="service" items="${serviceList}">
                                        <option value="${service.getId()}">${service.getName()}</option>
                                    </c:forEach>
                                </select>
                                <select class="form-select" name="sortBy" id="by-price">
                                    <option value="" selected disabled hidden>Sort by</option>
                                    <option value="priceDesc">Price High To Low</option>
                                    <option value="priceAsc">Price Low To High</option>
                                    <option value="abcDesc">A-Z</option>
                                    <option value="abcAsc">Z-A</option>
                                </select>
                            </div>

                            <div class="filter-apply">
                                <a href="#" >
                                    <button type="submit"
                                            class="btn btn-primary">Apply
                                    </button>
                                </a>
                            </div>

                        </form>

                    </div>

                    <div class="filter-clear-btn">
                        <a href="tariffs">
                            <button type="button" class="btn btn-secondary clear-btn"
                                    onClick="window.location.reload();">Clear &#x2717
                            </button>
                        </a>
                    </div>

                </div>

            </section>


            <section class="tariffs">

                <c:forEach var="tariff" items="${tariffList}">

                    <div class="card tariff-card" style="width: 18rem;">

                        <div class="card-body tariff-content">
                            <h5 class="card-title tariff-title">"${tariff.getName()}"</h5>
                            <p class="card-text tariff-subtitle">${tariff.getDescription()}</p>

                            <div class="tariff-price">
                                <div class="tariff-price-box">
                                    <span>Price:</span>
                                    <span>${tariff.getPrice()}$/${tariff.getDayDuration()}days</span>
                                </div>
                            </div>

                            <div class="tariff-service-list">
                                Service: Phone, IP-TV
                            </div>
                        </div>


                        <ul class="list-group list-group-flush list-of-features">
                            <!-- TODO: MOVE INNER CSS IN  tariffs.css -->


                            <c:forEach var="feature" items="${tariff.getFeaturesList()}">
                                <li class="list-group-item">
                                    <span class="d-inline-block bg-primary rounded-circle"
                                  style="width: .5em; height: .5em;"></span>
                                    ${feature}
                                </li>
                            </c:forEach>

                        </ul>

                        <div class="card-body tariffs-connection">
                            <a href="#" method="GET">
                                <button type="button" class="btn btn-primary">Connect</button>
                            </a>
                        </div>

                    </div>

                </c:forEach>

            </section>
        </div>
    </main>
</t:page>