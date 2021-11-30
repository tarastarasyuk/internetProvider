<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:page title="Login" cssLink="">
    <h1>AdminPanel</h1>
    <!-- Button trigger modal -->
    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
        Add a new Client
    </button>

    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Register a new client:</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form class="row g-3" method="post" action="${pageContext.request.contextPath}/addNewClient">

                        <div class="col-md-4">
                            <label for="validationDefault03" class="form-label">Username</label>
                            <input type="text" class="form-control" id="validationDefault03" value="Otto" name="username" required>
                        </div>
                        <div class="col-md-4">
                            <label for="validationDefault04" class="form-label">Password</label>
                            <input type="text" class="form-control" id="validationDefault04" value="Otto" name="password" required>
                        </div>
                        <div class="col-md-4"></div>

                        <div class="col-md-8">
                            <label for="validationDefault05" class="form-label">Email</label>
                            <input type="text" class="form-control" id="validationDefault05" value="Otto" name="email" required>
                        </div>
                        <div class="col-md-4"></div>

                        <div class="col-md-3">
                            <label for="cityId" class="form-label">City</label>
                            <select class="form-select" id="cityId" name="cityId" required>
                                <option selected disabled value="">Choose your city</option>
                                <c:forEach var="city" items="${cityList}">
                                    <option value="${city.getId()}">${city.getCityName()}
                                    </option>
                                </c:forEach>
                            </select>

                            <div class="invalid-feedback">
                                Please select a valid state.
                            </div>
                        </div>

                        <div class="col-12">
                            <button class="btn btn-primary" type="submit">Submit form</button>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                    <button type="button" class="btn btn-primary">Register</button>
                </div>
            </div>
        </div>
    </div>
    <br>
    <br>
    <br>
    <br>
    <br>
   <div style="display: flex; justify-content: center;"><a href="/logout">Logout</a></div>

</t:page>

