<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SecondChance Tech | Home</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/variables.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/layout.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>

    <jsp:include page="header.jsp" />

    <main>
        <section class="hero">
            <div class="hero-content">
                <img src="${pageContext.request.contextPath}/assets/Homepage Iphone Image.png" alt="Featured iPhone" class="hero-img">
                <div class="hero-text-group">
                    <h1 class="hero-title">SecondChance Tech</h1>
                    <p class="hero-subtitle">Where good tech gets a second life.</p>
                </div>
            </div>
        </section>

、       <section class="category-section">
            <h2 class="section-title">Browse By Category</h2>
            <div class="category-grid">
                <a href="${pageContext.request.contextPath}/gadgets?type=phones" class="category-card">
                    <img src="${pageContext.request.contextPath}/assets/icon phone.png" alt="Phones">
                    <span>Phones</span>
                </a>

                <a href="${pageContext.request.contextPath}/gadgets?type=watches" class="category-card">
                    <img src="${pageContext.request.contextPath}/assets/icon watch.png" alt="Smart Watches">
                    <span>Smart Watches</span>
                </a>

                <a href="${pageContext.request.contextPath}/gadgets?type=cameras" class="category-card">
                    <img src="${pageContext.request.contextPath}/assets/icon camera.png" alt="Cameras">
                    <span>Cameras</span>
                </a>

                <a href="${pageContext.request.contextPath}/gadgets?type=headphones" class="category-card">
                    <img src="${pageContext.request.contextPath}/assets/icon headphone.png" alt="Headphones">
                    <span>Headphones</span>
                </a>

                <a href="${pageContext.request.contextPath}/gadgets?type=computers" class="category-card">
                    <img src="${pageContext.request.contextPath}/assets/icon computer.png" alt="Computers">
                    <span>Computers</span>
                </a>
            </div>
        </section>

        <section class="products-container">
            <div class="product-tabs">
                <button class="tab-btn active" onclick="openTab(event, 'new-arrival')">New Arrival</button>
                <button class="tab-btn" onclick="openTab(event, 'recommend')">Recommend For You</button>
            </div>

            <div id="new-arrival" class="tab-content active">
                <div class="product-grid">
                    <%-- Use 'newArrivals' to match what the HomeServlet sends --%>
                    <c:forEach var="item" items="${newArrivals}">
                        <div class="product-card reveal">
                            <div class="product-image">
                                <img src="${pageContext.request.contextPath}/assets${item.imagePath}" alt="${item.name}">
                            </div>
                            <div class="product-info">
                                <p class="product-name">${item.name}</p>
                                <p class="product-price">RM ${item.price}</p>
                                <%-- Use gadgetId to ensure the ProductServlet finds the right record --%>
                                <button class="buy-btn" onclick="location.href='${pageContext.request.contextPath}/product-details?id=${item.gadgetId}'">Buy Now</button>
                            </div>
                        </div>
                    </c:forEach>

                    <c:if test="${empty newArrivals}">
                        <p class="no-data">No new arrivals available.</p>
                    </c:if>
                </div>
            </div>

            <div id="recommend" class="tab-content">
                <div class="product-grid">
                    <c:forEach var="item" items="${recommended}">
                        <div class="product-card reveal">
                            <div class="product-image">
                                 <img src="${pageContext.request.contextPath}/assets${item.imagePath}" alt="${item.name}">
                            </div>
                            <div class="product-info">
                                <p class="product-name">Product ID: ${item.productId}</p>
                                <p class="product-price">RM ${item.price}</p>
                                <button class="buy-btn" onclick="location.href='${pageContext.request.contextPath}/ProductServlet?id=${item.productId}'">Buy Now</button>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section>

        <section class="products-container grey-bg">
            <h2 class="section-heading">Best Condition</h2>
            <div class="product-grid">
                <c:forEach var="item" items="${products}">
                    <div class="product-card reveal">
                        <div class="product-image">
                            <img src="${pageContext.request.contextPath}/assets${item.imagePath}" alt="${item.name}">
                        </div>
                        <div class="product-info">
                            <p class="product-name">Product ID: ${item.productId}</p>
                            <p class="product-price">RM ${item.price}</p>
                            <button class="buy-btn">Buy Now</button>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </section>
    </main>

    <jsp:include page="footer.jsp" />

    <script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>