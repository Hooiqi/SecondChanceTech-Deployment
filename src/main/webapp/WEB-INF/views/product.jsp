<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${product.name} | SecondChance Tech</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/layout.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/variables.css">
    <style>
    body {
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif;
            color: #1d1d1f;
        }
        .product-details-container { max-width: 1200px; margin: 80px auto; display: grid; grid-template-columns: 1fr 1fr; gap: 60px; padding: 0 20px; }
        .main-image img { width: 100%; height: 500px; object-fit: contain; }
        .product-info { display: flex; flex-direction: column; gap: 15px; }
        .product-title { font-size: 2.5rem; font-weight: 700; color: #111; }
        .product-price { font-size: 2rem; font-weight: 500; color: #333; margin-bottom: 20px; }

        .specs-grid { display: grid; grid-template-columns: 1fr 1fr; gap: 15px; }
        .spec-box { background: #f5f5f7; padding: 15px; border-radius: 12px; }
        .spec-label { font-size: 0.8rem; color: #86868b; margin-bottom: 4px; text-transform: capitalize; }
        .spec-value { font-size: 1rem; font-weight: 600; color: #1d1d1f; }

        .btn-cart { background: #000; color: #fff; padding: 18px; border: none; border-radius: 12px; font-weight: 600; cursor: pointer; margin-top: 20px; }
        /* --- SMART SCORING STYLES --- */
            .smart-scoring-container {
                max-width: 900px;
                margin: 60px auto;
                padding: 40px;
                background: #fff;
                border-top: 1px solid #e5e7eb;
            }

            .scoring-section-title {
                font-size: 28px;
                margin-bottom: 40px;
                text-align: center;
                font-weight: 700;
                color: #111;
            }

            .score-row {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 5px;
            }

            .score-label { font-size: 14px; font-weight: 600; color: #4b5563; }
            .score-number { font-size: 14px; font-weight: 600; color: #1f2937; }

            .slider {
                height: 6px;
                background: #e5e7eb;
                border-radius: 999px;
                margin: 6px 0 25px;
                width: 100%;
            }
            .slider-fill {
                height: 100%;
                background: #8fa57b; /* Green color from your screenshot */
                border-radius: 999px;
            }

            .score-circle {
                width: 160px;
                height: 160px;
                border-radius: 50%;
                border: 12px solid #8fa57b;
                display: flex;
                align-items: center;
                justify-content: center;
                font-size: 48px;
                font-weight: bold;
                color: #1f2937;
                margin: 30px auto;
            }

            .subsection-title {
                font-size: 18px;
                font-weight: 700;
                margin-bottom: 15px;
                color: #111;
                border-bottom: 2px solid #f5f5f7;
                padding-bottom: 10px;
            }

            .fair-price-box {
                text-align: center;
                margin-top: 50px;
                padding: 30px;
                background: #f9fafb;
                border-radius: 12px;
            }
            .fair-price-value {
                font-size: 42px;
                font-weight: 800;
                color: #111;
                margin: 10px 0;
            }
    </style>
</head>
<body>
    <jsp:include page="/WEB-INF/views/header.jsp" />
     <main class="product-details-container">
        <div class="main-image">
            <img src="${pageContext.request.contextPath}/assets${product.imagePath}" alt="${product.name}">
        </div>

        <div class="product-info">
            <h1 class="product-title">${product.name}</h1>
            <div class="product-price">RM ${product.price}</div>

            <div class="specs-grid">
                <c:forEach var="s" items="${specs}">
                    <div class="spec-box">
                        <div class="spec-label">${s.specKey}</div>
                        <div class="spec-value">${s.specValue}</div>
                    </div>
                </c:forEach>
            </div>

            <button class="btn-cart" onclick="addToCart(${product.productId})">Add to Cart</button>
        </div>
    </main>

    <div class="smart-scoring-container">
            <h2 class="scoring-section-title">Smart Scoring Assessment</h2>

            <div class="score-circle">
                ${totalScore}
            </div>
            <p style="text-align:center; color:#666; margin-bottom:50px;">Overall Quality Score</p>

            <h3 class="subsection-title">Physical Condition (${physicalScore}/25)</h3>

            <div class="score-row">
                <span class="score-label">Screen Condition</span>
                <span class="score-number">${screenScore} / 10</span>
            </div>
            <div class="slider"><div class="slider-fill" style="width:${screenScore * 10}%"></div></div>

            <div class="score-row">
                <span class="score-label">Body Condition</span>
                <span class="score-number">${bodyScore} / 10</span>
            </div>
            <div class="slider"><div class="slider-fill" style="width:${bodyScore * 10}%"></div></div>

            <div class="score-row">
                <span class="score-label">Buttons & Ports</span>
                <span class="score-number">${buttonScore} / 10</span>
            </div>
            <div class="slider"><div class="slider-fill" style="width:${buttonScore * 10}%"></div></div>


            <h3 class="subsection-title" style="margin-top:40px;">Battery Health (${batteryScore}/25)</h3>

            <div class="score-row">
                <span class="score-label">Battery Capacity (${batteryPercentage}%)</span>
                <span class="score-number">Excellent</span>
            </div>
            <div class="slider"><div class="slider-fill" style="width:${batteryPercentage}%"></div></div>


            <h3 class="subsection-title" style="margin-top:40px;">Functionality (${functionalityScore}/25)</h3>

            <div class="score-row">
                <span class="score-label">Sensors, Camera, Connectivity, Audio, Display</span>
                <span class="score-number">Pass</span>
            </div>
            <div class="slider"><div class="slider-fill" style="width:100%"></div></div>


            <div class="fair-price-box">
                <h3>Estimated Fair Market Price</h3>
                <div class="fair-price-value">RM ${fairPrice}</div>
                <p style="color:#666; font-size:0.9rem;">Based on condition, age, and current market trends.</p>
            </div>
        </div>

    <jsp:include page="/WEB-INF/views/footer.jsp" />

    <script>
        function addToCart(productId) {
            // 1. Prepare the data to send
            const params = new URLSearchParams();
            params.append('action', 'add');       // Matches "action" in CartAPIServlet
            params.append('productId', productId);
            params.append('quantity', 1);         // Default to 1 since there is no quantity input

            // 2. Send request to the backend
            fetch('${pageContext.request.contextPath}/api/cart', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: params
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                // 3. Handle success or error
                if (data.success) {
                    alert("Success! Item added to cart.");
                    // Optional: Redirect to cart page
                    // window.location.href = '${pageContext.request.contextPath}/shoppingcart';
                } else {
                    alert("Failed to add to cart: " + (data.error || "Unknown error"));
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert("Error connecting to server.");
            });
        }
    </script>
</body>
</html>