<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Smart Scoring</title>
    <style>
      body {
        margin: 0;
        font-family: Arial, sans-serif;
        background: #fff;
        color: #1f2937;
      }
      .container {
        max-width: 900px;
        margin: auto;
        padding: 40px 20px;
      }
      h1 {
        font-size: 32px;
        margin-bottom: 10px;
      }
      h2 {
        font-size: 22px;
        margin: 30px 0 10px;
      }
      h3 {
        font-size: 18px;
        margin: 20px 0 10px;
      }
      p {
        font-size: 14px;
        color: #4b5563;
        margin: 6px 0;
      }
      label {
        font-size: 14px;
        font-weight: 600;
        margin-bottom: 6px;
        display: block;
      }
      input {
        width: 100%;
        padding: 10px;
        border-radius: 6px;
        border: 1px solid #d1d5db;
        background: #f9fafb;
      }
      section {
        border-bottom: 1px solid #e5e7eb;
        padding-bottom: 25px;
        margin-bottom: 25px;
      }
      .row {
        display: flex;
        justify-content: space-between;
        align-items: center;
      }
      .slider {
        height: 6px;
        background: #e5e7eb;
        border-radius: 999px;
        margin: 6px 0 14px;
      }
      .slider-fill {
        height: 100%;
        background: #8fa57b;
        border-radius: 999px;
      }
      .note {
        background: #e5e7eb;
        padding: 10px;
        border-radius: 6px;
        font-size: 13px;
        margin-top: 10px;
      }
      .score-circle {
        width: 160px;
        height: 160px;
        border-radius: 50%;
        border: 12px solid #8fa57b;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 36px;
        font-weight: bold;
        margin: 20px auto;
      }
      .center {
        text-align: center;
      }
      .price h1 {
        font-size: 36px;
      }

    .specs-container {
        display: flex;
        flex-direction: column;
        gap: 1.5rem; 
        width:100%;
    }

    .spec-item {
        display: flex;
        flex-direction: column;
    }

    .spec-item label {
        font-weight: 600;
        margin-bottom: 0.5rem;
        color: #333;
    }

    .spec-item input {
        padding: 0.5rem;
        border: 1px solid #ccc;
        border-radius: 6px;
        font-size: 0.95rem;
        background-color: #f9f9f9;
        color: #555;
    }

    .spec-item input[readonly] {
        cursor: default;
    }

    .score-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 0.4rem;
    }

    .banner-img {
        width: 100%;
        height: 100%;
        object-fit: cover; /* ensures image covers entire div */
        position: absolute;
        top: 0;
        left: 0;
        z-index: 0;
    }

    .banner-text {
        width:100%;
        max-width: 900px;
        margin:auto;
        position: absolute;
        z-index: 1; /* above image */
        bottom: 0%;
        left: 50%;
        transform: translate(-50%, -30%); /* center text */
        color: white;
        text-shadow: 0 2px 6px rgba(0,0,0,0.5); /* make text readable on image */
    }

    .banner-text h1 {
        font-size: 36px;
        margin: 0;
    }

    .banner-text p {
        font-size: 18px;
        margin: 1rem 0 0;
        color:white;
    }

    .banner {
        position: relative;
        width: 100%;
        height: 300px; /* fixed solid height */
        overflow: hidden;
    }
    </style>
  </head>
  <body>
    <div class="banner">
        <img src="${pageContext.request.contextPath}/assets/smart-scoring-banner.webp" alt="Banner" class="banner-img"/>
        <div class="banner-text">
            <h1>Smart Scoring</h1>
            <p style="width:70%;">Get a comprehensive rating for your product before you sell. 
               Our system provides a detailed breakdown and a fair market price estimate.
            </p>
        </div>
    </div>

    <div class="container">
      <!-- HEADER -->

      <!-- PRODUCT -->
      <section>
        <h2>Product Assessment</h2>
        <label>Device Name & Model</label>
        <input type="text" value="${product.name}" disabled />
      </section>

      <!-- PHYSICAL CONDITION -->
      <section>
        <h3>Physical Condition</h3>

        <div class="row">
          <p>Screen Condition</p>
          <span>${screenScore} / 10</span>
        </div>
        <div class="slider">
          <div class="slider-fill" style="width:${screenScore * 10}"></div>
        </div>

        <div class="row">
          <p>Body Condition</p>
          <span>${bodyScore} / 10</span>
        </div>
        <div class="slider">
          <div class="slider-fill" style="width:${bodyScore * 10}%"></div>
        </div>

        <div class="row">
          <p>Buttons & Ports</p>
          <span>${buttonScore} / 10</span>
        </div>
        <div class="slider">
          <div class="slider-fill" style="width:${buttonScore * 10}%"></div>
        </div>
      </section>

      <!-- BATTERY -->
      <section>
        <h3>Battery Health</h3>

        <div class="row">
          <p>Battery Capacity (${batteryPercentage}%)</p>
          <span>Excellent</span>
        </div>
        <div class="slider">
          <div class="slider-fill" style="width:${batteryPercentage}%"></div>
        </div>

        <div class="note">
          Battery has been replaced with a new one from aftermarket by the user.
        </div>
      </section>

      <!-- FUNCTIONALITY -->
      <section>
        <h3>Functionality</h3>

        <c:forEach
          var="label"
          items="${['Camera','Connectivity','Sensors','Speakers & Microphone','Display']}"
        >
          <div class="row">
            <p>${label}</p>
            <span>${functionalityScore / 5} / 5</span>
            <!-- simple division for example -->
          </div>
          <div class="slider">
            <div
              class="slider-fill"
              style="width:${functionalityScore * 4}%"
            ></div>
          </div>
        </c:forEach>
      </section>

    <!-- SPECS -->
    <section>
        <h3 style="margin-bottom:1rem">Specifications</h3>
        <div class="specs-container">
            <c:forEach var="productSpec" items="${productSpecs}">
            <div class="spec-item">
                <label>${productSpec.specKey}</label>
                <input type="text" value="${productSpec.specValue}" readonly>
            </div>
            </c:forEach>
        </div>
    </section>

      <!-- TOTAL SCORE -->
