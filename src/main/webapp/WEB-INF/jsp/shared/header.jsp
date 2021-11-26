<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<header class="site-header fixed-top py-0">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">


        <div class="container-fluid justify-content-around">

            <div><a class="navbar-brand" href="#"><img src="../../../assets/img/iProvider_logo.png" alt=""></a></div>

            <div>
                <ul class="nav justify-content-center">
                    <li class="nav-item">
                        <a class="nav-link active" href="index.jsp">About</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="coverage.html">Coverage</a>
                    </li>
                    <li class=" nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            Service
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item d-flex align-items-center gap-2 py-2"
                                   href="services"><span class="d-inline-block bg-primary rounded-circle"
                                                              style="width: .5em; height: .5em;"></span>Services</a></li>
                            <li><a class="dropdown-item d-flex align-items-center gap-2 py-2" href="#"><span
                                    class="d-inline-block bg-primary rounded-circle"
                                    style="width: .5em; height: .5em;"></span>Tariffs</a></li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Contacts</a>
                    </li>
                </ul>
            </div>

            <div>
                <a href="${pageContext.request.contextPath}/login"><button class="btn login-btn" type="submit">Login</button></a>
                <button class="btn btn-primary connect-btn" type="submit">Connect</button>

            </div>

        </div>
        <button class="nav-item dropdown language-btn">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"
               aria-expanded="false">
                UA <i class="fas fa-globe"></i>
            </a>
            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                <li><a class="dropdown-item d-flex align-items-center gap-2 py-2" href="#"><span
                        class="d-inline-block bg-primary rounded-circle"
                        style="width: .5em; height: .5em;"></span>Українська</a></li>
                <li><a class="dropdown-item d-flex align-items-center gap-2 py-2" href="#"><span
                        class="d-inline-block bg-primary rounded-circle"
                        style="width: .5em; height: .5em;"></span>English</a></li>
            </ul>
        </button>
    </nav>
</header>