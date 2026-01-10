<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SecondChance Tech | About Us</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/variables.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/layout.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/about.css">
    <script src="${pageContext.request.contextPath}/js/main.js" defer></script>
</head>
<body>
    <jsp:include page="header.jsp" />

    <main class="about-container">
        <section class="about-hero">
            <div class="hero-content">
                <h1 class="about-title">About Us</h1>
                <h2 class="about-tagline">Premium tech, fair prices, and zero risk.</h2>
                <p class="about-description">
                    At SecondChance Tech, our goal is to make buying used electronics safe, honest, and easy for everyone. We believe that giving a device a second life should not mean compromising on quality or peace of mind.
                </p>
            </div>
            <div class="hero-logo">
                <img src="${pageContext.request.contextPath}/assets/logo about.png" alt="SecondChance Logo">
            </div>
        </section>

        <section class="team-section">
            <h2 class="section-heading">Meet The Team</h2>

            <div class="team-card reveal">
                <div class="member-photo">
                    <img src="${pageContext.request.contextPath}/assets/hooiqi.jpg" alt="Gooi Hooi Qi">
                </div>
                <div class="member-info">
                    <h3>Gooi Hooi Qi 23304682</h3>
                    <p class="role">Lead Front-End Developer</p>
                    <p class="bio">Hooi Qi leads the development of the Home and Product pages, including search and filtering features. She also oversees the final visual refinements to ensure a polished user interface across the platform.</p>
                </div>
            </div>

            <div class="team-card card-reverse reveal">
                <div class="member-photo">
                    <img src="${pageContext.request.contextPath}/assets/anyi.jpg" alt="Ong An Yi">
                </div>
                <div class="member-info">
                    <h3>Ong An Yi 23304671</h3>
                    <p class="role">UX Designer & Project Coordinator</p>
                    <p class="bio">An Yi manages the Landing Page, shopping cart and payment system. She also coordinates project documentation, tracking all technical specifications to meet project requirements.</p>
                </div>
            </div>

            <div class="team-card reveal">
                <div class="member-photo">
                    <img src="${pageContext.request.contextPath}/assets/akarsh.jpg" alt="Akarsh Srivastava">
                </div>
                <div class="member-info">
                    <h3>Akarsh Srivastava 23300053</h3>
                    <p class="role">System Architect & Backend Lead</p>
                    <p class="bio">Akarsh architects the database and manages the user authentication and profile systems. He also developed the "Smart Scoring System" for the Product Details page to provide data-driven tech ratings.</p>
                </div>
            </div>
        </section>
    </main>

    <jsp:include page="footer.jsp" />
</body>
</html>