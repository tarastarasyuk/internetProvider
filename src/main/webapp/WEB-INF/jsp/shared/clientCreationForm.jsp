<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:page title="Manage Tariffs" cssLink="../../assets/css/clientCreationForm.css">
    <main>
        <div class="container">

            <a href="/adminPanel/manageClients"><button class="back-to-btn btn btn-primary">
                <i class="fas fa-arrow-left"></i>
                Back
            </button></a>

            <section class="client-creation">

                <div class="client-creation-title">
                    <h3>User Create Form</h3>
                </div>

                <hr>

                <div class="modal-body">
                    <form class="row g-3" method="POST" action="${sessionScope.user.roleId == 2 ? '......change....' : 'clientCreationForm/addNewClient'}">

                        <div class="col-md-6">
                            <label class="form-label">Username<span style="color:red;">*</span></label>
                            <input type="text" class="form-control" placeholder="username" name="username" value="${sessionScope.user.roleId == 2 ? sessionScope.user.username : ''}" required>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Password <span style="color:red;">*</span></label>
                            <input type="password" class="form-control" placeholder="********" name="password" required>
                        </div>


                        <div class="col-md-12">
                            <label class="form-label">Email</label>
                            <input type="email" class="form-control" placeholder="email" name="email" value="${sessionScope.user.roleId == 2 ? sessionScope.user.email : ''}">
                        </div>

                        <div class="col-md-6">
                            <label for="cityId" class="form-label">City <span style="color:red;">*</span></label>
                            <select class="form-select" id="cityId" name="cityId" required>
                                <option selected disabled value="">Choose your city</option>
                                <c:choose>
                                    <c:when test="${sessionScope.user.roleId == 2}">
                                        <c:forEach var="city" items="${cityList}">
                                            <option value="${city.getId()}"  ${sessionScope.user.cityId == city.getId() ? 'selected' : ''}>${city.getCityName()}
                                            </option>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach var="city" items="${cityList}">
                                            <option value="${city.getId()}">${city.getCityName()}
                                            </option>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>

                            </select>

                        </div>
                        <div class="modal-footer ">
                            <button class="btn btn-primary" type="submit">Register</button>
                        </div>
                    </form>
                </div>

            </section>

        </div>
    </main>
</t:page>