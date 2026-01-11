<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Checkout - Step 1</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/variables.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/layout.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/checkout.css">
</head>
<body>

    <jsp:include page="header.jsp" />

    <!-- Progress Steps -->
    <div class="steps-wrapper">
        <!-- Step 1 -->
        <div class="step-item">
            <div class="step-icon">
                <img src="${pageContext.request.contextPath}/assets/Location.png" class="step-icon-img" alt="Step 1" onerror="this.style.display='none'">
            </div>
            <div class="step-info">
                <span class="step-label">Step 1</span>
                <span class="step-title">Address</span>
            </div>
        </div>

        <div class="step-divider"></div>

        <!-- Step 2 -->
        <div class="step-item inactive">
            <div class="step-icon">
                <img src="${pageContext.request.contextPath}/assets/Shipping.png" class="step-icon-img" alt="Step 2" onerror="this.style.display='none'">
            </div>
            <div class="step-info">
                <span class="step-label">Step 2</span>
                <span class="step-title">Shipping</span>
            </div>
        </div>

        <div class="step-divider"></div>

        <!-- Step 3 -->
        <div class="step-item inactive">
            <div class="step-icon">
                <img src="${pageContext.request.contextPath}/assets/payment.png" class="step-icon-img" alt="Step 3" onerror="this.style.display='none'">
            </div>
            <div class="step-info">
                <span class="step-label">Step 3</span>
                <span class="step-title">Payment</span>
            </div>
        </div>
    </div>

    <!-- Main Content -->
    <main class="content-wrapper">
        <h2 class="section-title">Select Address</h2>

        <div class="address-list" id="addressList">
            <!-- Address cards will be rendered here -->
        </div>

        <!-- Add Address Section -->
        <div class="add-address-trigger" onclick="openAddressModal()">
            <div class="divider-row">
                <div class="h-line"></div>
                <div class="add-icon-box">
                    <img src="${pageContext.request.contextPath}/assets/Plus.png" class="icon-img" alt="Add" onerror="this.src='data:image/svg+xml,%3Csvg xmlns=%22http://www.w3.org/2000/svg%22 width=%2224%22 height=%2224%22 viewBox=%220 0 24 24%22%3E%3Cpath d=%22M12 5v14M5 12h14%22 stroke=%22%23313647%22 stroke-width=%222%22 stroke-linecap=%22round%22/%3E%3C/svg%3E'">
                </div>
                <div class="h-line"></div>
            </div>
            <div class="add-text">Add New Address</div>
        </div>

        <!-- Action Buttons -->
        <div class="nav-footer">
            <button class="btn btn-back" onclick="window.location.href='shoppingcart'">Back</button>
            <button class="btn btn-next" id="nextBtn" onclick="goToNextStep()">Next</button>
        </div>
    </main>

    <!-- Address Modal -->
    <div id="addressModal" class="modal">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title" id="modalTitle">Add New Address</h3>
                <button class="close-modal" onclick="closeAddressModal()">&times;</button>
            </div>
            <form id="addressForm" onsubmit="handleFormSubmit(event)">
                <div class="form-group">
                    <label class="form-label">Full Name *</label>
                    <input type="text" class="form-input" id="fullName" name="fullName" required>
                </div>
                <div class="form-group">
                    <label class="form-label">Phone Number *</label>
                    <input type="tel" class="form-input" id="phone" name="phone" required>
                </div>
                <div class="form-group">
                    <label class="form-label">Address Line *</label>
                    <input type="text" class="form-input" id="addressLine" name="addressLine" required>
                </div>
                <div class="form-group">
                    <label class="form-label">City *</label>
                    <input type="text" class="form-input" id="city" name="city" required>
                </div>
                <div class="form-group">
                    <label class="form-label">State *</label>
                    <input type="text" class="form-input" id="state" name="state" required>
                </div>
                <div class="form-group">
                    <label class="form-label">Postal Code *</label>
                    <input type="text" class="form-input" id="postalCode" name="postalCode" required>
                </div>
                <div class="modal-actions">
                    <button type="button" class="btn-cancel" onclick="closeAddressModal()">Cancel</button>
                    <button type="submit" class="btn-save" id="saveBtn">Save Address</button>
                </div>
            </form>
        </div>
    </div>

    <script>
            window.currentUserData = {
                userId: <%= session.getAttribute("currentUser") != null ?
                            ((org.secondchancetech.model.User)session.getAttribute("currentUser")).getUserId() :
                            "null" %>,
                firstName: "<%= session.getAttribute("currentUser") != null ?
                               ((org.secondchancetech.model.User)session.getAttribute("currentUser")).getFirstName() :
                               "" %>",
                lastName: "<%= session.getAttribute("currentUser") != null ?
                              ((org.secondchancetech.model.User)session.getAttribute("currentUser")).getLastName() :
                              "" %>",
                phone: "<%= session.getAttribute("currentUser") != null ?
                           ((org.secondchancetech.model.User)session.getAttribute("currentUser")).getPhone() :
                           "" %>",
                address: "<%= session.getAttribute("currentUser") != null ?
                             ((org.secondchancetech.model.User)session.getAttribute("currentUser")).getAddress() :
                             "" %>",
                city: "<%= session.getAttribute("currentUser") != null ?
                          ((org.secondchancetech.model.User)session.getAttribute("currentUser")).getCity() :
                          "" %>",
                state: "<%= session.getAttribute("currentUser") != null ?
                           ((org.secondchancetech.model.User)session.getAttribute("currentUser")).getState() :
                           "" %>",
                zipcode: "<%= session.getAttribute("currentUser") != null ?
                             ((org.secondchancetech.model.User)session.getAttribute("currentUser")).getZipcode() :
                             "" %>"
            };
        </script>

    <jsp:include page="footer.jsp" />

    <script src="${pageContext.request.contextPath}/js/step1.js?v=2"></script>

</body>
</html>