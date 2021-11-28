<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ attribute name="tariff" required="true" type="com.internetProvider.model.Tariff" %>
<%@ attribute name="serviceList" required="true"%>

<t:tariff serviceList="${serviceList}" tariff="${tariff}">
    <div class="card tariff-card" style="width: 18rem;">
        <div class="card-body tariff-content">
            ${tariff}
            <h5 class="card-title tariff-title">"${tariff.name}"</h5>
            <p class="card-text tariff-subtitle">${tariff.description}</p>
            <div class="tariff-price">
                <div class="tariff-price-box">
                    <span>Price:</span>
                    <span>${tariff.price}$/${tariff.dayDuration}days</span>
                </div>
            </div>
            <div class="tariff-service-list">
                Services:
                <c:forEach var="service"
                           items="${serviceList}"><span><strong>*${service} </strong></span></c:forEach>
            </div>
        </div>
        <ul class="list-group list-group-flush list-of-features">
            <!-- TODO: MOVE INNER CSS IN  tariffs.css -->
            <c:forEach var="feature" items="${tariff.featuresList}">
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
</t:tariff>