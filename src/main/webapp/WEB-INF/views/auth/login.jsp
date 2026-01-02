<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Login</title>

    <style>
      body {
        background: var(--bg-color);
        width: 100%;
        height: 100%;
      }
    </style>
  </head>

  <link
    rel="stylesheet"
    type="text/css"
    href="${pageContext.request.contextPath}/css/global.css"
  />

  <body>
    <div style="display: flex; height: 100vh">
      <div
        style="
          background: var(--bg-color);
          width: 100%;
          height: 100%;
          display: flex;
          flex-direction: column;
          justify-content: center;
        "
      >
        <div style="padding: 0rem 10rem">
          <img
            src="https://static.vecteezy.com/system/resources/previews/022/100/214/original/java-logo-transparent-free-png.png"
            alt="Logo"
            style="
              display: block;
              margin-left: auto;
              margin-right: auto;
              height: 5em;
            "
          />

          <h1 style="color: var(--text-primary); text-align: center; margin: 0">
            Welcome Back
          </h1>
          <p
            style="
              color: var(--text-primary);
              margin-bottom: 2rem;
              margin-top: 5px;
              text-align: center;
            "
          >
            Enter your email address and password to access your account
          </p>

          <form
            action="login"
            method="post"
            style="display: flex; flex-direction: column; gap: 0.75rem"
          >
            <div class="form-group">
              <label for="email">Email</label>
              <input
                id="email"
                class="j-input"
                type="email"
                name="email"
                placeholder="Enter your email"
                required
              />
            </div>

            <div class="form-group">
              <label for="password">Password</label>
              <input
                id="password"
                class="j-input"
                type="password"
                name="password"
                placeholder="Enter your password"
                required
              />
            </div>

            <% String error = (String) request.getAttribute("error"); %> <% if
            (error != null) { %>
            <div class="error-banner"><%= error %></div>
            <% } %>

            <a
              href="#"
              style="
                text-align: right;
                display: block;
                color: var(--text-primary);
              "
            >
              Forgot Password?
            </a>

            <button
              type="submit"
              class="submit-btn"
              style="color: var(--text-secondary); font-weight: bold"
            >
              Sign In
            </button>

            <a
              href="signup"
              style="
                color: var(--text-primary);
                text-align: center;
                display: block;
                margin-top: 0.5rem;
              "
              >Don't have an account? Sign up</a
            >
          </form>
        </div>
      </div>

      <div style="background: #333848; width: 100%">
        <img
          src="${pageContext.request.contextPath}/assets/auth-bg.webp"
          alt="Login Illustration"
          style="width: 100%; height: 100%; object-fit: contain"
        />
      </div>
    </div>
  </body>
</html>
