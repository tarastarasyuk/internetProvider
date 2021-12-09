<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:page title="Login" cssLink="../../assets/css/manageTariffs.css">
    <main>

        <div class="container container-table">
            <section class="users-table">
                <h1 class="users-table-title">Tariffs Management</h1>

                <div class="user-table-subtitle">
                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-success connect-btn" data-bs-toggle="modal"
                            data-bs-target="#createTariff">
                        Add a new Tariff
                    </button>
                    <div class="search-filter">
                        <input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search by names.."
                               title="Type in a name">
                    </div>
                </div>

                <table class="table table-bordered" id="myTable">
                    <thead>
                    <tr>
                        <th class="td-name">Name</th>
                        <th class="td-description">Description</th>
                        <th class="td-price">Price</th>
                        <th class="td-daysDuration">Duration</th>
                        <th class="td-features">Features</th>
                        <th class="td-services">Services</th>
                        <th class="td-action">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="tariff" items="${tariffList}">
                        <tr>
                            <td class="td-name">${tariff.getName()}</td>
                            <td class="td-description">${tariff.getDescription()}</td>
                            <td class="td-price">${tariff.getPrice()}</td>
                            <td class="td-daysDuration">${tariff.getDayDuration()}</td>
                            <td class="td-features">
                                <c:forEach var="feature" items="${tariff.getFeaturesList()}">
                                    <span>${feature}</span><br>
                                </c:forEach>
                            </td>
                            <td class="td-services">
                                <c:forEach var="service" items="${tariff.getListOfServiceName()}">
                                    <span>${service}</span><br>
                                </c:forEach>
                            </td>
                            <td class="td-action">
                                <div>
                                    <button type="button" class="btn btn-primary connect-btn" data-bs-toggle="modal"
                                            data-bs-target="#modal-${tariff.getId()}">
                                        Edit
                                    </button>

                                </div>
                                <div>

                                    <button type="button" class="btn btn-danger connect-btn" data-bs-toggle="modal"
                                            data-bs-target="#deleteTariffModal-${tariff.getId()}">
                                        Delete
                                    </button>

                                </div>
                            </td>
                        </tr>

                    <!-- Modal Delete-->
                    <div class="modal fade" id="deleteTariffModal-${tariff.getId()}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="deleteTariffModalLabel">Do you really want to delete this tariff?</h5>
