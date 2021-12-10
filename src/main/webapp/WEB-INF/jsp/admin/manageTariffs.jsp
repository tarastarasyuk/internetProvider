<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:page title="Login" cssLink="../../assets/css/manageTariffs.css">
    <main>

        <div class="container container-table">

            <a href="${pageContext.request.contextPath}/adminPanel"><button class="back-to-btn btn btn-primary">
                <i class="fas fa-arrow-left"></i>
                Back
            </button></a>

            <section class="users-table">
                <h1 class="users-table-title">Tariffs Management</h1>
                <hr>

                <div class="user-table-subtitle">
                    <!-- Button trigger modal -->
                    <form action="manageTariffs/tariffCreationForm" method="GET">
                        <button type="submit" class="btn btn-success connect-btn">
                            Add a new Tariff
                        </button>
                    </form>

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

                                    <form action="manageTariffs/tariffCreationForm" method="GET">
                                        <input name="editedTariffId" value="${tariff.getId()}" hidden>
                                        <button type="submit" class="btn btn-primary connect-btn">
                                            Edit
                                        </button>
                                    </form>

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
                    </c:forEach>

                    </tbody>
                </table>
            </section>
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
    </script>

</t:page>