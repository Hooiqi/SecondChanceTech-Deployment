<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SecondChance Tech - Landing Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/landing.css">
</head>
<body>
    <section class="landing-banner">
        <div class="landing-content">
            <a href="${pageContext.request.contextPath}/login" class="login-btn-top">Login</a>

            <img src="${pageContext.request.contextPath}/assets/logo landscape.png" alt="Logo" class="landing-logo">

            <p class="landing-description">
                Discover SecondChance Tech, the easy and reliable platform dedicated exclusively to buy quality used electronic devices.
            </p>

            <a href="${pageContext.request.contextPath}/signup" class="btn-get-started">Get Started &rarr;</a>
        </div>
    </section>

</body>
</html>