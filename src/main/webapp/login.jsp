<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:page title="Login" cssLink="../../assets/css/login.css">
    <main>
        <div class="container d-flex flex-column justify-content-center align-items-center " style="height: 80vh;">
            <div class="gg d-flex justify-content-center align-items-center"
                 style="width: 30%; height: 70%; background-color: #F2F3F5; border-radius: 6px;">
                <form class="form" method="post" action="${pageContext.request.contextPath}/clientPanel">
                    <div class="d-flex justify-content-center user-icon">
                        <i class="far fa-user fa-9x"></i>
                    </div>
                    <div class="mb-3">
                        <input name="username" placeholder="Username" type="name" class="form-control" id="exampleInputEmail1"
                               aria-describedby="emailHelp" required>
                    </div>
                    <div class="mb-3">
                        <input name="password" placeholder="Password" type="password" class="form-control" id="exampleInputPassword1" required>
                    </div>
                    <button type="submit" class="btn btn-primary" style="width: 100%;">Login</button>

                    <p style="color: #DC3545; text-align: center; position: relative;"><br>${signInError}</p>
                    <p style="color: #DC3545; text-align: center; position: relative;"><br>${session.getAttribute("signInError")}</p>
                </form>
            </div>
        </div>
    </main>
</t:page>