<section>
  <h2>Total Score</h2>
  <div class="score-circle">${totalScore} / 100</div>

  <!-- Physical Condition -->
  <div class="score-section">
    <div class="score-header">
      <h3>Physical Condition</h3>
      <span>${physicalScore} / 25</span>
    </div>
    <div class="slider">
      <div class="slider-fill" style="width:${physicalScore * 4}%;"></div>
    </div>
  </div>

  <!-- Battery Health -->
  <div class="score-section">
    <div class="score-header">
      <h3>Battery Health</h3>
      <span>${batteryScore} / 25</span>
    </div>
    <div class="slider">
      <div class="slider-fill" style="width:${batteryScore * 4}%;"></div>
    </div>
  </div>

  <!-- Functionality -->
  <div class="score-section">
    <div class="score-header">
      <h3>Functionality</h3>
      <span>${functionalityScore} / 25</span>
    </div>
    <div class="slider">
      <div class="slider-fill" style="width:${functionalityScore * 4}%;"></div>
    </div>
  </div>

  <!-- Age & Specification -->
  <div class="score-section">
    <div class="score-header">
      <h3>Age & Specification</h3>
      <span>${ageAndSpecScore} / 25</span>
    </div>
    <div class="slider">
      <div class="slider-fill" style="width:${ageAndSpecScore * 4}%;"></div>
    </div>
  </div>
</section>


      <!-- FAIR PRICE -->
      <section class="price center">
        <h2>Estimated Fair Price</h2>
        <h1>RM ${fairPrice}</h1>
      </section>
    </div>
  </body>
</html>
