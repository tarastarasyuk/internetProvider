<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.locale.value}"/>
<fmt:setBundle basename="titles"/>
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
                                    <option value="" ${selectedSortBy == null ? 'selected':''} disabled hidden><fmt:message key="action.sort_by"/></option>
                                    <option value="price_desc" ${selectedSortBy.equals("price_desc")? 'selected': ''}><fmt:message key="action.sort_by_price_high_to_low"/></option>
                                    <option value="price_asc" ${selectedSortBy.equals("price_asc")? 'selected': ''}><fmt:message key="action.sort_by_price_low_to_high"/></option>
                                    <option value="name_asc" ${selectedSortBy.equals("name_asc")? 'selected': ''}><fmt:message key="action.sort_by_a_z"/></option>
                                    <option value="name_desc" ${selectedSortBy.equals("name_desc")? 'selected': ''}><fmt:message key="action.sort_by_z_a"/></option>
                                </select>
                            </div>

                            <div class="filter-apply">
                            <a href="#" >
                                    <button type="submit"
                                            class="btn btn-primary"><fmt:message key="action.apply"/>
                                    </button>
                                </a>
                            </div>

                        </form>

                    </div>

                    <div class="filter-clear-btn">
                        <a href="tariffs">
                            <button type="button" class="btn btn-secondary clear-btn"
                                    onClick="window.location.reload();"><fmt:message key="action.clear"/> &#x2717
                            </button>
                        </a>
                    </div>

                </div>

            </section>


            <section class="tariffs">
                ${noSuchTariffs}
                <t:tariff></t:tariff>
<%--                    TODO: .TLD--%>
            </section>
        </div>
    </main>
</t:page>