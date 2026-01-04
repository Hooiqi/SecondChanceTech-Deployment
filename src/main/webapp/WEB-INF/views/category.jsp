<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SecondChance Tech | ${categoryTitle}</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/variables.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/layout.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/category.css">

    <script src="${pageContext.request.contextPath}/js/main.js" defer></script>
</head>
<body>

    <jsp:include page="header.jsp" />

    <main class="category-page">
        <nav class="breadcrumb">
            <a href="${pageContext.request.contextPath}/home">Home</a>
            <span>&gt;</span>
            <a href="${pageContext.request.contextPath}/gadgets?type=${param.type}" class="current-category">${categoryTitle}</a>
        </nav>

        <div class="category-layout">
            <aside class="filter-sidebar">
                <div class="filter-group">
                    <h3 class="filter-title" onclick="toggleFilter(this)">Price</h3>
                    <div class="filter-content price-inputs">
                        <input type="number" id="min-price" placeholder="From">
                        <input type="number" id="max-price" placeholder="To">
                    </div>
                </div>

                <div class="filter-group">
                    <h3 class="filter-title" onclick="toggleFilter(this)">Brand</h3>
                    <div class="filter-content">
                        <div class="sidebar-search">
                            <img src="${pageContext.request.contextPath}/assets/icon search.png" alt="Search">
                            <input type="text" id="brand-search" placeholder="Search Brand">
                        </div>
                        <div class="checkbox-list" id="brand-filter-list">
                            <label class="checkbox-item">
                                <div><input type="checkbox" name="brand" value="Apple"> Apple</div>
                                <span class="brand-count">0</span>
                            </label>
                            <label class="checkbox-item">
                                <div><input type="checkbox" name="brand" value="Samsung"> Samsung</div>
                                <span class="brand-count">0</span>
                            </label>
                            </div>
                    </div>
                </div>
                <div class="filter-actions">
                    <button type="button" class="apply-btn" onclick="applyFilters()">Apply Filters</button>
                    <button type="button" class="reset-btn" onclick="resetFilters()">Reset</button>
                </div>

                <script>
                function applyFilters() {
                    const minPrice = document.getElementById('min-price').value;
                    const maxPrice = document.getElementById('max-price').value;
                    const brands = Array.from(document.querySelectorAll('input[name="brand"]:checked'))
                                        .map(cb => cb.value).join(',');

                    // Get the current category from the URL parameter
                    const urlParams = new URLSearchParams(window.location.search);
                    const type = urlParams.get('type') || '';

                    // Redirect with filter parameters
                    window.location.href = `gadgets?type=${type}&minPrice=${minPrice}&maxPrice=${maxPrice}&brands=${brands}`;
                }

                function resetFilters() {
                    const urlParams = new URLSearchParams(window.location.search);
                    window.location.href = `gadgets?type=${urlParams.get('type') || ''}`;
                }
                </script>

                </aside>

            <section class="product-listing">
                <div class="listing-toolbar">
                    <p>Selected Products: <strong id="product-count">${gadgets.size()}</strong></p>
                    <select class="sort-dropdown">
                        <option value="price-low">Price: Low to High</option>
                        <option value="price-high">Price: High to Low</option>
                        <option value="az">A-Z</option>
                        <option value="za">Z-A</option>
                    </select>
                </div>

                <div class="category-grid" id="main-product-grid">
                    <c:forEach var="item" items="${gadgets}">
                        <div class="product-card reveal">
                            <div class="product-image">
                                <img src="${pageContext.request.contextPath}/assets/${item.name}.png" alt="${item.name}">
                            </div>
                            <div class="product-info">
                                <h3 class="product-name">${item.name}</h3>
                                <p class="product-price">RM ${item.price}</p>
                                <button class="buy-btn" onclick="location.href='${pageContext.request.contextPath}/ProductServlet?id=${item.gadgetId}'">
                                    Buy Now
                                </button>
                            </div>
                        </div>
                    </c:forEach>

                    <c:if test="${empty gadgets}">
                        <p class="no-data">No products found in this category.</p>
                    </c:if>
                </div>
            </section>
        </div>
    </main>

    <jsp:include page="footer.jsp" />

</body>
</html>