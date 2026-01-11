<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Checkout - Step 2</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/variables.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/layout.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/checkout.css">
</head>

<body>

    <jsp:include page="header.jsp" />

    <!-- Progress Steps -->
    <div class="steps-wrapper">
        <div class="step-item inactive">
            <div class="step-icon">
                <img src="${pageContext.request.contextPath}/assets/Location.png" class="step-icon-img" alt="Step 1">
            </div>
            <div class="step-info">
                <span class="step-label">Step 1</span>
                <span class="step-title">Address</span>
            </div>
        </div>

        <div class="step-divider"></div>

        <div class="step-item">
            <div class="step-icon">
                <img src="${pageContext.request.contextPath}/assets/Shipping.png" class="step-icon-img" alt="Step 2">
            </div>
            <div class="step-info">
                <span class="step-label">Step 2</span>
                <span class="step-title">Shipping</span>
            </div>
        </div>

        <div class="step-divider"></div>

        <div class="step-item inactive">
            <div class="step-icon">
                <img src="${pageContext.request.contextPath}/assets/payment.png" class="step-icon-img" alt="Step 3">
            </div>
            <div class="step-info">
                <span class="step-label">Step 3</span>
                <span class="step-title">Payment</span>
            </div>
        </div>
    </div>

    <!-- Main Shipping Content -->
    <main class="main-content">
        <section class="shipment-section">
            <h2 class="section-title">Shipment Method</h2>

            <div class="shipment-list" id="shippingList">
                <!-- Shipping options will be rendered here -->
            </div>
        </section>

        <!-- Action Buttons -->
        <footer class="button-group">
            <button class="btn btn-back" onclick="window.location.href='${pageContext.request.contextPath}/step1'">Back</button>
            <button class="btn btn-next" onclick="window.location.href='${pageContext.request.contextPath}/step3'">Next</button>
        </footer>
    </main>

    <jsp:include page="footer.jsp" />

    <script src="${pageContext.request.contextPath}/js/step2.js?v=2" defer></script>

</body>
</html>