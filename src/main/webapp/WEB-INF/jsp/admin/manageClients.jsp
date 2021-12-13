<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:page title="User Managment" cssLink="../../assets/css/manageClients.css">
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
                <a href="${pageContext.request.contextPath}/adminPanel"><button class="back-to-btn btn btn-primary">
                    <i class="fas fa-arrow-left"></i>
                    Back
                </button></a>
                <h1 class="users-table-title">Client Management</h1>
                <hr>
                <div class="user-table-subtitle">

                    <form action="manageClients/clientCreationForm" method="GET">
                        <button type="submit" class="btn btn-success connect-btn">
                            Add a new Client
                        </button>
                    </form>

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

                <t:pagination link="/adminPanel/manageClients"></t:pagination>
            </section>
        </div>
    </main>

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