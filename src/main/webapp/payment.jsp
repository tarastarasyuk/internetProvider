<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:page title="Payment" cssLink="../../assets/css/payment.css" >
    <main>
        <section class="payment">


            <div class="container">
                <div class="card">
                    <div class="form">
                        <div class="left-side"> <span class="success">Success</span>
                            <div class="image"> </div>
                            <div class="debit-card">
                                <div class="card_name"> <small>Cardholder name</small> <span class="user_name">Taras
                                        Tarasiuk</span> </div>
                                <div class="num_expiry">
                                    <div class="card_number"> <small>Card Number</small> <span
                                            class="set_card_number">0000
                                            0000 0000 0000</span> </div>
                                    <div class="card_cvv"> <small>Valid upto</small> <span
                                            class="user_card_cvv">MM/YYYY</span> </div>
                                </div>
                            </div>
                        </div>
                        <div class="right-side">
                            <h3>Payment details</h3>
                            <div class="input-text"> <input type="text" id="user_name_input"
                                                            onkeyup="userName(this.value)" placeholder="Taras Tarasiuk" required>
                                <span>Cardholder
                                    name</span>
                            </div>
                            <div class="input-text"> <input type="text" id="user_card_number_input"
                                                            placeholder="0000 0000 0000 0000" onkeyup="userCardNumber(this.value)"
                                                            data-slots="0" data-accept="\d" required> <span>Card number</span> </div>
                            <div class="input-div">
                                <div class="input-text"> <input type="text" onkeyup="usercardcvv(this.value)"
                                                                placeholder="MM/YYYY" data-slots="MY" required> <span>Valid upto</span> </div>
                                <div class="input-text "> <input type="text" placeholder="000" data-slots="0"
                                                                 data-accept="\d" size="3" required> <span>CVV</span> </div>
                            </div>

                            <form action="${pageContext.request.contextPath}/clientPanel/payment" method="POST">
                                <div class="input-text"> <input type="text" pattern="^[1-9][0-9]{1,4}$" placeholder="1000" required name="sum" title="Type only digits">
                                    <span>Sum</span>
                                </div>

                                <div class="button"> <button class="click-pay" type="submit">Pay</button></div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </main>

    <script src="../../assets/js/payment.js"></script>
</t:page>