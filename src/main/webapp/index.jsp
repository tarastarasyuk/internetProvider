<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>

    <style>
        * {
            padding: 0;
            margin: 0;
        }
        .header {
            font-size: 38px;
            color: #fff;
            font-weight: bold;
            text-align: center;
            align-items: center;
            top: 0;
            width: 100%;
            height: 80px;
            background-color: dodgerblue;
        }
        .main {
            display: flex;
            justify-content: center;
            align-items: center;
        }
    </style>
</head>
<body>
<header class="header">Internet-provider</header>
<main class="main">
<%--    <%= request.getAttribute("signInError")%>--%>

    <form action="<%= request.getContextPath()%>/SingInServlet" method="post" class="form" >
        <p>${signInError}</p>
        <br>
        <br>
        <br>
        <h3>Sign in</h3>
        <br>
        <br>
        <span>Username</span>
        <input type="text" name="username">
        <br>
        <br>
        <span>Password  </span>
        <input type="password" name="password">
        <br>
        <br>
        <input type="submit" value="Sign in">
        <br>
        <br>
        <br>
        <br>
        <a href="">Don't have an account? Sign up now!</a>
    </form>



</main>
<footer></footer>
</body>
</html>