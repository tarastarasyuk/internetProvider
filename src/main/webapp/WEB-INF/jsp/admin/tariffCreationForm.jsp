<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:page title="Manage Tariffs" cssLink="../../assets/css/manageTariffs.css">
    <main>
        <!-- Modal Tariff Creation form -->
        <div class="modal fade" id="createTariff" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="createTariffLabel">Create a new tariff:</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form class="row g-3" method="post" action="manageTariffs/addNewTariff">

                            <div class="col-md-6">
                                <label for="validationDefault03" class="form-label">Name <span style="color:red;">*</span></label>
                                <input type="text" class="form-control" id="validationDefault03" placeholder="Name"
                                       name="name" required>
                            </div>
                            <div class="col-md-6"></div>
                            <div class="col-md-12">
                                <label for="validationDefault04" class="form-label">Short Description <span style="color:red;">*</span></label>
                                <input type="text" class="form-control" id="validationDefault04"
                                       placeholder="Short Description" name="description" required>
                            </div>
                            <div class="col-md-6">
                                <label for="validationDefault052" class="form-label">Price <span style="color:red;">*</span></label>
                                <input type="text" class="form-control" id="validationDefault052" placeholder="Price"
                                       name="price" required pattern="^[1-9][0-9]*$" title="Type a number">
                            </div>
                            <div class="col-md-6">
                                <label for="validationDefault053" class="form-label">Days Duration <span style="color:red;">*</span></label>
                                <input type="text" class="form-control" id="validationDefault053" placeholder="Days Duration"
                                       name="dayDuration" required pattern="^[1-9][0-9]*$" title="Type a number">
                            </div>
                            <div class="col-md-12">
                                <label for="serviceId" class="form-label">Services <span style="color:red;">*</span></label>
                                <select class="form-select" id="serviceId" name="service" multiple required>
                                    <c:forEach var="service" items="${serviceList}">
                                        <option value="${service.getId()}">${service.getName()}
                                        </option>
                                    </c:forEach>
                                </select>

                                <div class="invalid-feedback">
                                    Please select a valid state.
                                </div>
                            </div>


                            <label class="form-label">Features <span style="color:red;">*</span></label>
                            <div class="newFeaturesCreate">
                                <div class="col-md-12 feature-input-create"><input type="text" class="form-control" placeholder="Feature" name="feature" required></div>
                            </div>
                            <div class="col-md-12 feature-buttons">
                                <a class="btn btn-success" onclick="addInput('newFeaturesCreate', 'feature-input-create')">+ add
                                    feature</a>

                                <a class="btn btn-danger" onclick="removeInput('feature-input-create')">- remove
                                    feature</a>
                            </div>




                            <div class="col-md-4"></div>



                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                <button class="btn btn-primary" type="submit">Add tariff</button>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </main>
</t:page>