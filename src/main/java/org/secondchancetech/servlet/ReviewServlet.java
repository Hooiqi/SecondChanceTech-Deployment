package org.secondchancetech.servlet;

import org.secondchancetech.dao.ReviewDAO;
import org.secondchancetech.model.Review;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/reviews")
public class ReviewServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ReviewDAO dao = new ReviewDAO();
        List<Review> reviews = dao.getAllReviews();
        req.setAttribute("reviews", reviews);
        req.getRequestDispatcher("/jsp/reviews.jsp").forward(req, resp);
    }
}
