<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <style>
        body { font-family: sans-serif; background-color: #f4f4f9; padding: 20px; }
        .container { max-width: 1000px; margin: 0 auto; }

        /* Header with Sign Out */
        .header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; }
        .header h1 { margin: 0; }

        /* Stats Cards */
        .stats-grid { display: flex; gap: 20px; margin-bottom: 30px; flex-wrap: wrap; justify-content: center}


        .card {
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            text-align: center;

            /* --- NEW FIXED SIZE SETTINGS --- */
            width: 200px;        /* 1. Set the exact width you want */
            height: 120px;       /* 2. Optional: Set a fixed height so they are all even */
            flex: none;          /* 3. Stop them from stretching (growing) or shrinking */

            /* Center content vertically if you added height */
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }
        .card h3 { margin: 0; color: #666; font-size: 14px; text-transform: uppercase; }
        .card .number { font-size: 36px; font-weight: bold; color: #333; margin-top: 10px; }

        /* Buttons */
        .btn { padding: 10px 20px; text-decoration: none; border-radius: 5px; color: white; border: none; cursor: pointer; font-size: 14px; display: inline-block;}
        .btn-add { background-color: #28a745; float: right; margin-bottom: 15px; }
        .btn-delete { background-color: #dc3545; padding: 5px 10px; font-size: 12px; }
        .btn-signout { background-color: #6c757d; } /* Grey color for sign out */
        .btn-signout:hover { background-color: #5a6268; }

        /* Table */
        table { width: 100%; border-collapse: collapse; background: white; border-radius: 8px; overflow: hidden; box-shadow: 0 2px 5px rgba(0,0,0,0.1); clear: both; }
        th, td { padding: 15px; text-align: left; border-bottom: 1px solid #eee; }
        th { background-color: #383B4A; color: white; }
    </style>
</head>
<body>

<div class="container">
    <div class="header">
        <h1>Admin Dashboard</h1>
        <a href="${pageContext.request.contextPath}/" class="btn btn-signout">Sign Out</a>
    </div>

    <div class="stats-grid">
        <div class="card">
            <h3>Total Products</h3>
            <div class="number">${totalProducts}</div>
        </div>

        <c:forEach var="entry" items="${categoryStats}">
            <c:if test="${not empty entry.key}">
                <div class="card">
                    <h3>${entry.key}</h3>
                    <div class="number">${entry.value}</div>
                </div>
            </c:if>
        </c:forEach>
    </div>

    <hr style="border: 0; border-top: 1px solid #ddd; margin: 30px 0;">

    <a href="add_product.jsp" class="btn btn-add">+ Add New Product</a>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Image</th>
                <th>Product Name</th>
                <th>Price</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="p" items="${products}">
                <tr>
                    <td>#${p.productId}</td>
                    <td>
                        <img src="${pageContext.request.contextPath}/assets${p.imagePath}" width="40" height="40" style="object-fit: contain;" onerror="this.style.display='none'">
                    </td>
                    <td>${p.name}</td>
                    <td>RM ${p.price}</td>
                    <td>
                        <form action="admin" method="post" onsubmit="return confirm('Are you sure you want to delete this?');" style="margin:0;">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="${p.productId}">
                            <button type="submit" class="btn btn-delete">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>