<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart - SecondChance Tech</title>

    <link rel="stylesheet" href="css/layout.css" />
    <link rel="stylesheet" href="css/variables.css" />
    <link rel="stylesheet" href="css/shoppingcart.css" />
</head>

<body>
    <jsp:include page="header.jsp" />

    <!-- Main Content -->
    <main class="page-content">
        <div class="cart-container">
            <!-- Left Side: Items -->
            <section class="cart-items-section">
                <h1 class="cart-title">Shopping Cart</h1>
                <div class="item-list" id="itemList">
                    <!-- Items will be rendered here by JavaScript -->
                </div>
            </section>

            <!-- Right Side: Summary -->
            <aside class="order-summary-section">
                <h2 class="summary-title">Order Summary</h2>
                <div class="price-rows">
                    <div class="price-row">
                        <span>Subtotal</span>
                        <span id="subtotalAmount">RM 0</span>
                    </div>
                    <div class="price-row" style="color: var(--text-secondary)">
                        <span>Estimate Shipping Fee</span>
                        <span id="shippingAmount">RM 17</span>
                    </div>
                    <div class="price-row" style="margin-top: 10px; font-weight: 700;">
                        <span>Total</span>
                        <span id="totalAmount">RM 0</span>
                    </div>
                </div>
                <div class="checkout-button" onclick="handleCheckout()">Checkout</div>
            </aside>
        </div>
    </main>

    <jsp:include page="footer.jsp" />

    <script src="${pageContext.request.contextPath}/js/shoppingcart.js?v=2" defer></script>

</body>
</html>