<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:page title="Login" cssLink="../../assets/css/tariffs.css">
    <main>
        <div class="container">

            <section class="filter">

                <div class="tariffs-title">
                    <h1>Tariffs</h1>
                </div>

                <div class="filters-line">

                    <div class="filter-choosing">
                        <form action="">

                            <div class="filters-logo"><i class="fas fa-sliders-h fa-lg"></i></span><span></div>
                            <div class="filters">
                                <select class="form-select" name="Services" id="by-service">
                                    <option value="" selected disabled hidden>Services</option>
                                    <option value="">All</option>
                                </select>
                                <select class="form-select" name="Price" id="by-price">
                                    <option value="" selected disabled hidden>Sort by</option>
                                    <option value="">Price High To Low</option>
                                    <option value="">Price Low To High</option>
                                    <option value="">A-Z</option>
                                    <option value="">Z-A</option>
                                </select>
                            </div>

                            <div class="filter-apply">
                                <a href="#" method="GET"><button type="button"
                                                                 class="btn btn-primary">Apply</button></a>
                            </div>

                        </form>

                    </div>

                    <div class="filter-clear-btn">
                        <a href="tariffs.html"><button type="button" class="btn btn-secondary clear-btn"
                                                       onClick="window.location.reload();">Clear &#x2717</button></a>
                    </div>

                </div>

            </section>


            <section class="tariffs">


                <div class="card tariff-card" style="width: 18rem;">
                    <!-- <img src="LOGO.png" style="object-fit:contain; height: 80px;" class="card-img-top" alt="..."> -->
                    <div class="card-body tariff-content">
                        <h5 class="card-title tariff-title">"Silver Pro Max"</h5>
                        <p class="card-text tariff-subtitle">Phone: Tariff plan for all family!</p>

                        <div class="tariff-price">
                            <div class="tariff-price-box">
                                <span>Price:</span>
                                <span>50$/month</pan>
                            </div>
                        </div>
                    </div>
                    <ul class="list-group list-group-flush list-of-features">
                        <!-- TODO: MOVE INNER CSS IN  tariffs.css -->
                        <li class="list-group-item">

                            <span class="d-inline-block bg-primary rounded-circle"
                                  style="width: .5em; height: .5em;"></span>
                            15GB of Intenet
                        </li>
                        <li class="list-group-item">
                            <span class="d-inline-block bg-primary rounded-circle"
                                  style="width: .5em; height: .5em;"></span>
                            Unlimitted calls
                        </li>
                        <li class="list-group-item">
                            <span class="d-inline-block bg-primary rounded-circle"
                                  style="width: .5em; height: .5em;"></span>
                            A third item
                        </li>
                        <li class="list-group-item">
                            <span class="d-inline-block bg-primary rounded-circle"
                                  style="width: .5em; height: .5em;"></span>
                            A third item
                        </li>

                    </ul>
                    <div class="card-body tariffs-connection">
                        <a href="#" method="GET">
                            <button type="button" class="btn btn-primary">Connect</button>
                        </a>
                    </div>
                </div>


                <div class="card tariff-card" style="width: 18rem;">
                    <!-- <img src="LOGO.png" style="object-fit:contain; height: 80px;" class="card-img-top" alt="..."> -->
                    <div class="card-body tariff-content">
                        <h5 class="card-title tariff-title">"Silver Pro Max"</h5>
                        <p class="card-text tariff-subtitle">Phone: Tariff plan for all family!</p>

                        <div class="tariff-price">
                            <div class="tariff-price-box">
                                <span>Price:</span>
                                <span>50$/month</span>
                            </div>
                        </div>
                    </div>
                    <ul class="list-group list-group-flush list-of-features">
                        <!-- TODO: MOVE INNER CSS IN  tariffs.css -->
                        <li class="list-group-item">

                            <span class="d-inline-block bg-primary rounded-circle"
                                  style="width: .5em; height: .5em;"></span>
                            15GB of Intenet
                        </li>
                        <li class="list-group-item">
                            <span class="d-inline-block bg-primary rounded-circle"
                                  style="width: .5em; height: .5em;"></span>
                            Unlimitted calls
                        </li>
                        <li class="list-group-item">
                            <span class="d-inline-block bg-primary rounded-circle"
                                  style="width: .5em; height: .5em;"></span>
                            A third item
                        </li>
                    </ul>
                    <div class="card-body tariffs-connection">
                        <a href="#" method="GET">
                            <button type="button" class="btn btn-primary">Connect</button>
                        </a>
                    </div>
                </div>

                <div class="card tariff-card" style="width: 18rem;">
                    <!-- <img src="LOGO.png" style="object-fit:contain; height: 80px;" class="card-img-top" alt="..."> -->
                    <div class="card-body tariff-content">
                        <h5 class="card-title tariff-title">"Silver Pro Max"</h5>
                        <p class="card-text tariff-subtitle">Phone: Tariff plan for all family!</p>

                        <div class="tariff-price">
                            <div class="tariff-price-box">
                                <span>Price:</span>
                                <span>50$/month</span>
                            </div>
                        </div>
                    </div>
                    <ul class="list-group list-group-flush list-of-features">
                        <!-- TODO: MOVE INNER CSS IN  tariffs.css -->
                        <li class="list-group-item">

                            <span class="d-inline-block bg-primary rounded-circle"
                                  style="width: .5em; height: .5em;"></span>
                            15GB of Intenet
                        </li>
                        <li class="list-group-item">
                            <span class="d-inline-block bg-primary rounded-circle"
                                  style="width: .5em; height: .5em;"></span>
                            Unlimitted calls
                        </li>

                    </ul>
                    <div class="card-body tariffs-connection">
                        <a href="#" method="GET">
                            <button type="button" class="btn btn-primary">Connect</button>
                        </a>
                    </div>
                </div>

                <div class="card tariff-card" style="width: 18rem;">
                    <!-- <img src="LOGO.png" style="object-fit:contain; height: 80px;" class="card-img-top" alt="..."> -->
                    <div class="card-body tariff-content">
                        <h5 class="card-title tariff-title">"Silver Pro Max"</h5>
                        <p class="card-text tariff-subtitle">Phone: Tariff plan for all family!</p>

                        <div class="tariff-price">
                            <div class="tariff-price-box">
                                <span>Price:</span>
                                <span>50$/month</span>
                            </div>
                        </div>
                    </div>
                    <ul class="list-group list-group-flush list-of-features">
                        <!-- TODO: MOVE INNER CSS IN  tariffs.css -->
                        <li class="list-group-item">

                            <span class="d-inline-block bg-primary rounded-circle"
                                  style="width: .5em; height: .5em;"></span>
                            15GB of Intenet
                        </li>
                        <li class="list-group-item">
                            <span class="d-inline-block bg-primary rounded-circle"
                                  style="width: .5em; height: .5em;"></span>
                            Unlimitted calls
                        </li>
                        <li class="list-group-item">
                            <span class="d-inline-block bg-primary rounded-circle"
                                  style="width: .5em; height: .5em;"></span>
                            A third item
                        </li>

                    </ul>
                    <div class="card-body tariffs-connection">
                        <a href="#" method="GET">
                            <button type="button" class="btn btn-primary">Connect</button>
                        </a>
                    </div>
                </div>


            </section>
        </div>
    </main>
</t:page>