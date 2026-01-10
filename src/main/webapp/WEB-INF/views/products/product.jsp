<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><c:out value="${product.name}" default="Product Details"/> - SecondChance Tech</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/variables.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/layout.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/product-details.css">
</head>
<body>

    <jsp:include page="../header.jsp" />

    <c:choose>
        <c:when test="${not empty product}">
            <!-- Breadcrumb -->
            <nav class="breadcrumb">
                <a href="${pageContext.request.contextPath}/home">Home</a>
                <span>›</span>
                <a href="${pageContext.request.contextPath}/gadgets">Catalog</a>
                <span>›</span>
                <span class="current">${product.name}</span>
            </nav>

            <!-- Product Details -->
            <main class="product-container">
                <div class="product-layout">
                    <!-- Product Image -->
                    <div class="product-image-section">
                        <img src="${pageContext.request.contextPath}/${product.imagePath}"
                             alt="${product.name}"
                             onerror="this.src='https://via.placeholder.com/400x500?text=${product.name}'">
                    </div>

                    <!-- Product Info -->
                    <div class="product-details-section">
                        <h1>${product.name}</h1>
                        <div class="product-price">RM ${product.price}</div>

                        <!-- Specifications Grid -->
                        <div class="specs-grid">
                            <c:if test="${not empty specs}">
                                <c:forEach var="entry" items="${specs}">
                                    <div class="spec-item">
                                        <div class="spec-label">${entry.key}</div>
                                        <div class="spec-value">${entry.value}</div>
                                    </div>
                                </c:forEach>
                            </c:if>
                        </div>

                        <!-- Action Button -->
                        <div class="action-buttons">
                            <button class="btn-cart" onclick="addToCart(${product.productId})">
                                Add to Cart
                            </button>
                        </div>
                    </div>
                </div>
            </main>
        </c:when>
        <c:otherwise>
            <main class="product-container">
                <div class="no-product">
                    <p>Product not found. Please return to the <a href="${pageContext.request.contextPath}/home">home page</a>.</p>
                </div>
            </main>
        </c:otherwise>
    </c:choose>

    <jsp:include page="../footer.jsp" />

    <script>
        function addToCart(productId) {
            // TODO: Implement cart functionality
            alert('Added to Cart! (Product ID: ' + productId + ')');

            // Uncomment when ready to implement backend
            /*
            fetch('${pageContext.request.contextPath}/cart/add', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    productId: productId,
                    quantity: 1
                })
            })
            .then(response => response.json())
            .then(data => {
                if(data.success) {
                    alert('Successfully added to cart!');
                    // Optionally redirect to cart page
                    // window.location.href = '${pageContext.request.contextPath}/cart';
                } else {
                    alert('Failed to add to cart: ' + data.message);
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('An error occurred while adding to cart.');
            });
            */
        }
    </script>
</body>
</html>
