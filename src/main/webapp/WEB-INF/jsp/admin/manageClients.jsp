<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:page title="User Managment" cssLink="../../assets/css/manageUsers.css">
    <style>
        input {
            background: none;
            border: 0;
            outline: none;
            padding: 0;
            margin: 0;
        }
        form {
            padding: 0;
            margin: 0;
        }
    </style>
    <main>

        <div class="container container-table">
            <section class="users-table">
                <h1 class="users-table-title">Client Management</h1>
                <div class="user-table-subtitle">

                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-success connect-btn" data-bs-toggle="modal"
                            data-bs-target="#exampleModal">
                        Add a new Client
                    </button>
                    <div class="search-filter">
                        <input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search by usernames.."
                               title="Type in a name">
                    </div>
                </div>
                <table class="table table-bordered" id="myTable">
                    <thead>
                    <tr>
                        <th colspan="1" class="td-username">Username</th>
                        <th colspan="1" class="td-email">Email</th>
                        <th colspan="1" class="td-city">City</th>
                        <th colspan="1" class="td-status">Status</th>
                        <th colspan="1" class="td-action">Actions</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach var="client" items="${clientList}">
                        <tr>
                            <td class="td-username">${client.getUsername()}</td>
                            <td class="td-email">${client.getEmail()}</td>
                            <td class="td-city">${client.getCityName()}</td>
                            <td class="td-status"><span>${client.getStatus()}</span></td>
                            <td class="td-action">
                                <form action="manageClients/changeUserStatus" method="POST">
                                    <input name="clientId" value="${client.getId()}" hidden>
                                    <input name="clientStatus" value="${client.getStatus()}" hidden>
                                    <c:choose>
                                        <c:when test='${client.getStatus().toString().equals("BLOCKED")}'>
                                            <input class="unblock-btn" type="submit" value="UNBLOCK">
                                        </c:when>
                                        <c:otherwise>
                                            <input class="block-btn" type="submit" value="BLOCK">
                                        </c:otherwise>
                                    </c:choose>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </section>
        </div>
    </main>

    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Register a new client:</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form class="row g-3" method="post"
                          action="manageClients/addNewClient">

                        <div class="col-md-4">
                            <label for="validationDefault03" class="form-label">Username <span style="color:red;">*</span></label>
                            <input type="text" class="form-control" id="validationDefault03" placeholder="username"
                                   name="username" required>
                        </div>
                        <div class="col-md-4">
                            <label for="validationDefault04" class="form-label">Password <span style="color:red;">*</span></label>
                            <input type="password" class="form-control" id="validationDefault04" placeholder="password"
                                   name="password" required>
                        </div>
                        <div class="col-md-4"></div>

                        <div class="col-md-8">
                            <label for="validationDefault05" class="form-label">Email</label>
                            <input type="email" class="form-control" id="validationDefault05" placeholder="email" name="email">
                        </div>
                        <div class="col-md-4"></div>

                        <div class="col-md-3">
                            <label for="cityId" class="form-label">City <span style="color:red;">*</span></label>
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
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                            <button class="btn btn-primary" type="submit">Register</button>
                        </div>
                    </form>
                </div>

            </div>
        </div>
    </div>


    <script>
        function myFunction() {
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