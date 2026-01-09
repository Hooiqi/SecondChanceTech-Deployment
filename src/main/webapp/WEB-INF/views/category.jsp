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
                    <h3 class="filter-title">Price</h3>
                    <div class="filter-content price-inputs">
                        <input type="number" id="min-price" name="minPrice" placeholder="From" value="${param.minPrice}">
                        <input type="number" id="max-price" name="maxPrice" placeholder="To" value="${param.maxPrice}">
                    </div>
                </div>

                <c:choose>
                    <%-- Filters for PHONES --%>
                    <c:when test="${param.type == 'phones'}">
                        <div class="filter-group">
                            <h3 class="filter-title">RAM</h3>
                            <div class="checkbox-list">
                                <label class="checkbox-item"><input type="checkbox" name="spec" value="8GB"> 8GB</label>
                                <label class="checkbox-item"><input type="checkbox" name="spec" value="12GB"> 12GB</label>
                                <label class="checkbox-item"><input type="checkbox" name="spec" value="16GB"> 16GB</label>
                            </div>
                        </div>
                        <div class="filter-group">
                            <h3 class="filter-title">Storage</h3>
                            <div class="checkbox-list">
                                <label class="checkbox-item"><input type="checkbox" name="spec" value="128GB"> 128GB</label>
                                <label class="checkbox-item"><input type="checkbox" name="spec" value="256GB"> 256GB</label>
                                <label class="checkbox-item"><input type="checkbox" name="spec" value="512GB"> 512GB</label>
                            </div>
                        </div>
                    </c:when>

                    <%-- Filters for CAMERAS --%>
                    <c:when test="${param.type == 'cameras'}">
                        <div class="filter-group">
                            <h3 class="filter-title">Resolution</h3>
                            <div class="checkbox-list">
                                <label class="checkbox-item"><input type="checkbox" name="spec" value="20MP"> 20MP</label>
                                <label class="checkbox-item"><input type="checkbox" name="spec" value="24MP"> 24MP</label>
                                <label class="checkbox-item"><input type="checkbox" name="spec" value="45MP"> 45MP</label>
                            </div>
                        </div>
                    </c:when>

                    <%-- Default: Show Brand for other categories --%>
                    <c:otherwise>
                        <div class="filter-group">
                            <h3 class="filter-title">Brand</h3>
                            <div class="checkbox-list">
                                <label class="checkbox-item"><input type="checkbox" name="spec" value="Apple"> Apple</label>
                                <label class="checkbox-item"><input type="checkbox" name="spec" value="Samsung"> Samsung</label>
                                <label class="checkbox-item"><input type="checkbox" name="spec" value="Sony"> Sony</label>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>

                <div class="filter-actions-container">
                    <button type="button" class="btn-primary-filter" onclick="applyFilters()">Apply Filters</button>
                    <button type="button" class="btn-secondary-filter" onclick="resetFilters()">Reset All</button>
                </div>

                <script>
                function applyFilters() {
                    const urlParams = new URLSearchParams(window.location.search);
                    const type = urlParams.get('type') || '';
                    const minPrice = document.getElementById('min-price').value;
                    const maxPrice = document.getElementById('max-price').value;

                    // Collect all checked specification values
                    const checkedSpecs = Array.from(document.querySelectorAll('input[name="spec"]:checked'))
                                              .map(cb => cb.value)
                                              .join(',');

                    // Redirect using standard JS (No EL encodeURIComponent needed)
                    window.location.href = `gadgets?type=${type}&minPrice=${minPrice}&maxPrice=${maxPrice}&specs=` + encodeURIComponent(checkedSpecs);
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
                                <img src="${pageContext.request.contextPath}/assets${item.imagePath}" alt="${item.name}">
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