<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %> <%@
taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <title>SecondChance Tech | Add Product</title>

    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/css/variables.css"
    />
    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/css/layout.css"
    />
    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/css/main.css"
    />

    <style>
      /* Container for the form */
      .add-product-container {
        max-width: 700px;
        margin: 50px auto;
        padding: 30px 40px;
        background: #fff;
        border-radius: 12px;
        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
      }

      .add-product-container h2 {
        color: #0f3f86;
        margin-bottom: 20px;
        font-size: 28px;
      }

      .add-product-container p {
        color: #555;
        margin-bottom: 30px;
        font-size: 15px;
      }

      /* Form styling */
      .add-product-form {
        display: flex;
        flex-direction: column;
        gap: 20px;
      }

      .add-product-form label {
        font-weight: 600;
        color: #0f3f86;
        margin-bottom: 6px;
        font-size: 14px;
      }

      .add-product-form input,
      .add-product-form select,
      .add-product-form textarea {
        padding: 12px 15px;
        border-radius: 8px;
        border: 1px solid #ccc;
        font-size: 14px;
        transition: all 0.2s ease-in-out;
        width: 100%;
      }

      .add-product-form input:focus,
      .add-product-form select:focus,
      .add-product-form textarea:focus {
        border-color: #0f3f86;
        box-shadow: 0 0 8px rgba(15, 63, 134, 0.2);
        outline: none;
      }

      .add-product-form textarea {
        resize: vertical;
        min-height: 100px;
      }

      /* Buttons */
      .form-actions {
        display: flex;
        gap: 15px;
        margin-top: 10px;
      }

      .form-actions button {
        cursor: pointer;
        border: none;
        border-radius: 8px;
        font-size: 16px;
        font-weight: 600;
        padding: 12px 25px;
        transition: all 0.2s;
      }

      .btn-primary {
        background-color: #0f3f86;
        color: #fff;
      }

      .btn-primary:hover {
        background-color: #0b2d63;
      }

      .btn-secondary {
        background-color: #eee;
        color: #333;
      }

      .btn-secondary:hover {
        background-color: #ccc;
      }

      /* Error message */
      .error-message {
        color: #fff;
        background-color: #e74c3c;
        padding: 12px 15px;
        border-radius: 8px;
        font-size: 14px;
        margin-bottom: 20px;
      }

      /* Breadcrumb */
      .breadcrumb {
        font-size: 14px;
        margin-bottom: 30px;
      }

      .breadcrumb a {
        color: #0f3f86;
        text-decoration: none;
        margin-right: 5px;
      }

      .breadcrumb span {
        margin-right: 5px;
      }
    </style>
  </head>

  <script>
    document.addEventListener("DOMContentLoaded", function () {
      const gadgetSelect = document.getElementById("gadgetId");

      if (!gadgetSelect) return;

      gadgetSelect.addEventListener("change", function () {
        const selectedId = this.value;

        document.querySelectorAll(".spec-group").forEach((group) => {
          group.style.display = "none";
          group.querySelectorAll("input").forEach((i) => (i.disabled = true));
        });

        const activeGroup = document.querySelector(
          '.spec-group[data-gadget-id="' + selectedId + '"]'
        );

        if (activeGroup) {
          activeGroup.style.display = "block";
          activeGroup
            .querySelectorAll("input")
            .forEach((i) => (i.disabled = false));
        }
      });
    });
  </script>

  <body>
    <jsp:include page="../header.jsp" />

    <main class="add-product-container">
      <nav class="breadcrumb">
        <a href="${pageContext.request.contextPath}/home">Home</a>
        <span>&gt;</span>
        <span>Add Product</span>
      </nav>

      <h2>Add New Product</h2>
      <p>Fill in the details below to add a new product to your catalog.</p>

      <c:if test="${not empty error}">
        <div class="error-message">${error}</div>
      </c:if>

      <form
        action="${pageContext.request.contextPath}/products/add-product"
        method="post"
        class="add-product-form"
        enctype="multipart/form-data"
      >
        <div class="form-group">
          <label for="gadgetId">Gadget</label>
          <select id="gadgetId" name="gadgetId" required>
            <option value="" disabled selected>Select a Gadget</option>
            <c:forEach var="gadget" items="${gadgets}">
              <option value="${gadget.gadgetId}">
                ${fn:toUpperCase(gadget.name)}
              </option>
            </c:forEach>
          </select>
        </div>

        <div class="form-group">
          <label for="name">Product Name</label>
          <input
            type="text"
            id="name"
            name="name"
            required
            placeholder="e.g., Samsung Galaxy S24"
          />
        </div>

        <div class="form-group">
          <label for="imageFile">Product Image</label>
          <input
            type="file"
            id="imageFile"
            name="imageFile"
            accept="image/*"
            required
          />
        </div>

        <div class="form-group">
          <label for="price">Price (RM)</label>
          <input
            type="number"
            id="price"
            name="price"
            required
            min="0"
            step="0.01"
            placeholder="e.g., 1999.99"
          />
        </div>

        <div class="form-group">
          <label for="deliveryDay">Delivery Time (Days)</label>
          <input
            type="number"
            id="deliveryDay"
            name="deliveryDay"
            required
            min="1"
            max="30"
            placeholder="e.g., 3"
          />
        </div>

        <div class="form-group">
          <label for="guaranteedPeriod">Guaranteed Period (Months)</label>
          <input
            type="number"
            id="guaranteedPeriod"
            name="guaranteedPeriod"
            required
            min="1"
            max="60"
            placeholder="e.g., 12"
          />
        </div>

        <div
          style="
            padding-top: 1rem;
            border-top: 1px solid gray;
            font-weight: bold;
            margin-top: 1rem;
          "
        >
          Product Specifications
        </div>

        <c:forEach var="entry" items="${gadgetSpecsMap}">
          <div
            class="spec-group"
            data-gadget-id="${entry.key}"
            style="display: none"
          >
            <c:forEach var="spec" items="${entry.value}">
              <div class="form-group">
                <label>${spec.specKey}</label>
                <input
                  type="text"
                  name="spec_${spec.specId}"
                  placeholder="Enter ${spec.specKey}"
                />
              </div>
            </c:forEach>
          </div>
        </c:forEach>

        <div class="form-actions">
          <button type="submit" class="btn-primary">Add Product</button>
          <button type="reset" class="btn-secondary">Reset</button>
        </div>
      </form>
    </main>

    <jsp:include page="../footer.jsp" />
  </body>
</html>
