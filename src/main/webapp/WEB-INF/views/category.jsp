<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
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

    <!-- Breadcrumb -->
    <nav class="breadcrumb">
        <a href="${pageContext.request.contextPath}/home">Home</a>
        <span>&gt;</span>
        <span class="current-category">${categoryTitle}</span>
    </nav>

    <!-- Listing -->
     <div class="category-layout">
        <aside class="filter-sidebar">
            <div class="filter-group">
                <h3 class="filter-title">Price</h3>
                <div class="filter-content price-inputs">
                    <input type="number" id="min-price" name="minPrice" placeholder="From" value="0">
                    <input type="number" id="max-price" name="maxPrice" placeholder="To" value="0">
                </div>
            </div>

            <div>
                <%-- Filters for PHONES --%>
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

                <%-- Filters for CAMERAS --%>
                    <div class="filter-group">
                        <h3 class="filter-title">Resolution</h3>
                        <div class="checkbox-list">
                            <label class="checkbox-item"><input type="checkbox" name="spec" value="20MP"> 20MP</label>
                            <label class="checkbox-item"><input type="checkbox" name="spec" value="24MP"> 24MP</label>
                            <label class="checkbox-item"><input type="checkbox" name="spec" value="45MP"> 45MP</label>
                        </div>
                    </div>

                <%-- Default: Show Brand for other categories --%>
                    <div class="filter-group">
                        <h3 class="filter-title">Brand</h3>
                        <div class="checkbox-list">
                            <label class="checkbox-item"><input type="checkbox" name="spec" value="Apple"> Apple</label>
                            <label class="checkbox-item"><input type="checkbox" name="spec" value="Samsung"> Samsung</label>
                            <label class="checkbox-item"><input type="checkbox" name="spec" value="Sony"> Sony</label>
                        </div>
                    </div>
                </div>

                <div class="filter-actions-container">
                    <button type="button" class="btn-primary-filter" style="background:rgb(15, 63, 134);" onclick="applyFilters()">Apply Filters</button>
                    <button type="button" class="btn-secondary-filter" style="background:lightgray;" onclick="resetFilters()">Reset All</button>
                </div>

                <script>
                function applyFilters() {
                    const urlParams = new URLSearchParams(window.location.search);
                    const type = urlParams.get('type') || '';
                    const search = urlParams.get('search') || '';
                    const minPrice = document.getElementById('min-price').value;
                    const maxPrice = document.getElementById('max-price').value;

                    const checkedSpecs = Array.from(document.querySelectorAll('input[name="spec"]:checked'))
                                            .map(cb => cb.value)
                                            .join(',');

                    // Build query params dynamically, only include non-empty
                    let query = [];
                    if (search) query.push("search=" + encodeURIComponent(search));
                    if (type) query.push("type=" + encodeURIComponent(type));
                    if (minPrice) query.push("minPrice=" + encodeURIComponent(minPrice));
                    if (maxPrice) query.push("maxPrice=" + encodeURIComponent(maxPrice));
                    if (checkedSpecs) query.push("specs=" + encodeURIComponent(checkedSpecs));

                    // Redirect
                    window.location.href = "gadgets" + (query.length > 0 ? "?" + query.join("&") : "");
                }
                function resetFilters() {
                    const urlParams = new URLSearchParams(window.location.search);
                    window.location.href = `gadgets?search=${urlParams.get('search') || ''}`;
                }
                </script>
        </aside>

         <section class="product-listing">
     
             <div class="listing-toolbar">
                 <p>
                     Products Found:
                     <strong>${products.size()}</strong>
                 </p>

                <a href ="products/add-product"  type="button" class="btn-primary-filter"  style="margin-left:20px; background:green; color:white;"  >
                    Add New Product
                </a>
             </div>
     
             <div class="category-grid">
     
                <c:forEach var="item" items="${products}">
                    <a href="${pageContext.request.contextPath}/products/${item.productId}"
                    style="text-decoration: none; color: inherit;">
                        <div class="product-card reveal">

                            <div class="product-image">
                                <img src="${pageContext.request.contextPath}/${item.imagePath}"
                                    alt="${item.name}">
                            </div>

                            <div class="product-info">
                                <h3 class="product-name">${item.name}</h3>
                                <p class="product-price">RM ${item.price}</p>
                            </div>

                        </div>
                    </a>
                </c:forEach>

     
                 <c:if test="${empty products}">
                     <p class="no-data">No products found.</p>
                 </c:if>
     
             </div>
         </section>
     </div>

</main>

<jsp:include page="footer.jsp" />

</body>
</html>
