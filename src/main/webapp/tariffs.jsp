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
                        <form method="GET", action="${pageContext.request.contextPath}/tariffs">

                            <div class="filters-logo"><i class="fas fa-sliders-h fa-lg"></i></div>
                            <div class="filters">
                                <select class="form-select" name="service" id="by-service" multiple>
                                    <option value="" ${selectedServices == null ? 'selected':''} disabled hidden >Services</option>
                                    <c:forEach var="service" items="${serviceList}">
                                        <option value="${service.getId()}" ${selectedServices.contains(service.getId().toString()) ? 'selected': ''}  >${service.getName()}
                                        </option>
                                    </c:forEach>
                                </select>
                                <select class="form-select" name="sortBy" id="by-price">
                                    <option value="" ${selectedSortBy == null ? 'selected':''} disabled hidden>Sort by</option>
                                    <option value="price_desc" ${selectedSortBy.equals("price_desc")? 'selected': ''}>Price High To Low</option>
                                    <option value="price_asc" ${selectedSortBy.equals("price_asc")? 'selected': ''}>Price Low To High</option>
                                    <option value="name_asc" ${selectedSortBy.equals("name_asc")? 'selected': ''}>A-Z</option>
                                    <option value="name_desc" ${selectedSortBy.equals("name_desc")? 'selected': ''}>Z-A</option>
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
                    ${noSuchTariffs}
                <c:forEach var="entry" items="${mapWithTariffsAndServices.entrySet()}">

                    <div class="card tariff-card" style="width: 18rem;">

                        <div class="card-body tariff-content">
                            <h5 class="card-title tariff-title">"${entry.getKey().getName()}"</h5>
                            <p class="card-text tariff-subtitle">${entry.getKey().getDescription()}</p>

                            <div class="tariff-price">
                                <div class="tariff-price-box">
                                    <span>Price:</span>
                                    <span>${entry.getKey().getPrice()}$/${entry.getKey().getDayDuration()}days</span>
                                </div>
                            </div>

                            <div class="tariff-service-list">
                                Services:
                                <c:forEach var="service" items="${entry.getValue()}"><span><strong>*${service} </strong></span></c:forEach>
                            </div>
                        </div>


                        <ul class="list-group list-group-flush list-of-features">
                            <!-- TODO: MOVE INNER CSS IN  tariffs.css -->


                            <c:forEach var="feature" items="${entry.getKey().getFeaturesList()}">
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