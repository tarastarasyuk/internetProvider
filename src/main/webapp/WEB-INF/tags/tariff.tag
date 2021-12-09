<%@ tag import="com.internetProvider.model.Tariff" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="tariff" items="${tariffList}">

    <div class="card tariff-card" style="width: 18rem;">

        <div class="card-body tariff-content">
            <h5 class="card-title tariff-title">"${tariff.getName()}"
                <form action="/tariffs/downloadTariff" method="get" style="position: absolute; right: 5px; top: 5px; border: none; color: blue;">
                    <input value="${tariff.getId()}" name="tariff_id_download" hidden>
                    <button type="submit"  style="background: none; border: none; color: green;"><i class="fas fa-file-download"></i></button>
                </form></h5>
            <p class="card-text tariff-subtitle">${tariff.getDescription()}</p>

            <div class="tariff-price">
                <div class="tariff-price-box">
                    <span>Price:</span>
                    <span>${tariff.getPrice()}$/${tariff.getDayDuration()}days</span>
                </div>
            </div>

            <div class="tariff-service-list">
                Services:
                <c:forEach var="service"
                           items="${tariff.getListOfServiceName()}"><span><strong>*${service} </strong></span></c:forEach>
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
            <c:choose>
                <c:when test='${user == null}'>
                    <button type="submit" class="btn btn-primary"
                            data-bs-toggle="modal" data-bs-target="#noAccount">
                        Connect
                    </button>
                </c:when>
                <c:when test='${user != null}'>


                    <c:choose>
                        <c:when test='${sessionScope.user.status.toString().equals("BLOCKED")}'>
                            <button type="submit" class="btn btn-primary"
                                    data-bs-toggle="modal" data-bs-target="#blockedClient">
                                Connect
                            </button>
                        </c:when>

                        <c:when test='${!sessionScope.user.status.toString().equals("BLOCKED")}'>

                            <c:choose>
                                <c:when test="${sessionScope.user.account.compareTo(tariff.getPrice()) >= 0}">
                                    <c:choose>
                                        <c:when test="${user.getTariffId() != 0}">
                                            <%--                            TODO: MAKE MESSAGE FOR USERS THAT ALREADY HAVE TARIFF--%>
                                            <%--                            <button type="submit" class="btn btn-primary"--%>
                                            <%--                                    data-bs-toggle="modal" data-bs-target="#tariffExistence">--%>
                                            <%--                                Connect--%>
                                            <%--                            </button>--%>
                                            <form action="${pageContext.request.contextPath}/clientPanel/tariffConnection"
                                                  method="GET">
                                                <button type="submit" class="btn btn-primary" value="${tariff.getId()}"
                                                        name="newTariffId">
                                                    Connect
                                                </button>
                                            </form>
                                        </c:when>
                                        <c:when test="${user.getTariffId() == 0}">
                                            <form action="${pageContext.request.contextPath}/clientPanel/tariffConnection"
                                                  method="GET">
                                                <button type="submit" class="btn btn-primary" value="${tariff.getId()}"
                                                        name="newTariffId">
                                                    Connect
                                                </button>
                                            </form>
                                        </c:when>
                                    </c:choose>
                                </c:when>
                                <c:when test="${sessionScope.user.account.compareTo(tariff.getPrice()) == -1}">
                                    <button type="submit" class="btn btn-primary"
                                            data-bs-toggle="modal" data-bs-target="#notEnoughMoney">
                                        Connect
                                    </button>
                                </c:when>
                            </c:choose>
                        </c:when>

                    </c:choose>


                </c:when>
            </c:choose>

        </div>

    </div>


    <%--    <!-- Modal DON'T HAVE AN ACCOUNT-->--%>
    <%--    <div  class="modal fade" id="tariffExistence" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"--%>
    <%--         aria-hidden="true">--%>
    <%--        <div class="modal-dialog modal-dialog-centered" role="document">--%>
    <%--            <div class="modal-content">--%>
    <%--                <div class="modal-header">--%>
    <%--                    <h5 class="modal-title" id="exampleModalCenterTitle">You already have a tariff:</h5>--%>
    <%--                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>--%>
    <%--                </div>--%>
    <%--                <div class="modal-body">--%>
    <%--                    You can only have one tariff for one account. <br>--%>
    <%--                    If you want to change your current tariff - just continue.--%>
    <%--                </div>--%>
    <%--                <div class="modal-footer" style="justify-content: space-between;">--%>
    <%--                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>--%>
    <%--                    <form action="${pageContext.request.contextPath}/tariff/tariffConnection" method="GET">--%>
    <%--                        <button type="submit" class="btn btn-primary" value="${tariff.getId()}" name="newTariffId">--%>
    <%--                            Connect--%>
    <%--                        </button>--%>
    <%--                    </form>--%>
    <%--                </div>--%>
    <%--            </div>--%>
    <%--        </div>--%>

    <%--    </div>--%>


    <!-- Modal DON'T HAVE AN ACCOUNT-->
    <div class="modal fade" id="noAccount" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="noAccountTitle">You need to login to connect the tariff:</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    If you do not have an account you can make a request to our managers for registration you in our
                    company. <br>
                    If you have an account you need to just login and then connect the tariff.
                </div>
                <div class="modal-footer" style="justify-content: space-between;">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                    <div class="action-buttons">
                        <a href="${pageContext.request.contextPath}/login" style="color: #fff;">
                            <button type="button" class="btn login-btn">Login</button>
                        </a>
                        <a href="">
                            <button type="button" class="btn btn-primary">Register</button>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal BLOCKED USER-->
    <div class="modal fade" id="blockedClient" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="blockedUserTitle">Your status is BLOCKED! Try to connect support to solve problem!</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">

                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal DON'T HAVE ENOUGH MONEY-->
    <div class="modal fade" id="notEnoughMoney" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="notEnoughMoneyTitle">You don't have enough money to connect this
                        tariff...</h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">

                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                    <a href="${pageContext.request.contextPath}/payment" style="color: #fff;">
                        <button type="button" class="btn btn-success">Top up account</button>
                    </a>
                </div>
            </div>
        </div>
    </div>
</c:forEach>

