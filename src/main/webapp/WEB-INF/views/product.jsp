
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Details | SecondChance Tech</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/variables.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/layout.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/product-details.css">
</head>
<body>

    <jsp:include page="header.jsp" />

    <main>
        <!-- Breadcrumb -->
        <nav class="breadcrumb">
            <a href="${pageContext.request.contextPath}/">Home</a>
            <span>›</span>
            <a href="${pageContext.request.contextPath}/gadgets">Catalog</a>
            <span>›</span>
            <a href="${pageContext.request.contextPath}/gadgets?type=phones">Smartphones</a>
            <span>›</span>
            <a href="#">Apple</a>
            <span>›</span>
            <span class="current">iPhone 14 Pro Max</span>
        </nav>

        <!-- Product Details -->
        <section class="product-details-container">
            <div class="product-content">
                <!-- Image Gallery -->
                <div class="image-gallery">
                    <div class="thumbnail-list">
                        <div class="thumbnail active" data-image="1">
                            <img src="${pageContext.request.contextPath}/assets/product_${product.gadgetId}_1.png" alt="View 1">
                        </div>
                        <div class="thumbnail" data-image="2">
                            <img src="${pageContext.request.contextPath}/assets/product_${product.gadgetId}_2.png" alt="View 2">
                        </div>
                        <div class="thumbnail" data-image="3">
                            <img src="${pageContext.request.contextPath}/assets/product_${product.gadgetId}_3.png" alt="View 3">
                        </div>
                        <div class="thumbnail" data-image="4">
                            <img src="${pageContext.request.contextPath}/assets/product_${product.gadgetId}_4.png" alt="View 4">
                        </div>
                    </div>
                    <div class="main-image">
                        <img id="mainProductImage" src="${pageContext.request.contextPath}/assets/product_${product.gadgetId}.png" alt="${product.name}">
                    </div>
                </div>

                <!-- Product Info -->
                <div class="product-info">
                    <h1 class="product-title">${product.name}</h1>
                    <div class="product-price">RM ${product.price}</div>

                    <!-- Specifications Grid -->
                    <div class="specs-grid">
                        <div class="spec-item">
                            <span class="spec-icon">📏</span>
                            <div class="spec-details">
                                <div class="spec-label">Screen size</div>
                                <div class="spec-value">${product.screenSize}</div>
                            </div>
                        </div>

                        <div class="spec-item">
                            <span class="spec-icon">🖥️</span>
                            <div class="spec-details">
                                <div class="spec-label">CPU</div>
                                <div class="spec-value">${product.cpu}</div>
                            </div>
                        </div>

                        <div class="spec-item">
                            <span class="spec-icon">💾</span>
                            <div class="spec-details">
                                <div class="spec-label">Number of Cores</div>
                                <div class="spec-value">${product.cores}</div>
                            </div>
                        </div>

                        <div class="spec-item">
                            <span class="spec-icon">📸</span>
                            <div class="spec-details">
                                <div class="spec-label">Main camera</div>
                                <div class="spec-value">${product.mainCamera}</div>
                            </div>
                        </div>

                        <div class="spec-item">
                            <span class="spec-icon">🤳</span>
                            <div class="spec-details">
                                <div class="spec-label">Front-camera</div>
                                <div class="spec-value">${product.frontCamera}</div>
                            </div>
                        </div>

                        <div class="spec-item">
                            <span class="spec-icon">🔋</span>
                            <div class="spec-details">
                                <div class="spec-label">Battery capacity</div>
                                <div class="spec-value">${product.battery}</div>
                            </div>
                        </div>
                    </div>

                    <!-- Condition Badge -->
                    <div class="condition-badge">
                        <span class="condition-label">Condition:</span>
                        <span class="condition-value">${product.condition}</span>
                    </div>

                    <!-- Action Buttons -->
                    <div class="action-buttons">
                        <button class="btn btn-wishlist" onclick="addToWishlist(${product.productId})">
                            Add to Wishlist
                        </button>
                        <button class="btn btn-cart" onclick="addToCart(${product.productId})">
                            Add to Cart
                        </button>
                    </div>
                </div>
            </div>
        </section>

        <!-- Product Description -->
        <section class="product-description-container">
            <h2 class="section-title">Product Description</h2>
            <div class="description-content">
                <p>${product.description}</p>
            </div>
        </section>
    </main>

    <jsp:include page="footer.jsp" />

    <script src="${pageContext.request.contextPath}/js/product-details.js"></script>
</body>
</html>