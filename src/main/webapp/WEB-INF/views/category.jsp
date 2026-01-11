<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>

<%
    // --- FIX: PREPARE CHECKED BOXES LOGIC HERE ---
    // We create a List of the selected specs to make checking them easier in the HTML below.
    String[] specsParam = request.getParameterValues("specs");
    List<String> specsList = new ArrayList<>();
    if (specsParam != null) {
        specsList = Arrays.asList(specsParam);
    }
    request.setAttribute("specsList", specsList);
%>

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

    <nav class="breadcrumb">
        <a href="${pageContext.request.contextPath}/home">Home</a>
        <span>&gt;</span>
        <span class="current-category">${categoryTitle}</span>
    </nav>

    <div class="category-layout">

        <aside class="filter-sidebar">
            <form action="gadgets" method="get">

                <input type="hidden" name="type" value="${param.type}">
                <input type="hidden" name="search" value="${param.search}">

                <div class="filter-group">
                    <h3 class="filter-title">Price</h3>
                    <div class="filter-content price-inputs">
                        <input type="number" name="minPrice" class="form-control" placeholder="From" value="${param.minPrice}">
                        <input type="number" name="maxPrice" class="form-control" placeholder="To" value="${param.maxPrice}">
                    </div>
                </div>

                <c:if test="${categoryTitle == 'SMARTPHONE'}">
                    <div class="filter-group">
                        <h3 class="filter-title">Storage</h3>
                        <div class="checkbox-list">
                            <label class="checkbox-item"><input type="checkbox" name="specs" value="128GB" ${specsList.contains('128GB') ? 'checked' : ''}> 128GB</label>
                            <label class="checkbox-item"><input type="checkbox" name="specs" value="256GB" ${specsList.contains('256GB') ? 'checked' : ''}> 256GB</label>
                            <label class="checkbox-item"><input type="checkbox" name="specs" value="512GB" ${specsList.contains('512GB') ? 'checked' : ''}> 512GB</label>
                        </div>
                    </div>
                    <div class="filter-group">
                        <h3 class="filter-title">Operating System</h3>
                        <div class="checkbox-list">
                            <label class="checkbox-item"><input type="checkbox" name="specs" value="Android" ${specsList.contains('Android') ? 'checked' : ''}> Android</label>
                            <label class="checkbox-item"><input type="checkbox" name="specs" value="iOS" ${specsList.contains('iOS') ? 'checked' : ''}> iOS</label>
                        </div>
                    </div>
                </c:if>

                <c:if test="${categoryTitle == 'LAPTOP'}">
                    <div class="filter-group">
                        <h3 class="filter-title">RAM</h3>
                        <div class="checkbox-list">
                            <label class="checkbox-item"><input type="checkbox" name="specs" value="8GB" ${specsList.contains('8GB') ? 'checked' : ''}> 8GB</label>
                            <label class="checkbox-item"><input type="checkbox" name="specs" value="16GB" ${specsList.contains('16GB') ? 'checked' : ''}> 16GB</label>
                        </div>
                    </div>
                    <div class="filter-group">
                        <h3 class="filter-title">Storage</h3>
                        <div class="checkbox-list">
                            <label class="checkbox-item"><input type="checkbox" name="specs" value="256GB SSD" ${specsList.contains('256GB SSD') ? 'checked' : ''}> 256GB SSD</label>
                            <label class="checkbox-item"><input type="checkbox" name="specs" value="512GB SSD" ${specsList.contains('512GB SSD') ? 'checked' : ''}> 512GB SSD</label>
                            <label class="checkbox-item"><input type="checkbox" name="specs" value="1TB SSD" ${specsList.contains('1TB SSD') ? 'checked' : ''}> 1TB SSD</label>
                        </div>
                    </div>
                </c:if>

                <c:if test="${categoryTitle == 'CAMERA'}">
                    <div class="filter-group">
                        <h3 class="filter-title">Video Quality</h3>
                        <div class="checkbox-list">
                            <label class="checkbox-item"><input type="checkbox" name="specs" value="4K 60p" ${specsList.contains('4K 60p') ? 'checked' : ''}> 4K 60p</label>
                            <label class="checkbox-item"><input type="checkbox" name="specs" value="6.2K 30p" ${specsList.contains('6.2K 30p') ? 'checked' : ''}> 6.2K 30p</label>
                            <label class="checkbox-item"><input type="checkbox" name="specs" value="5.7K 60p" ${specsList.contains('5.7K 60p') ? 'checked' : ''}> 5.7K 60p</label>
                        </div>
                    </div>
                    <div class="filter-group">
                        <h3 class="filter-title">Sensor Size</h3>
                        <div class="checkbox-list">
                            <label class="checkbox-item"><input type="checkbox" name="specs" value="Full Frame" ${specsList.contains('Full Frame') ? 'checked' : ''}> Full Frame</label>
                            <label class="checkbox-item"><input type="checkbox" name="specs" value="APS-C" ${specsList.contains('APS-C') ? 'checked' : ''}> APS-C</label>
                            <label class="checkbox-item"><input type="checkbox" name="specs" value="Micro 4/3" ${specsList.contains('Micro 4/3') ? 'checked' : ''}> Micro 4/3</label>
                        </div>
                    </div>
                </c:if>

                <c:if test="${categoryTitle == 'HEADPHONE'}">
                    <div class="filter-group">
                        <h3 class="filter-title">Type</h3>
                        <div class="checkbox-list">
                            <label class="checkbox-item"><input type="checkbox" name="specs" value="Over-Ear" ${specsList.contains('Over-Ear') ? 'checked' : ''}> Over-Ear</label>
                        </div>
                    </div>
                    <div class="filter-group">
                        <h3 class="filter-title">Noise Cancellation</h3>
                        <div class="checkbox-list">
                            <label class="checkbox-item"><input type="checkbox" name="specs" value="Yes" ${specsList.contains('Yes') ? 'checked' : ''}> Yes (ANC)</label>
                        </div>
                    </div>
                </c:if>

                <c:if test="${categoryTitle == 'WATCH'}">
                    <div class="filter-group">
                        <h3 class="filter-title">Connectivity</h3>
                        <div class="checkbox-list">
                            <label class="checkbox-item"><input type="checkbox" name="specs" value="GPS + Cellular" ${specsList.contains('GPS + Cellular') ? 'checked' : ''}> GPS + Cellular</label>
                            <label class="checkbox-item"><input type="checkbox" name="specs" value="Bluetooth" ${specsList.contains('Bluetooth') ? 'checked' : ''}> Bluetooth</label>
                            <label class="checkbox-item"><input type="checkbox" name="specs" value="GPS, Bluetooth" ${specsList.contains('GPS, Bluetooth') ? 'checked' : ''}> GPS, Bluetooth</label>
                        </div>
                    </div>
                    <div class="filter-group">
                        <h3 class="filter-title">Water Resistance</h3>
                        <div class="checkbox-list">
                            <label class="checkbox-item"><input type="checkbox" name="specs" value="50m" ${specsList.contains('50m') ? 'checked' : ''}> 50m</label>
                            <label class="checkbox-item"><input type="checkbox" name="specs" value="100m" ${specsList.contains('100m') ? 'checked' : ''}> 100m</label>
                        </div>
                    </div>
                </c:if>

                <div class="filter-actions-container">
                    <button type="submit" class="btn-primary-filter" style="background:rgb(15, 63, 134);">Apply Filters</button>

                    <c:choose>
                        <c:when test="${not empty param.search}">
                            <a href="gadgets?search=${param.search}" class="btn-secondary-filter" style="background:lightgray; text-align:center; text-decoration:none; padding: 10px;">Reset All</a>
                        </c:when>
                        <c:otherwise>
                            <a href="gadgets?type=${param.type}" class="btn-secondary-filter" style="background:lightgray; text-align:center; text-decoration:none; padding: 10px;">Reset All</a>
                        </c:otherwise>
                    </c:choose>
                </div>

            </form>
        </aside>

         <section class="product-listing">

             <div class="listing-toolbar">
                 <p>
                     Products Found:
                     <strong>${products.size()}</strong>
                 </p>
             </div>

             <div class="category-grid">

                <c:forEach var="item" items="${products}">
                    <a href="${pageContext.request.contextPath}/products/${item.productId}"
                    style="text-decoration: none; color: inherit;">
                        <div class="product-card reveal">

                            <div class="product-image">
                                <img src="${pageContext.request.contextPath}/assets${item.imagePath.startsWith('/') ? '' : '/'}${item.imagePath}" alt="${item.name}">
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