<%--                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">--%>
<%--                                        <span aria-hidden="true">&times;</span>--%>
<%--                                    </button>--%>
                                </div>
                                <div class="modal-body">
                                    All users that have this tariff will be INACTIVE if you delete current tariff                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>

                                    <form action="manageTariffs/deleteTariff" method="POST">
                                        <input name="deletedTariff" value="${tariff.getId()}" hidden>
                                        <button class="btn btn-danger connect-btn" type="submit">Delete</button>
                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>


                        <!-- Modal Tariff Editing -->
                        <div class="modal fade" id="modal-${tariff.getId()}" tabindex="-1" aria-labelledby="exampleModalEdit" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="editTariffLabel">Edit current tariff:</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body edit-modal-body">
                                        <form class="row g-3" method="post" action="manageTariffs/editCurrentTariff">

                                            <div class="col-md-6">
                                                <label for="validationDefault023" class="form-label">Name <span style="color:red;">*</span></label>
                                                <input value="${tariff.getId()}" name="tariffEditId" hidden>
                                                <input type="text" class="form-control" id="validationDefault023" placeholder="Name" value="${tariff.getName()}"
                                                       name="name" required>
                                            </div>
                                            <div class="col-md-6"></div>
                                            <div class="col-md-12">
                                                <label for="1validationDefault04" class="form-label">Short Description <span style="color:red;">*</span></label>
                                                <input type="text" class="form-control" id="1validationDefault04"
                                                       placeholder="Short Description" name="description" value="${tariff.getDescription()}" required>
                                            </div>
                                            <div class="col-md-6">
                                                <label for="2validationDefault052" class="form-label">Price <span style="color:red;">*</span></label>
                                                <input type="text" class="form-control" id="2validationDefault052" placeholder="Price"
                                                       name="price" value="${tariff.getPrice()}" pattern="^[1-9][0-9]*$" title="Type a number" required>
                                            </div>
                                            <div class="col-md-6">
                                                <label for="3validationDefault053" class="form-label">Days Duration <span style="color:red;">*</span></label>
                                                <input type="text" class="form-control" id="3validationDefault053" placeholder="Days Duration"
                                                       name="dayDuration" pattern="^[1-9][0-9]*$" title="Type a number" value="${tariff.getDayDuration()}" required>
                                            </div>
                                            <div class="col-md-12">
                                                <label for="serviceId" class="form-label">Services <span style="color:red;">*</span></label>
                                                <select class="form-select"  name="service" multiple required>
                                                    <c:forEach var="service" items="${serviceList}">
                                                        <option value="${service.getId()}" ${tariff.getListOfServiceName().contains(service.getName()) ? 'selected': ''}>${service.getName()}
                                                        </option>
                                                    </c:forEach>
                                                </select>

                                                <div class="invalid-feedback">
                                                    Please select a valid state.
                                                </div>
                                            </div>


                                            <label class="form-label">Features <span style="color:red;">*</span></label>
                                            <div class="newFeatures_${tariff.getId()}">
                                                <c:forEach var="feature" items="${tariff.getFeaturesList()}">
                                                    <div class="col-md-12 feature-input_${tariff.getId()}"><input type="text" class="form-control" placeholder="Feature" name="feature" value="${feature}" required></div>
                                                </c:forEach>
                                            </div>
                                                <%--                                TODO:  MAKE ADD AND DELETE FEATURES IN EDIT TARIFFS MODAL--%>
<%--                                            <div class="col-md-12 feature-buttons">--%>
<%--                                                <a class="btn btn-success" onclick="addInput('newFeatures_${tariff.getId()}', 'feature-input_${tariff.getId()}')">+ add--%>
<%--                                                    feature</a>--%>

<%--                                                <a class="btn btn-danger" onclick="removeInput('feature-input_${tariff.getId()}')">- remove--%>
<%--                                                    feature</a>--%>
<%--                                            </div>--%>


                                    <div class="col-md-4"></div>



                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                        <button class="btn btn-primary" type="submit">Edit tariff</button>
                                    </div>
                                    </form>
                                </div>

                            </div>
                        </div>


                    </c:forEach>

                    </tbody>
                </table>
            </section>


            <!-- Modal Tariff Creating -->
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
        </div>
    </main>



    <script>
        function myFunction() {
            const d = document.getElementsByClassName("feature-input").length
            console.log(d)
            var input, filter, table, tr, td, i, txtValue;
            input = document.getElementById("myInput");
            filter = input.value.toUpperCase();
            table = document.getElementById("myTable");
            tr = table.getElementsByTagName("tr");
            for (i = 0; i < tr.length; i++) {
                td = tr[i].getElementsByTagName("td")[0];
                if (td) {
                    txtValue = td.textContent || td.innerText;
                    if (txtValue.toUpperCase().indexOf(filter) > -1) {
                        tr[i].style.display = "";
                    } else {
                        tr[i].style.display = "none";
                    }
                }
            }
        }


        function addInput(newFeatures, featureInput) {
            var new_input = '<div class="col-md-12 '+featureInput+'"><input type="text" class="form-control" placeholder="Feature" name="feature" required></div>';
            $('.'+newFeatures).append(new_input);
        }
        function removeInput(featureInput) {
            const last_chq_no = $("."+featureInput).length
            console.log(last_chq_no)
            if (last_chq_no > 1) {
                $("."+featureInput)[last_chq_no - 1].parentNode.removeChild($("."+featureInput)[last_chq_no - 1]);
            }
        }

    </script>

</t:page>