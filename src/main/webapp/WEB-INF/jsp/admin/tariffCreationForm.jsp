<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:page title="Manage Tariffs" cssLink="../../assets/css/tariffCreationForm.css">
    <main>

        <div class="container">

            <a href="${pageContext.request.contextPath}/adminPanel/manageTariffs"><button class="back-to-btn btn btn-primary">
                <i class="fas fa-arrow-left"></i>
                Back
            </button></a>

            <section class="tariff-creation">


                <div class="tariff-creation-title">
                    <h3>
                        ${tariff == null ? 'Tariff Create Form' : 'Tariff Edit Form'}</h3>
                </div>

                <hr>

                <div class="modal-body">
                    <form class="row g-3" method="post" action="tariffCreationForm/${tariff == null ? 'addNewTariff' : 'editCurrentTariff'}">

                        <div class="col-md-6">
                            <label class="form-label">Name <span class="required-field">*</span></label>
                            <input type="text" class="form-control" placeholder="Name" name="name" value="${tariff.getName()}" required>
                        </div>
                        <div class="col-md-6"></div>
                        <div class="col-md-12">
                            <label class="form-label">Short Description <span class="required-field">*</span></label>
                            <input type="text" class="form-control" placeholder="Short Description" name="description" value="${tariff.getDescription()}"
                                   required>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Price <span class="required-field">*</span></label>
                            <input type="text" class="form-control" placeholder="Price" name="price" required
                                   pattern="^[1-9][0-9]{1,3}$" title="Type a number" value="${tariff.getPrice()}">
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Days Duration <span class="required-field">*</span></label>
                            <input type="text" class="form-control" placeholder="Days Duration" name="dayDuration"
                                   required pattern="^[1-9][0-9]{1}$" title="Type a number" value="${tariff.getDayDuration()}">
                        </div>
                        <div class="col-md-12">
                            <label for="serviceId" class="form-label">Services <span
                                    class="required-field">*</span></label>
                            <select class="form-select" id="serviceId" name="service" multiple required>
                                <c:forEach var="service" items="${serviceList}">
                                    <option value="${service.getId()}" ${tariff.getListOfServiceName().contains(service.getName()) ? 'selected': ''}>${service.getName()}
                                    </option>
                                </c:forEach>
                            </select>

                        </div>


                        <div class="col-md-12">

                            <label class="form-label">Features <span class="required-field">*</span></label>
                            <div class="newFeaturesCreate">
                                <c:choose>
                                    <c:when test="${tariff == null}">
                                        <div class="col-md-12 feature-input-create"><input type="text" class="form-control" placeholder="Feature" name="feature" required></div>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach var="feature" items="${tariff.getFeaturesList()}">
                                            <div class="col-md-12 feature-input_${tariff.getId()} feature-input-create"><input type="text" class="form-control" placeholder="Feature" name="feature" value="${feature}" required></div>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>

                            </div>
                            <div class="col-md-12 feature-buttons">
                                <a class="btn btn-success"
                                   onclick="addInput('newFeaturesCreate', 'feature-input-create')">+
                                    add
                                    feature</a>

                                <a class="btn btn-danger" onclick="removeInput('feature-input-create')">- remove
                                    feature</a>
                            </div>

                        </div>


                        <div class="modal-footer">
                            <input name="tariffEditId" value="${tariff.getId()}" hidden>
                            <button class="btn btn-primary" type="submit">
                                    ${tariff == null ? 'Add tariff' : 'Edit tariff'}
                            </button>
                        </div>
                    </form>
                </div>


            </section>
        </div>

    </main>


    <script>
        function addInput(newFeatures, featureInput) {
            const new_input = '<div class="col-md-12 ' + featureInput + ' feature-form-filed "><input type="text" class="form-control" placeholder="Feature" name="feature" required></div>';
            $('.' + newFeatures).append(new_input);
        }
        function removeInput(featureInput) {
            const last_chq_no = $("." + featureInput).length
            console.log(last_chq_no)
            if (last_chq_no > 1) {
                $("." + featureInput)[last_chq_no - 1].parentNode.removeChild($("." + featureInput)[last_chq_no - 1]);
            }
        }
    </script>
</t:page>