<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                Services:
                <c:forEach var="service" items="${tariff.getListOfServiceName()}"><span><strong>*${service} </strong></span></c:forEach>
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
            <form action="${pageContext.request.contextPath}/tariff/tariffConnection">
                    <button type="submit" class="btn btn-primary" value="${tariff.getId()}" name="newTariffId">Connect</button>
            </form>
        </div>

    </div>

</c:forEach>

