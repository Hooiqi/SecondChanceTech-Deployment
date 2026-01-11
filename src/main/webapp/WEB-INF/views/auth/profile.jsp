<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Register</title>

  <link
    rel="stylesheet"
    type="text/css"
    href="${pageContext.request.contextPath}/css/global.css"
  />

  <style>
    body {
      margin: 0;
      height: 100vh;
      background: #2f3447;
      font-family: inherit;
    }

    .container {
      display: flex;
      height: 100vh;
    }

    /* LEFT SIDE */
    .left {
      width: 50%;
      background: linear-gradient(
        135deg,
        rgba(120, 130, 90, 0.8),
        rgba(40, 45, 65, 0.95)
      );
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      color: #f5f5f5;
    }

    .left img {
      width: 120px;
      margin-bottom: 1rem;
    }

    .brand-title {
      font-size: 2rem;
      font-weight: bold;
      letter-spacing: 2px;
      text-align: center;
    }

    .brand-title span {
      display: block;
      color: #b7c58a;
    }

    /* RIGHT SIDE */
    .right {
      width: 50%;
      background: #333848;
      display: flex;
      justify-content: center;
      align-items: center;
    }

    .form-box {
      width: 100%;
      max-width: 480px;
      color: #fff;
    }

    .form-box h1 {
      margin-bottom: 0.25rem;
    }

    .form-box p {
      margin-top: 0;
      margin-bottom: 1.5rem;
      color: #c7c7c7;
    }

    .grid-2 {
      display: grid;
      grid-template-columns: 1fr 1fr;
      gap: 0.75rem;
    }

    .form-group {
      display: flex;
      flex-direction: column;
    }

    .full {
      grid-column: span 2;
    }
  </style>
</head>

<body>
<div class="container">

  <!-- LEFT BRANDING -->
  <div class="left">
    <img
      src="${pageContext.request.contextPath}/assets/logo about.png"
      alt="Logo"
    />
    <div class="brand-title">
      SECOND CHANCE
      <span>TECH</span>
    </div>
  </div>

  <!-- RIGHT FORM -->
  <div class="right">
    <div class="form-box">
      
    <div style="display: flex; align-items: center; gap: 1rem; padding: 8px 0;">
        <button onclick="window.history.back()" 
                style="
                    display: flex;
                    align-items: center;
                    justify-content: center;
                    width: 36px;
                    height: 36px;
                    font-size: 18px;
                    cursor: pointer;
                    border:1px solid white;
                    border-radius: 50%;
                    background:transparent;
                    color:white;
                    transition: background 0.15s, color 0.3s;
                "
                onMouseOver="this.style.background='white'; this.style.color='#2f3447';"
                >
            &#8592;
        </button>
        <h1 style="margin: 0; font-size: 1.5rem;">User Profile</h1>
    </div>


      <p>Set Up Your Profile Details</p>

      <form action="profile" method="post" class="grid-2">

        <div class="form-group">
          <label>First Name</label>
          <input class="j-input" name="first_name"
                 value="${user.firstName}">
        </div>

        <div class="form-group">
          <label>Last Name</label>
          <input class="j-input" name="last_name"
                 value="${user.lastName}">
        </div>

        <div class="form-group">
          <label>Phone Number</label>
          <input class="j-input" name="phone" 
                 value="${user.phone}">
        </div>

        <div class="form-group">
          <label>Gender</label>
          <select class="j-input" name="gender">
            <option value="">Select Gender</option>
            <option value="Male" ${user.gender == 'Male' ? 'selected' : ''}>Male</option>
            <option value="Female" ${user.gender == 'Female' ? 'selected' : ''}>Female</option>
            <option value="Other" ${user.gender == 'Other' ? 'selected' : ''}>Other</option>
          </select>
        </div>

        <div class="form-group full">
          <label>Address</label>
          <input class="j-input" name="address"
                 value="${user.address}">
        </div>

        <div class="form-group">
          <label>City</label>
          <input class="j-input" name="city"
                 value="${user.city}">
        </div>

        <div class="form-group">
          <label>State</label>
          <input class="j-input" name="state"
                 value="${user.state}">
        </div>

        <div class="form-group full">
          <label>Zipcode</label>
          <input class="j-input" name="zipcode"
                 value="${user.zipcode}">
        </div>

        <div class="form-group full">
          <button class="submit-btn" type="submit" style="color: var(--text-secondary); font-weight: bold">
            Update
          </button>
        </div>

        <% if (request.getAttribute("error") != null) { %>
          <div class="error-banner full">
            <%= request.getAttribute("error") %>
          </div>
        <% } %>

        <% if (request.getAttribute("success") != null) { %>
          <div class="success-banner full">
            <%= request.getAttribute("success") %>
          </div>
        <% } %>

      </form>
    </div>
  </div>

</div>
</body>
</html>
