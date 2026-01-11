<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<header class="main-header">
  <nav class="top-nav">
    <img
      src="${pageContext.request.contextPath}/assets/logo round.png"
      alt="SecondChance Logo"
      class="logo"
    />

    <form
      action="${pageContext.request.contextPath}/gadgets"
      method="GET"
      class="search-form"
    >
      <div class="search-bar">
        <button
          type="submit"
          style="background: none; border: none; padding: 0; cursor: pointer"
        >
          <img
            src="${pageContext.request.contextPath}/assets/icon search.png"
            alt="Search"
            class="nav-icon-small"
          />
        </button>
        <input
          type="text"
          name="search"
          placeholder="Search"
          class="search-input"
        />
      </div>
    </form>

    <div class="nav-links">
      <a href="${pageContext.request.contextPath}/home">Home</a>
      <a href="${pageContext.request.contextPath}/about" class="active"
        >About</a
      >
    </div>

    <div class="header-icons">
      <div
        class="icon cart"
        onclick="location.href='${pageContext.request.contextPath}/shoppingcart'"
      >
        <img
          src="${pageContext.request.contextPath}/assets/icon cart.png"
          alt="Shopping Cart"
        />
      </div>
      <div
        class="icon profile"
        onclick="location.href='${pageContext.request.contextPath}/profile'"
      >
        <img
          src="${pageContext.request.contextPath}/assets/icon user.png"
          alt="User Profile"
        />
      </div>
    </div>
  </nav>

  <nav class="category-nav">
      <a href="${pageContext.request.contextPath}/gadgets?search=Smartphone" class="category-item">
        <img src="${pageContext.request.contextPath}/assets/navbar phone.png" alt="" />
        Phones
      </a>

      <a href="${pageContext.request.contextPath}/gadgets?search=Laptop" class="category-item">
        <img src="${pageContext.request.contextPath}/assets/navbar computer.png" alt="" />
        Computers
      </a>

      <a href="${pageContext.request.contextPath}/gadgets?search=Watch" class="category-item">
        <img src="${pageContext.request.contextPath}/assets/navbar watch.png" alt="" />
        Smart Watches
      </a>

      <a href="${pageContext.request.contextPath}/gadgets?search=Camera" class="category-item">
        <img src="${pageContext.request.contextPath}/assets/navbar camera.png" alt="" />
        Cameras
      </a>

      <a href="${pageContext.request.contextPath}/gadgets?search=Headphone" class="category-item">
        <img src="${pageContext.request.contextPath}/assets/navbar headphone.png" alt="" />
        Headphones
      </a>
    </nav>
</header>
